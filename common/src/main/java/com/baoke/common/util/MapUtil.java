package com.baoke.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {

	/**
	 * Map转查询SOLR QUERY STRING
	 * @param map
	 * @return SOLR QUERY STRING
	 * @author wyh 2015-4-10 下午4:36:17
	 */
	public static String mapToSolrQueryString(Map<?,?> map) {
		StringBuilder sb = new StringBuilder();
		if (map == null || map.isEmpty()) {
			return "*:*";
		}
		Iterator<?> entrys = map.entrySet().iterator();
		while (entrys.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) entrys.next();
			sb.append("+").append(entry.getKey()).append(":");
			sb.append(entry.getValue()).append(" ");
		}
		return sb.toString();
	}
	
	/**
	 * Map转String
	 * @param map
	 * @return
	 * @author wyh 2015-4-8 下午10:26:09
	 */
	public static String objectToString(Map<?,?> map) {
		StringBuilder sb = new StringBuilder();
		if (map == null || map.isEmpty()) {
			return "";
		}
		Iterator<?> entrys = map.entrySet().iterator();
		while (entrys.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) entrys.next();
			sb.append("{\"key\":\"").append(entry.getKey()).append("\",");
			sb.append("\"value\":\"").append(entry.getValue()).append("\"},");
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(Object object) {
		if (object == null) {
			return new HashMap<String, Object>();
		}

		if (object instanceof Map) {
			return (Map<String, Object>) object;
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("value", object);

		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			field.setAccessible(true);
			try {
				map.put(fieldName, field.get(object));
			} catch (Exception e) {
				throw new RuntimeException("objectToMap error.");
			}
		}

		return map;
	}
}
