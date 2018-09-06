package com.baoke.admin.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.baoke.admin.util.DateUtils;

public class ExportTxtUtil<T> {

	private static final Logger log = Logger.getLogger(ExportTxtUtil.class);

	public String exportTxt(String filePath, List<T> list, String fileds,
			HttpServletResponse response) {

		long startTime = System.currentTimeMillis();
		OutputStream os = null;
		try {
			boolean title = true;
			StringBuilder sb = new StringBuilder();
			for (T t : list) {

				// 反射机制获取所有字段
				Field fields[] = t.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					String fieldName = field.getName();
					Object type = field.getType();

					field.setAccessible(true);
					if(fileds != null && fileds.indexOf(fieldName) == -1){
						continue;
					}
					if (title) {
						sb.append(fieldName + "\t");
					} else {
						if (type.toString().equals("class java.util.Date")) {
							try {
								sb.append(DateUtils.formatDateYMDHMS((Date) field.get(t))
										+ "\t");
							} catch (Exception e) {
								log.error("日期格式转换失败", e);
							}
						} else {
							sb.append((String) (field.get(t) == null ? "\t"
									: field.get(t) + "\t"));
						}
					}
				}
				sb.append("\r\n");
				title = false;
			}
			os = new FileOutputStream(filePath, true);
			byte[] byt = sb.toString().getBytes();
			os.write(byt);
			long endTime = System.currentTimeMillis();
			log.error("export file success, path=" + filePath + ", size=" + list.size() + ", time=" + (endTime - startTime) + "ms");
			return filePath;
		} catch (Exception e) {
			log.error("用户cid+邮箱导出失败:", e);
			return "";
		} finally {
			try {
				if(os !=null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				log.error("exportTxt I/O流关闭失败" + e.getMessage());
			}
		}
	}

	/**
	 * 发送服务器文件路径供客户端下载
	 * 
	 * @param filePath
	 * @param outFileName
	 * @return
	 */
	public boolean downLoadClient(String filePath, String outFileName,
			HttpServletResponse response) {

		long startTime = System.currentTimeMillis();
		OutputStream os = null;
		FileInputStream is = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 导出zip文件
			response.setContentType("application/x-msdownload");
			outFileName = URLEncoder.encode(outFileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + outFileName);
			is = new FileInputStream(filePath);
			// 放到缓冲流里面
			bis = new BufferedInputStream(is);
			byte[] buff = new byte[1024];
			int len;
			// 获取文件输出IO流
			os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			if (null != is) {
				while ((len = bis.read(buff)) != -1) {
					bos.write(buff, 0, len);
				}
			}
			long endTime = System.currentTimeMillis();
			log.info("download file success, path=" + filePath + ", time=" + (endTime - startTime) + "ms");
			return true;
		} catch (IOException e1) {
			log.error("用户cid+邮箱download失败:" + e1.getMessage());
			return false;
		} catch (Exception e) {
			log.error("用户cid+邮箱download失败:", e);
			return false;
		} finally {
			try {
				if(bos != null)
					bos.flush();
				if(os != null)
					os.close();
				if(is != null)
					is.close();
				if(bis != null)
					bis.close();
				if(bos != null)
					bos.close();
			} catch (IOException e) {
				log.error("downLoadClient I/O流关闭失败" + e.getMessage());
			}
		}
	}

	/**
	 * 将文件压缩成ZIP包
	 * 
	 * @param filePath
	 * @param fileName
	 * @param serverFilePath 
	 * @param response
	 * @return
	 */
	public String fileToZip(String serverFileName, HttpServletResponse response) {
		File file = new File(serverFileName);
		String downloadFileName = serverFileName.replace(".txt", ".zip");
		File zipfile = new File(downloadFileName);
		byte[] buf = new byte[1024];
		try {
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			// Compress the files
			FileInputStream in = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(in);
			// Add ZIP entry to output stream.
			out.putNextEntry(new ZipEntry(file.getName()));
			// Transfer bytes from the file to the ZIP file
			int len;
			while ((len = bis.read(buf)) != -1) {    
                out.write(buf, 0, len);    
            }    
			// Complete the entry
			out.closeEntry();
			in.close();
			// Complete the ZIP file
			out.close();
			file.delete();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

}
