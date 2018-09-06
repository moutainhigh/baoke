package com.baoke.user.constant;

/**
 * 是否越狱枚举
 * 
 * @author wyj
 * @date: 2018年6月13日 下午6:11:46
 */
public enum DeviceIsRootEnum {

	ROOT(1, "是"),

	NOT_ROOT(0, "否");

	private int code;

	private String name;

	DeviceIsRootEnum(int code, String name) {
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
