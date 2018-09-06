package com.baoke.admin.util;

import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;


public class UrlParameterUtils {
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getParameterFromRequest(HttpServletRequest request){
    	Map<String, String> parameterMap= new HashMap<String, String>();
		//获取请求参数
		Enumeration pNames=request.getParameterNames();
		while(pNames.hasMoreElements()){
		    String name=(String)pNames.nextElement();
		    String value=request.getParameter(name);
		    if(StringUtils.isNotEmpty(value) && !"v".equals(name)){
		    	parameterMap.put(name, value);
		    }
		}
		getQueryMap(parameterMap);
		return parameterMap;
    }
	
	public static Map<String, Object> getParameterFromRequestForHandle(HttpServletRequest request) throws ParseException{
		Map<String, String> parameterMap = getParameterFromRequest(request);
		Map<String, Object> map = new HashMap<String, Object>(parameterMap);
		for (Map.Entry<String, String> entry : parameterMap.entrySet()) { 
			String name = entry.getKey();
			String value = entry.getValue();
			if(!StringUtils.isEmpty(value)) {
				if("lastLoginTime".equals(name) || "gmtCreate".equals(name) || "gmtModified".equals(name)){
					map.put(name, DateUtils.parseDateYMDHMS(value));
				}else if("password".equals(name)) {
					value = Md5Encrypt.md5(value, "UTF-8");
					map.put(name, value);
				}
			}
		}
		return map;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	private static void getQueryMap(Map<String, String> map) {
		if (!StringUtils.isEmpty(map.get("filters"))) {
			JSONObject OrginalJson = JSONObject.fromObject((String)map.get("filters"));
			JSONArray filtersArray = JSONArray.fromObject(OrginalJson);
	
			Map<String, Class<Rules>> classMap = new HashMap<String, Class<Rules>>();
			classMap.put("rules", Rules.class);
			List<Filter> list = JSONArray.toList(filtersArray, Filter.class,classMap);
			
			for(Filter filter:list){
				for(Rules rule:filter.getRules()){
					if(StringUtils.isNotEmpty(rule.getData())){
						map.put(rule.getfield(), rule.getData());
					}
				}
			}
		}
	}
	
	/**
	 * 剔除参数中值为""空字符串的,没有参数值的,统一返回null
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> trimParameters(HttpServletRequest request){
		Map<String, String> parameterMap= new HashMap<String, String>();
		//获取请求参数
		Enumeration pNames=request.getParameterNames();
		while(pNames.hasMoreElements()){
		    String name=(String)pNames.nextElement();
		    String value=request.getParameter(name);
		    if(value!=null){
		    	value = value.trim();
		    	if(value.length()==0){
		    		value = null;
		    	}
		    }
		    parameterMap.put(name, value);
		}
		return parameterMap;
	}

}
