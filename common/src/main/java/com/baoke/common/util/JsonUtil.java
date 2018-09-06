package com.baoke.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public final class JsonUtil {

    public static String string2json(String key, String value) { 
        JSONObject object = new JSONObject(); 
        object.put(key, value); 
        return object.toString(); 
    }

    @SuppressWarnings("rawtypes")
	public static Object json2Array(String json, Class valueClz) { 
        JSONArray jsonArray = JSONArray.fromObject(json); 
        return JSONArray.toArray(jsonArray, valueClz); 
    }

    public static String array2json(Object object) { 
        JSONArray jsonArray = JSONArray.fromObject(object); 
        return jsonArray.toString(); 
    }

    public static String map2json(Object object) { 
        JSONObject jsonObject = JSONObject.fromObject(object); 
        return jsonObject.toString(); 
    }

    @SuppressWarnings({"unchecked" })
	public static Map<Object,Object> json2Map(String json) { 
        JSONObject jsonObject = JSONObject.fromObject(json); 
        Map<Object, Object> valueMap = new HashMap<Object, Object>();
        for(Iterator<Object> iterator = jsonObject.keys(); iterator.hasNext();){
        	Object key = iterator.next();
        	Object value = jsonObject.get(key);
        	valueMap.put(key, value);
        }
        return valueMap;
     }

    public static String bean2json(Object object) { 
        JSONObject jsonObject = JSONObject.fromObject(object); 
        return jsonObject.toString(); 
    }

    public static String bean2jsonArray(Object object) { 
        JSONArray jsonObject = JSONArray.fromObject(object); 
        return jsonObject.toString(); 
    }

    @SuppressWarnings("rawtypes")
	public static Object json2Object(String json, Class beanClz) { 
        return JSONObject.toBean(JSONObject.fromObject(json), beanClz); 
    }

    public static String json2String(String json, String key) { 
        JSONObject jsonObject = JSONObject.fromObject(json); 
        return jsonObject.get(key).toString(); 
    } 
    
    /**
     * 从JSONArray对象转为List
     * @param jsonArray
     * @return
     */
	private static List<Object> populateArray(JSONArray jsonArray) { 
		List<Object> list = new ArrayList<Object>();
		
        for (int i = 0; i < jsonArray.size(); i++) {  
            if (jsonArray.get(i).getClass().equals(JSONArray.class)) {
                List<Object> _list = populateArray(jsonArray.getJSONArray(i));
                list.add(_list);
            } else if (jsonArray.get(i).getClass().equals(JSONObject.class)) {
                Map<Object, Object> _map = populateMap(jsonArray.getJSONObject(i));  
                list.add(_map);  
            } else {
                list.add(jsonArray.get(i));  
            }  
        }
        
        return list;
    }  
  
	/**
	 * 从JSONObject对象转为Map
	 * @param jsonObject
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Map<Object, Object> populateMap(JSONObject jsonObject) { 
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		for (Iterator iterator = jsonObject.entrySet().iterator(); iterator.hasNext();) {  
            String entryStr = String.valueOf(iterator.next());  
            String key = entryStr.substring(0, entryStr.indexOf("="));  
            if (jsonObject.get(key).getClass().equals(JSONObject.class)) {  
                Map<Object, Object> _map = populateMap(jsonObject.getJSONObject(key));  
                map.put(key, _map);
            } else if (jsonObject.get(key).getClass().equals(JSONArray.class)) {  
                List<Object> list = populateArray(jsonObject.getJSONArray(key));  
                map.put(key, list);  
            } else {  
                map.put(key, jsonObject.get(key));  
            }  
        }
		
        return map;  
    } 
    
	/**
	 * 从JSON字符串转成Map
	 * @param json
	 * @return
	 */
    public static Map<Object, Object> JSONArrayConvertMap(String json) {  
    	JSONObject jsonObject = JSONObject.fromObject(json);  
    	Map<Object, Object> valueMap = populateMap(jsonObject);
        return valueMap;  
    } 
}