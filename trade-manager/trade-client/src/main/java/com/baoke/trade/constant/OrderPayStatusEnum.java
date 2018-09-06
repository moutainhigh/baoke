package com.baoke.trade.constant;

/**
 * 订单支付状态
 */
public enum OrderPayStatusEnum {

	WAIT(10, "待支付"),

	GO(12, "去支付"),

	FAIL(20, "支付失败"),

	SUCCESS(30, "支付成功");

	private int code;
	private String name;

	OrderPayStatusEnum(int code, String name) {
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
