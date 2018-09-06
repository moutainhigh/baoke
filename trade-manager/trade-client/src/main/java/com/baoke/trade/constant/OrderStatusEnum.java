package com.baoke.trade.constant;

/**
 * 订单状态
 */
public enum OrderStatusEnum {

	WAIT_PAY(10, "待支付"),

	ORDER_FAIL_NUM_LESS(22, "已支付-库存不足"),

	ORDER_FAIL(28, "已支付-订单失败"),

	WAIT_DELIVER(40, "待发货"),

	WAIT_RECEIVE(60, "待收货"),

	RECEIVED(66, "已完成");

	// WAIT_REFUND(70, "退款中"),
	//
	// REFUNDED(80, "待发货");

	private int code;
	private String name;

	OrderStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static String getNameByStatus(Integer status) {
		if (null == status) {
			return "";
		}
		for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
			if (status.equals(orderStatusEnum.getCode())) {
				return orderStatusEnum.name;
			}
		}
		return "";
	}

	public static OrderStatusEnum getEnumByStatus(Integer status) {
		if (null == status) {
			return null;
		}
		for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
			if (status.equals(orderStatusEnum.getCode())) {
				return orderStatusEnum;
			}
		}
		return null;
	}

}
