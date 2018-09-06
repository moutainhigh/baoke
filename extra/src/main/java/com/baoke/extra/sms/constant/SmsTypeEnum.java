package com.baoke.extra.sms.constant;

/**
 * 短信回传类型枚举
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午11:14:21
 */
public enum SmsTypeEnum {

	UPSTREAM(0, "上行短信"), STATUS_REPORT(1, "手机接收状态报告");

	// 类型
	private int code;

	// 描述
	private String name;

	SmsTypeEnum(int code, String name) {
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
