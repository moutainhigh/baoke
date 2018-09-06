package com.baoke.user.constant;

/**
 * 禁言状态枚举
 * 
 * @author zjm
 * @Date: 2018年6月5日 上午11:36:59
 */
public enum UserBannedEnum {

	NO(0, "未禁言"),

	YES(1, "已禁言"),

	ALWAYS(2, "永久禁言");

	private int code;

	private String name;

	private UserBannedEnum(int code, String name) {
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
	public static UserBannedEnum getBannedEnumByCode(Integer code) {
		for (UserBannedEnum param : UserBannedEnum.values()) {
			if (param.getCode() == code) {
				return param;
			}
		}
		return null;
	}

	/**
	 * 判断是否存在
	 * 
	 * @author wyh
	 * @date 2018年7月17日 下午8:10:01
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isExists(Integer code) {
		if (null == code) {
			return false;
		}
		for (UserBannedEnum param : UserBannedEnum.values()) {
			if (param.getCode() == code) {
				return true;
			}
		}
		return false;
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
