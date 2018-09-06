package com.baoke.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baoke.common.util.api.json.JsonApiUtil;

import net.sf.json.JSONArray;

public class BeanUtil {

	public static <T> Object convertToBean(Map<String, String> properties, Class<?> clazz) throws Exception {
		
		Object object = clazz.newInstance();
		
		Class<?> fieldType;
		ParameterizedType pt;
		Class<?> genericClazz;
		List<Field> fields = ClassUtil.getFieldWithObject(object.getClass());
		for (Field f : fields) {
			if (!properties.containsKey(f.getName()) || properties.get(f.getName()) == null
					|| properties.get(f.getName()).isEmpty()) {
				continue;
			}
			fieldType = f.getType();
			f.setAccessible(true);
			// 判断基础类型
			if (fieldType.isPrimitive() || fieldType.getName().startsWith("java.lang")) {
				f.set(object, convertBaseType(fieldType.getSimpleName(), properties.get(f.getName())));
				// 处理时间类型
			} else if (fieldType.getName().endsWith("Date")) {
				f.set(object, parseStringToDate(properties.get(f.getName())));
				// List类型
			} else if (fieldType.isAssignableFrom(List.class)) {
				Type fc = f.getGenericType();
				if (fc == null) {
					continue;
				}
				if (fc instanceof ParameterizedType) {
					pt = (ParameterizedType) fc;
					genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
					f.set(object, parseJsonArrayToList(properties.get(f.getName()), genericClazz));
				}
				// Java Bean 类型
			} else if (fieldType.getName().startsWith("com.baoke")) {
				f.set(object, parseJsonObjectToBean(properties.get(f.getName()), fieldType));
			} else {
			}
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> parseJsonArrayToList(String json, Class<T> genericClass) throws Exception {
		T genericObject;
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<T> beanList = new ArrayList<T>(jsonArray.size());
		for (int i = 0; i < jsonArray.size(); i++) {
			genericObject = (T) convertToBean(JsonApiUtil.convertForFirstChildren(jsonArray.getString(i)),
					genericClass);
			beanList.add(genericObject);
		}
		return beanList;
	}

	private static <T> Object parseJsonObjectToBean(String json, Class<T> targetClass) throws Exception {
		return convertToBean(JsonApiUtil.convertForFirstChildren(json), targetClass);
	}

	
	
	
	private static Date parseStringToDate(String dateStr) {
		Date date = null;
		Long dateLong = null;
		try {
			try {
				dateLong = Long.valueOf(dateStr);
			} catch (NumberFormatException nfe) {
			}
			if (dateLong != null) {
				date = new Date(Long.valueOf(dateStr));
			} else {
				date = DateUtil.parseForSecond(dateStr);
			}
		} catch (Exception e) {
		}
		return date;
	}
	
	/**
	 * 基础值类型转换
	 *
	 * @param type
	 *            值类型 , 可以是
	 *            "String","Float","float","Double","double","Integer","int",
	 *            "Boolean","boolean"
	 * @param value
	 * @return
	 */
	private static Object convertBaseType(String type, String value) {
		if (type.equals("Float") || type.equals("float")) {
			return new Float(value);
		}
		if (type.equals("Double") || type.equals("double")) {
			return new Double(value);
		}
		if (type.equals("Integer") || type.equals("int")) {
			return new Integer(value);
		}
		if (type.equals("Long") || type.equals("long")) {
			return new Long(value);
		}
		if (type.equals("Boolean") || type.equals("boolean")) {
			return Boolean.valueOf(value);
		}
		if (type.equals("String")) {
			return value;
		}
		return null;
	}
	
}
