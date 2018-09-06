package com.baoke.sms.constant;

/**
 * 短信接收状态，暂未使用
 */
public enum SmsReceiveEnum {

	FAIL(-1, "接收失败"),

	SUCCESS(1, "接收成功");

	// 状态
	private int code;

	// 描述
	private String name;

	SmsReceiveEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
