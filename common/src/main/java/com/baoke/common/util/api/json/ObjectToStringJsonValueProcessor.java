package com.baoke.common.util.api.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class ObjectToStringJsonValueProcessor implements JsonValueProcessor{

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return numberToString(arg0);
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		return numberToString(arg1);
	}
	
	private String numberToString(Object object){
		if(object == null){
			return "";
		} else {
			return object.toString();
		}
	}

}
