package com.baoke.trade.constant;

/**
 * 订单物流状态
 */
public enum PostageStatusEnum {

	PLACED(10, "已下单"),

	DELIVED(30, "已发货"),

	SIGNED(60, "已签收");

	private int code;

	private String name;

	private PostageStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static String getNameByCode(int code) {
		PostageStatusEnum[] postageStatusEnums = PostageStatusEnum.values();
		for (PostageStatusEnum postageStatusEnum : postageStatusEnums) {
			if (postageStatusEnum.getCode() == code) {
				return postageStatusEnum.getName();
			}
		}
		return null;
	}

}
