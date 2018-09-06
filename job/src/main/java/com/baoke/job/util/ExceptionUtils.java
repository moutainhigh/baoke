package com.baoke.job.util;


/**
 * 异常处理工具类
 * @author linlvping
 *
 */
public class ExceptionUtils {
	
	/**
	 * 返回异常的详细信息
	 * @param e
	 * @return
	 */
	public static String getDetailMessage(Exception e) {
		if (e == null) {  
	        return "";  
	    }  
		return e.toString();
	}
	
	public static String getStackMessage(Exception e){
		if(e == null){
			return "";
		}
		
		StringBuilder builder = new StringBuilder(e.toString());
		for(StackTraceElement ele: e.getStackTrace()){
			builder.append(ele.toString()).append(System.getProperty("line.separator"));
		}
		
		return builder.toString();
	}

}
