package com.baoke.sms.constant;

/**
 * 短信发送状态枚举
 */
public enum SmsSendStatusEnum {

	INVALID(-2, "已失效"),

	// 代码有BUG，还未使用状态
	FAIL(-1, "发送失败"),

	DATA_ERRO(0, "数据错误"),

	SENDING(1, "发送中"),

	SUCCESS(2, "发送成功");

	// 状态
	private int code;

	// 描述
	private String name;

	SmsSendStatusEnum(int code, String name) {
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
