package com.baoke.common.util;

import java.util.Random;

/**
 * 获取任意位数随机数
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午11:13:32
 */
public class RandomNumUtil {

	/**
	 * 获取数字组成的随机数
	 * 
	 * @author zdy
	 * @date: 2018年7月25日 下午7:50:22
	 * @param length
	 *            生成字符串的长度
	 * @return
	 */
	public static String getRandomNum(int length) {
		if (length == 0) {
			return "";
		} else {
			return ((int) (Math.random() * 10) + "") + getRandomNum(--length);
		}
	}

	/**
	 * 获取数字+字母组成的随机数
	 * 
	 * @author zdy
	 * @date: 2018年7月25日 下午7:52:09
	 * @param length
	 *            成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(RandomNumUtil.getRandomString(6));
	}

}
