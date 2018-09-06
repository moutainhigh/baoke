package com.baoke.trade.constant;

/**
 * 是否是购物车过来的订单
 * 
 * @author wyj
 * @date: 2018年6月13日 下午6:09:19
 */
public enum IsShoppingCartEnum {

	NO(0, "否"),

	YES(1, "是");

	private int code;
	private String name;

	IsShoppingCartEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static String getDescByStatus(int status) {
		for (IsShoppingCartEnum orderStatusEnum : IsShoppingCartEnum.values()) {
			if (orderStatusEnum.code == status) {
				return orderStatusEnum.name;
			}
		}
		return "";
	}

	/**
	 * 判断枚举是否存在
	 * 
	 * @author zjj
	 * @date 2018年6月30日 下午4:49:32
	 * @param code
	 * @return
	 */
	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}
		for (IsShoppingCartEnum isShoppingCartEnum : IsShoppingCartEnum.values()) {
			if (code.equals(isShoppingCartEnum.getCode())) {
				return true;
			}
		}
		return false;
	}

}
