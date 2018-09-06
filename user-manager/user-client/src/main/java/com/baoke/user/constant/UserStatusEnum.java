package com.baoke.user.constant;

/**
 * 用户状态枚举
 * 
 * @author zjm
 * @Date: 2018年6月5日 上午11:36:59
 */
public enum UserStatusEnum {

	DISABLE(0, "删除、禁止"),

	NORMAL(1, "正常");

	private int code;

	private String name;

	private UserStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * 根据code获取枚举
	 * 
	 * @author zjm
	 * @date: 2018年6月5日 上午10:13:49
	 * @param code
	 * @return
	 */
	public static UserStatusEnum getByCode(Integer code) {
		for (UserStatusEnum param : UserStatusEnum.values()) {
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
