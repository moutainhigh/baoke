package com.baoke.push.constant;

/**
 * 发送状态枚举
 * 
 * @author: ljj
 * @date: 2018年6月15日 上午11:14:46
 */
public enum PushStatusEnum {

	SENT(1, "已发送"), UNSENT(0, "未发送");

	// 状态
	private int code;

	// 描述
	private String name;

	PushStatusEnum(int code, String name) {
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
