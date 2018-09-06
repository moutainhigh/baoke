package com.baoke.common.util;

/**
 * 特殊字符工具类
 * 
 * @author zjm
 * @date: 2018年7月24日 下午4:26:50
 */
public class SpecharsUtil {

	/**
	 * 是否为特殊字符
	 * 
	 * @author zjm
	 * @date: 2018年7月24日 下午4:27:56
	 * @param str
	 * @return
	 */
	public static boolean isSpechar(String str) {

		char[] t = str.toCharArray();
		for (char a : t) {
			// 判断是否为汉字字符
			if (!Character.toString(a).matches("[\\u4E00-\\u9FA5]+")) {
				String tem = String.valueOf(a).replaceAll("[^\\w]", "");
				if (String.valueOf(a).length() != tem.length()) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isSpechar("123sss_"));
		System.out.println(isSpechar("123sss__ "));
		System.out.println(isSpechar("123sss__<>"));
		System.out.println(isSpechar("123sss__的"));
		System.out.println(isSpechar("的"));
		System.out.println(isSpechar("的 "));
		System.out.println(isSpechar("的_"));
		System.out.println(isSpechar("的<>"));
	}
}
