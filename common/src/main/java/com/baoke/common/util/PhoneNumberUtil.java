package com.baoke.common.util;

/**
 * 电话号码工具类
 *
 * @author zjj
 * @date 2018年6月18日 下午5:34:14
 */
public class PhoneNumberUtil {

	/**
	 * 验证手机号码 13 15 17 18 145 147 166 167 191 198 199
	 * 
	 * @author zjj
	 * @date 2018年6月18日 下午5:48:48
	 * @param phoneNumber
	 * @return
	 */
	public static boolean check(String phoneNumber) {
		if (StringUtil.isBlank(phoneNumber)) {
			return false;
		}
		return StringUtil.match(phoneNumber.trim(),
				"^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(14[5|7])|(16[6-7])|191|(19[8-9]))\\d{8}$");
	}

	public static void main(String[] args) {
		String mobile = "16796651593";
		System.out.println(check(mobile));

	}

}
