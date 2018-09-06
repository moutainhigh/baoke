package com.baoke.user.constant;

/**
 * 是否是默认地址枚举
 * 
 * @author wyj
 * @date: 2018年6月13日 下午6:11:30
 */
public enum UserAddressDefaultEnum {

	NO(0, "否"),

	YES(1, "是");

	private int code;
	private String name;

	UserAddressDefaultEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	UserAddressDefaultEnum() {
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
