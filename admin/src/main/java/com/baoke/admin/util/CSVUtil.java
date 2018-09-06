package com.baoke.admin.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CSVUtil {
	
	private static final Logger log = Logger.getLogger(CSVUtil.class);
	
	/** CSV文件列分隔符 */
	private static final String CSV_COLUMN_SEPARATOR = ",";
	
	/** CSV文件行分隔符 */
	private static final String CSV_RN = "\r\n";
	
	/**
	 * 导出CSV文件
	 * 
	 * @param dataList
	 * @param colNames
	 * @param mapKey
	 * @param response
	 * @return
	 */
	public static boolean exportCSVFile(List<Map<String, Object>> dataList, String colNames, 
			String mapKey, HttpServletResponse response) {
		
		OutputStream os = null;
		try {
			
			StringBuffer sbf = new StringBuffer();
			// 列标题
			String[] colNamesArr = null;
			String[] mapKeyArr = null;
			
			colNamesArr = colNames.split(",");
			mapKeyArr = mapKey.split(",");
			
			// 完成数据csv文件的封装
			// 输出列头
			for (int i = 0; i < colNamesArr.length; i++) {
				sbf.append(colNamesArr[i]);
				if(i < colNamesArr.length - 1) {
					sbf.append(CSV_COLUMN_SEPARATOR);
				}
			}
			// 换行
			sbf.append(CSV_RN);
			if (null != dataList) { // 输出数据
				for (int i = 0; i < dataList.size(); i++) {
					for (int j = 0; j < mapKeyArr.length; j++) {
						sbf.append(dataList.get(i).get(mapKeyArr[j]));
						if(j < mapKeyArr.length - 1) {
							sbf.append(CSV_COLUMN_SEPARATOR);
						}
					}
					sbf.append(CSV_RN);
				}
			}
			// 写出响应
			os = response.getOutputStream();
			os.write(sbf.toString().getBytes("GBK"));
			os.close();
			return true;
		} catch (Exception e) {
			log.error("导出VSC文件失败:"+e.getMessage());
			return false;
		}
	}
	
	/**
	 * 设置头信息
	 * 
	 * @param response
	 */
	public static void responseSetProperties(String fileName, HttpServletResponse response) {
		
		try {
			// 设置文件后缀
			String fn = fileName + DateUtils.formatDateYMD(new Date()).toString() + ".csv";
			// 设置响应
			response.setContentType("application/ms-txt.numberformat:@");
			// 设置编码
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, "UTF-8"));
		} catch (Exception e) {
			log.error("设置头信息失败: ", e);
		}
	}
	
	
}
