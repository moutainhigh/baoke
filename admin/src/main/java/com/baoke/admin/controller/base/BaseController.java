package com.baoke.admin.controller.base;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * @Title: BaseController.java
 * @Description: 提供json回写方法
 */
public class BaseController {
	
	public static final String Default_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 反写json信息
	 * @param response
	 * @param json
	 * @throws IOException
	 */
	public void writeJson(HttpServletResponse response ,  String json) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 反写json信息
	 * @param response
	 * @param jsonMap
	 * @throws IOException
	 */
	public void writeJsonWithMap(HttpServletResponse response ,  Map<String,?> jsonMap) throws IOException{
		JSONObject jsonObject = fromObject(jsonMap);
		if(jsonObject.get("success")==null){
			jsonObject.put("success", true);
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(jsonObject.toString());
	}
	
	/**
	 * 反写json信息
	 * @param response
	 * @param jsonMap
	 * @throws IOException
	 */
	public void writeJson(HttpServletResponse response ,  Object jsonObj) throws IOException{
		JSONObject jsonObject = fromObject(jsonObj);
		jsonObject.put("success", true);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(jsonObject.toString());
	}
	
	/**
	 * 反写json信息
	 * @param response
	 * @param jsonList
	 * @throws IOException
	 */
	public void writeJsonWithMapList(HttpServletResponse response ,  List<Map<String,Object>> jsonList) throws IOException{
		
		JSONObject	jsonObject	= null;
		JSONArray	jsonArray		= new JSONArray();
		for(Map<String,Object> jsonMap : jsonList){
			jsonObject = fromObject(jsonMap);
			jsonArray.element(jsonObject);
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(jsonArray.toString());
	}
	
	/**
	 * 反写json信息
	 * @param response
	 * @param jsonList
	 * @throws IOException
	 */
	public void writeJsonWithObjectList(HttpServletResponse response ,  List<?> jsonList , int total) throws IOException{
		
		JSONObject	jsonObject	= null;
		JSONArray	jsonArray		= new JSONArray();
		for(Object jsonObj : jsonList){
			jsonObject = fromObject(jsonObj);
			jsonArray.element(jsonObject);
		}
		JSONObject jsonRows = new JSONObject();
		jsonRows.put("rows", jsonArray);
		jsonRows.put("total", total);
		jsonRows.put("success", true);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(jsonRows.toString());
	}

	
	/**
	 * 反写json信息
	 * @param response
	 * @param jsonList
	 * @throws IOException
	 */
	public void writeJsonForJsonArrayWithObjectList(HttpServletResponse response ,  List<?> jsonList) throws IOException{
		
		JSONObject	jsonObject	= null;
		JSONArray	jsonArray		= new JSONArray();
		for(Object jsonObj : jsonList){
			jsonObject = fromObject(jsonObj);
			jsonArray.element(jsonObject);
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(jsonArray.toString());
	}
	
	private JSONObject fromObject(Object obj){
		JsonConfig config = new JsonConfig();
///		config.registerJsonValueProcessor(Date.class, new JSONUtil(Default_DATE_PATTERN));
//		config.registerJsonValueProcessor(Integer.class, new JsonNumberToStrUtil());
//		config.registerJsonValueProcessor(Long.class, new JsonNumberToStrUtil());
//		config.registerJsonValueProcessor(Double.class, new JsonNumberToStrUtil());
//		config.registerJsonValueProcessor(Float.class, new JsonNumberToStrUtil());
		return JSONObject.fromObject(obj, config);
	}
	
	public static String getIpAddr(HttpServletRequest request) {   
		try{
	        String ip = request.getHeader("x-forwarded-for");     
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	           ip = request.getHeader("Proxy-Client-IP");     
	       }     
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	           ip = request.getHeader("WL-Proxy-Client-IP");     
	        }     
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	            ip = request.getRemoteAddr();     
	       }
	       if(ip.length() > 32){
	    	   ip = ip.substring(0, 31);
	       }
	       return ip;
		}catch(Exception e){
			return "";
		}
  }  
}
