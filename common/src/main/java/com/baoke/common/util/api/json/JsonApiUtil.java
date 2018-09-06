package com.baoke.common.util.api.json;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.baoke.common.annotation.IgnoreJson;
import com.baoke.common.util.DateUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonApiUtil {

	/**
	 * 当json存在多个层级时,此方法只针对json的第一层children做解析
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午6:22:59
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> convertForFirstChildren(String jsonString) {
		Map<String, String> valueMap = new HashMap<String, String>();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		for (@SuppressWarnings("unchecked")
		Iterator<String> iterator = jsonObject.keys(); iterator.hasNext();) {
			String key = iterator.next();
			String value = jsonObject.getString(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}

	/**
	 * 自动把日期类型按指定格式转出字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String convertToJson(Object object) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(DateUtil.DATE_TIME_FORMAT_STR));
		jsonConfig.registerJsonValueProcessor(Integer.class, new ObjectToStringJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new ObjectToStringJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Double.class, new ObjectToStringJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Float.class, new ObjectToStringJsonValueProcessor());
		jsonConfig.addIgnoreFieldAnnotation(IgnoreJson.class);
		JSONObject json = JSONObject.fromObject(object, jsonConfig);
		return json.toString();
	}
}
