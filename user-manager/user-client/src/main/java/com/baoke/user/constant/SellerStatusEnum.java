package com.baoke.user.constant;

/**
 * 卖家认证审核状态
 */
public enum SellerStatusEnum {

	NOT_SELLER(0, "未认证"),

	SELLER_CENTER(10, "审核中"),

	SELLER_PASS(20, "审核通过"),

	SELLER_REFUSE(30, "已拒绝");

	private int code;
	private String name;

	private SellerStatusEnum(int code, String name) {
		this.name = name;
		this.code = code;
	}

	/**
	 * 根据code获取枚举
	 * 
	 * @author zjm
	 * @date: 2018年7月18日 下午2:14:17
	 * @param code
	 * @return
	 */
	public static SellerStatusEnum getByCode(Integer code) {
		for (SellerStatusEnum param : SellerStatusEnum.values()) {
			if (param.getCode() == code) {
				return param;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
