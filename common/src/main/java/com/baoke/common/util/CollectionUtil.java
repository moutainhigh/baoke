package com.baoke.common.util;

import java.util.List;

/**
 * 集合工具类
 * 
 * @author zdy
 * @date: 2018年7月21日 下午3:26:56
 */
public class CollectionUtil {

	/**
	 * 判断集合是否是空
	 * 
	 * @author zdy
	 * @date: 2018年7月21日 下午3:27:09
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		return null == list || list.isEmpty();
	}

	/**
	 * 判断集合是否不未空
	 * 
	 * @author zdy
	 * @date: 2018年7月21日 下午3:27:36
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

}
