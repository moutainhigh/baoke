package com.baoke.common.constant;

/**
 * code枚举类
 * 
 * @author zjm
 * @date: 2018年6月26日 下午7:32:49
 */
public enum ResponseResultCodeEnum {

	FORBIDDEN(403, "没有权限，重新登录"),

	ERROR(500, "请求异常"),

	SUCCESS(200, "成功");

	private int code;

	private String name;

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

	private ResponseResultCodeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

}
