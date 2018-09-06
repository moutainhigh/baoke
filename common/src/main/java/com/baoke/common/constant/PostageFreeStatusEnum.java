package com.baoke.common.constant;

/**
 * 是否包邮
 */
public enum PostageFreeStatusEnum {

	FREE(1, "包邮"),

	UN_FREE(0, "不包邮");

	private int code;
	private String name;

	PostageFreeStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
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
