package com.baoke.common.constant;

/**
 * 已读标记
 */
public enum IsReadEnum {

	READ(1, "已读"),

	UN_READ(0, "未读");

	private int code;
	private String name;

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private IsReadEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

}
