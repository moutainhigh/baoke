package com.baoke.trade.constant;

/**
 * 支付宝返回支付结构
 */
public enum AlipayStatusEnum {

	WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建，等待买家付款"),

	TRADE_CLOSED("TRADE_CLOSED", "未付款交易超时关闭，或支付完成后全额退款"),

	TRADE_SUCCESS("TRADE_SUCCESS", "交易支付成功"),

	TRADE_FINISHED("TRADE_FINISHED", "交易结束，不可退款");

	// 类型
	private String code;

	// 描述
	private String name;

	AlipayStatusEnum(String code, String name) {
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
