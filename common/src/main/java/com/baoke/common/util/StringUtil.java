package com.baoke.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * Des: Date: 2018年5月28日 下午5:23:28
 * 
 * @author zjm
 * @version
 */
public class StringUtil {

	/**
	 * 
	 * 判断是否有值
	 * 
	 * date: 2018年6月5日 上午10:25:42
	 * 
	 * @author zjm
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {

		return org.springframework.util.StringUtils.hasLength(str);

	}

	/**
	 * 
	 * 判断是否为空
	 * 
	 * date: 2018年6月5日 上午10:26:16
	 * 
	 * @author zjm
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return org.springframework.util.StringUtils.isEmpty(str);
	}

	/**
	 * 
	 * 判断是否不为空
	 * 
	 * date: 2018年6月5日 上午10:26:16
	 * 
	 * @author zjm
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断是否为空
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午5:42:40
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.length() == 0 || str.trim().length() == 0;
	}

	/**
	 * 判断是否为空
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午5:42:40
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 
	 * equals:(判断值是否相等).
	 * 
	 * date: 2018年6月5日 上午10:27:01
	 * 
	 * @author zjm
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {

		if (null == str1 && null != str2) {
			return false;
		}

		if (null != str1 && null == str2) {
			return false;
		}

		if (null == str1 && null == str2) {
			return true;
		}

		return str1.equals(str2);

	}

	/**
	 * 
	 * urlFormat:(url中的参数替换，替换的对象为map的key，值为value).
	 * 
	 * date: 2018年6月12日 下午2:55:14
	 * 
	 * @author zjm
	 * @param url
	 * @param map
	 * @return
	 */
	public static String urlFormat(String url, Map<String, String> map) {

		for (Entry<String, String> entry : map.entrySet()) {
			url = url.replace(entry.getKey(), entry.getValue());
		}

		return url;

	}

	/**
	 * 检查字符串最大长度 input为空或空串返回false，超过最大长度返回false
	 * 
	 * @author zjj
	 * @date 2018年6月18日 下午5:30:18
	 * @param input
	 * @param maxLength
	 * @return
	 */
	public static boolean checkLength(String input, int maxLength) {
		if (isBlank(input)) {
			return false;
		}
		if (input.trim().length() <= maxLength) {
			return true;
		}
		return false;
	}

	/**
	 * 正则验证
	 * 
	 * @author zjj
	 * @date 2018年6月18日 下午5:38:19
	 * @param input
	 * @param regex
	 * @return
	 */
	public static boolean match(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(input).matches();
	}

	/**
	 * 验证字符串是否为数字
	 * 
	 * @author zjj
	 * @date 2018年6月18日 下午5:42:24
	 * @param input
	 * @return
	 */
	public static boolean inNumber(String input) {
		if (StringUtil.isBlank(input)) {
			return false;
		}
		return match(input.trim(), "^(-?\\d+)(\\.\\d+)?$");
	}

	/**
	 * 验证字符串是否为正数
	 * 
	 * @author zjj
	 * @date 2018年6月18日 下午5:43:29
	 * @param input
	 * @return
	 */
	public static boolean isPositiveNumber(String input) {
		if (StringUtil.isBlank(input)) {
			return false;
		}
		return match(input.trim(), "^(\\d+)(\\.\\d+)?$");
	}

	/**
	 * 验证字符串是否为中文、大小写英文或阿拉伯数字
	 * 
	 * @author zdy
	 * @date: 2018年7月25日 下午9:27:37
	 * @param input
	 * @return
	 */
	public static boolean validateCharNumChinese(String input) {
		if (StringUtil.isBlank(input)) {
			return false;
		}
		return match(input.trim(), "^[a-zA-Z0-9\u4E00-\u9FA5]+$");
	}

	/**
	 * 字符串中除中文、大小写英文或阿拉伯数字外都替换为0-9随机数字
	 * 
	 * @author zdy
	 * @date: 2018年7月25日 下午9:27:37
	 * @param input
	 * @return
	 */
	public static String replaceAllSpecialCharToRandomNum(String input) {
		if (StringUtil.isBlank(input)) {
			return "";
		}

		return input.replaceAll("[^a-zA-Z0-9\u4E00-\u9FA5]+", RandomNumUtil.getRandomNum(1));
	}

	/**
	 * 字符串的字节长度
	 * 
	 * @author zdy
	 * @date: 2018年7月27日 上午10:08:12
	 * @param input
	 * @return
	 */
	public static int byteLengthByCode(String input) {
		try {
			return input.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 按字节长度截取字符串的方法
	 * 
	 * @author zdy
	 * @date: 2018年7月26日 下午10:55:34
	 * @param input
	 *            字符串
	 * @param length
	 *            截取字节长度
	 * @return
	 */
	public static String byteSubstring(String input, int length) {
		String result = "";
		byte[] bytes;
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		try {
			bytes = input.getBytes("Unicode");

			for (; i < bytes.length && n < length; i++) {
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1) {
					n++; // 在UCS2第二个字节时n加1
				} else {
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0) {
						n++;
					}
				}
			}
			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1)

			{
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0)
					i = i - 1;
				// 该UCS2字符是字母或数字，则保留该字符
				else
					i = i + 1;
			}
			result = new String(bytes, 0, i, "Unicode");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(validateCharNumChinese("11大师傅但是a"));
		try {
			System.out.println("11大师傅但是1".getBytes("GBK").length);
			System.out.println(String.valueOf(152).length());
			System.out.println(byteSubstring("11大师傅但是1啊啊啊啊啊啊啊", 16));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
