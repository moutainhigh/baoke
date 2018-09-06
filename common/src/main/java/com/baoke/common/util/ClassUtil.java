package com.baoke.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassUtil {

	/**
	 * get class fields
	 * 
	 * @author wyh
	 * @date 2018年7月21日 上午9:47:38
	 * 
	 * @param classz
	 * @return
	 */
	public static List<Field> getFieldWithObject(Class<?> classz) {
		List<Field> fields = new ArrayList<Field>();
		Collections.addAll(fields, classz.getDeclaredFields());
		if (classz.getSuperclass() != null) {
			fields.addAll(getFieldWithObject(classz.getSuperclass()));
		}
		return fields;
	}
}
