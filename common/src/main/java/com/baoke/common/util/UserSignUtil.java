package com.baoke.common.util;

public class UserSignUtil {

	/**
	 * 用户签名
	 * 
	 * @author wyh
	 * @date 2018年7月12日 下午3:45:46
	 * 
	 * @param userId
	 * @param size
	 * @return
	 */
	public static String getSign(long userId, int size) {
		String userIdStr = String.valueOf(userId);
		while (userIdStr.length() < size) {
			userIdStr = "0" + userIdStr;
		}
		userIdStr = userIdStr.substring(userIdStr.length() - size);
		return userIdStr.substring(size / 2) + userIdStr.substring(0, size / 2);
	}

	public static void main(String[] args) {
		System.out.println(getSign(1l, 2));
		System.out.println(getSign(12l, 2));
		System.out.println(getSign(1234l, 2));
		System.out.println(getSign(12l, 4));
		System.out.println(getSign(123l, 4));
		System.out.println(getSign(1234l, 4));
		System.out.println(getSign(12345l, 4));
		System.out.println(getSign(1234567l, 6));
	}

}
