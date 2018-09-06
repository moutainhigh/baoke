package com.baoke.trade.constant;

/**
 * 支付宝返回支付结构
 */
public enum WeChatResultCode {

	FAIL("FAIL", "失败"),

	SUCCESS("SUCCESS", "成功");

	// 类型
	private String code;

	// 描述
	private String name;

	WeChatResultCode(String code, String name) {
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
