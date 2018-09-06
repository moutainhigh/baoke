package com.baoke.sms.constant;

/**
 * wyj
 */
public enum SmsChannelCode {

	YunTongXun("yuntongxun", "云通讯");

	private String code;
	private String name;

	SmsChannelCode(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}

}
