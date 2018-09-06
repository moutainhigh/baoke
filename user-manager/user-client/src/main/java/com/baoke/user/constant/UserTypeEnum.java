package com.baoke.user.constant;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {

	USER(0, "普通用户"),

	SELLER(1, "播主");

	private int code;

	private String name;

	private UserTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * 根据code获取用户枚举
	 * 
	 * date: 2018年6月5日 上午10:13:49
	 * 
	 * @author zjm
	 * @param code
	 * @return
	 */
	public static UserTypeEnum getUserTypeEnumByCode(int code) {
		for (UserTypeEnum param : UserTypeEnum.values()) {
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
