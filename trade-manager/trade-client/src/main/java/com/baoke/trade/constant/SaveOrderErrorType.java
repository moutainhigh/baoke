package com.baoke.trade.constant;

/**
 * 订单状态
 */
public enum SaveOrderErrorType {

	NORMAL(0, "正常"),

	NO_ADDRESS(1, "未设置收货地址"),

	ITEM_NOT_ENOUGH(2, "库存不足");

	private int code;
	private String name;

	SaveOrderErrorType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static SaveOrderErrorType getByCode(Integer code) {
		if (null == code) {
			return null;
		}
		for (SaveOrderErrorType orderStatusEnum : SaveOrderErrorType.values()) {
			if (code.equals(orderStatusEnum.getCode())) {
				return orderStatusEnum;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
