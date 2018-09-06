package com.baoke.user.constant;

/**
 * 性别枚举
 * 
 * @author wyj
 * @date: 2018年6月13日 下午6:12:01
 */
public enum UserSexEnum {

	MALE(0, "男"),

	FEMALE(1, "女");

	// 性别
	private int code;

	// 描述
	private String name;

	UserSexEnum(int code, String name) {
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
