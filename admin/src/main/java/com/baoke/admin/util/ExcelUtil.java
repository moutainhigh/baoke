package com.baoke.admin.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

//import com.taofen8.mktm.admin.domain.cps.AdminItemDTO;

public class ExcelUtil<T> {

	private static final Logger log = Logger.getLogger(ExcelUtil.class);
	
	private final static String exclusiveField = "serialVersionUID";

	public static final int READ_IGNORE_FIRST_ROW_YES = 1; //excel文件有表头

	public static final int READ_IGNORE_FIRST_ROW_NO = 0; //excel文件没有表头

	public boolean exportExcel(String fileName, PageBean<T> pageBean, HttpServletResponse response) throws Exception {
		return exportExcel(fileName, pageBean.getList(), response);
	}

	public boolean exportExcel(String fileName, 
			                   List<T> list,
			                   HttpServletResponse response) throws Exception {
		
		fileName = fileName + DateUtils.formatDateYMD(new Date());
		response.setContentType("aplication/vnd.ms-excel");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "inline; filename="
				+ new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");

		try {
			// 创建Excel工作薄
			WritableWorkbook wwb = Workbook.createWorkbook(response
					.getOutputStream());
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet(fileName, 0);
			Label label = null;
			boolean title = true;
			int rowNum = 1;
			for (T t : list) {
				// 填充数据
				Field fields[] = t.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					String fieldName = field.getName();
					Object type = field.getType();
					if (fieldName.equals(exclusiveField)) {
						continue;
					}

					// 添加标题
					if (title) {
						label = new Label(i, 0, fields[i].getName());
						sheet.addCell(label);
					}

					field.setAccessible(true);
					Object value = field.get(t);

					if (value != null) {
						if (type.toString().equals("class java.util.Date")) {
							try {
								label = new Label(i, rowNum, DateUtils.formatDateYMDHMS((Date) value));
							} catch (Exception e) {
								value = "";
							}
						} else {
							label = new Label(i, rowNum, (String) (value + ""));
						}
						sheet.addCell(label);
					}
				}

				title = false;
				rowNum++;
			}

			wwb.write();
			wwb.close();
			return true;
		} catch (Exception e) {
			log.error("ExcelUtil生成Excel异常", e);
			return false;
		}
	}

	
	public boolean exportExcelWithName(String fileName, 
            List<T> list,
            HttpServletResponse response,
            Map<String, String> nameMap) throws Exception {

			fileName = fileName + DateUtils.formatDateYMD(new Date());
			response.setContentType("aplication/vnd.ms-excel");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "inline; filename="
			+ new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");
			
			try {
			// 创建Excel工作薄
			WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream());
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet(fileName, 0);
			Label label = null;
			boolean title = true;
			int rowNum = 1;
			for (T t : list) {
			// 填充数据
			Field fields[] = t.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				Object type = field.getType();
				if (fieldName.equals(exclusiveField)) {
					continue;
				}
			
				// 添加标题
				if (title) {
					label = new Label(i, 0, nameMap.get(fieldName)==null?fieldName:nameMap.get(fieldName));
					sheet.addCell(label);
				}
			
				field.setAccessible(true);
				Object value = field.get(t);
			
				if (value != null) {
					if (type.toString().equals("class java.util.Date")) {
						try {
							label = new Label(i, rowNum, DateUtils.formatDateYMDHMS((Date) value));
						} catch (Exception e) {
							value = "";
						}
					} else {
						label = new Label(i, rowNum, (String) (value + ""));
					}
					sheet.addCell(label);
				}
			}
			
			title = false;
			rowNum++;
			}
			
			wwb.write();
			wwb.close();
			return true;
			} catch (Exception e) {
			log.error("ExcelUtil生成Excel异常", e);
			return false;
			}
		}
	
	
	
	/**
	 * 从Excel文件读取选品信息
	 * @param stream
	 * @param ignoreFirstRow
	 * @return
	 */
	public static Map<String,Object> getItemsFromExcel(InputStream stream) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map<String, String>> itemsList = new ArrayList<Map<String, String>>();
		int failureNum = 0;
		StringBuilder failureMsg = new StringBuilder();
		try{
			Workbook workbook = Workbook.getWorkbook(stream);
			Sheet sheet = null;
			if (null != workbook) {
				sheet = workbook.getSheet(0);
			}
			if (null != sheet) {
				int rownum = sheet.getRows();
				for (int i = 0; i < rownum; i++) {
					Cell[] cells = sheet.getRow(i);
					if (cells == null || cells.length == 0) {
						continue;
					}

					Map<String, String> cellMap = new HashMap<String, String>();
					for (int j = 0; j < cells.length; j++) {

						if (j==18){
							System.out.println("");
						}
						
						Cell source = cells[j];
						String content = source.getContents() == null ? ""
								: source.getContents().trim();
						
						if (StringUtils.isNotBlank(content) &&  source.getType() == CellType.DATE) {
							content = dateToString(source);
							
						}

						cellMap.put(String.valueOf(j), content);
					}
					itemsList.add(cellMap);
				}

			}
			resultMap.put("failureNum", failureNum);
			resultMap.put("failureMsg", failureMsg);
			resultMap.put("result", true);
			resultMap.put("itemsList", itemsList);

		}catch(Exception e){
			resultMap.put("result", false);
			resultMap.put("itemsList", itemsList);
			log.error("getItemsFromExcel",e);
		}
		return resultMap;
	}
	
	
	private static String dateToString(Cell source) {
		try {
			DateCell dateCell = (DateCell) source;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			return dateFormat.format(dateCell.getDate());
		} catch (Exception ex) {
			return org.springframework.util.StringUtils
					.trimAllWhitespace(source.getContents());
		}
	}
	
	private static Date trimDate(Cell source) {
		try {
			DateCell dateCell = (DateCell) source;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			String dateStr = dateFormat.format(dateCell.getDate());
			dateFormat.setTimeZone(TimeZone.getDefault());

			return dateFormat.parse(dateStr);
		} catch (Exception ex) {
			String strValue = org.springframework.util.StringUtils.trimAllWhitespace(source.getContents());
			try {
				return DateUtils.parseDateYMDHMS(strValue);
			} catch (ParseException ignore) {
				String message = new StringBuilder().append("尝试把【")
						.append(strValue).append("】")
						.append("转换成时间【yyyy-MM-dd HH:mm:ss】失败 : ").append("第【")
						.append(source.getRow() + 1).append("】行 第【")
						.append(source.getColumn() + 1).append("】列").toString();
				throw new RuntimeException(message, ex);
			}
		}
	}
}
