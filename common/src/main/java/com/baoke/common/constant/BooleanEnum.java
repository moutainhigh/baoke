package com.baoke.common.constant;

/**
 * 是否可用状态
 */
public enum BooleanEnum {

	FALSE(0, "不可用、无效（删除）"),

	TRUE(1, "可用、有效");

	private int code;
	private String name;

	BooleanEnum(int code, String name) {
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
