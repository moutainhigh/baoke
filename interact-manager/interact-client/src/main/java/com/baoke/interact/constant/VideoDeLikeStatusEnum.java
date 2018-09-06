package com.baoke.interact.constant;

/**
 * 是否不感兴趣
 */
public enum VideoDeLikeStatusEnum {

	LIKE(0, "感兴趣"),

	DELIKE(1, "不感兴趣");

	private int code;

	private String name;

	private VideoDeLikeStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * getGreatStatusEnumByCode:(根据code获取枚举).
	 * 
	 * date: 2018年6月5日 上午10:13:49
	 * 
	 * @author zjm
	 * @param code
	 * @return
	 */
	public static VideoDeLikeStatusEnum getDeLikeStatusEnumByCode(int code) {
		for (VideoDeLikeStatusEnum param : VideoDeLikeStatusEnum.values()) {
			if (param.getCode() == code) {
				return param;
			}
		}
		return null;
	}

	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}

		return getDeLikeStatusEnumByCode(code) != null;
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
