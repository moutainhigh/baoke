package com.baoke.interact.constant;

/**
 * 视频/评论 点赞状态枚举
 */
public enum GreatStatusEnum {

	UNGREAT(0, "取消点赞"),

	GREAT(1, "点赞");

	private int code;

	private String name;

	private GreatStatusEnum(int code, String name) {
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
	public static GreatStatusEnum getGreatStatusEnumByCode(int code) {
		for (GreatStatusEnum param : GreatStatusEnum.values()) {
			if (param.getCode() == code) {
				return param;
			}
		}
		return null;
	}

	/**
	 * 判断枚举是否存在
	 * 
	 * @author zjj
	 * @date 2018年6月21日 下午2:14:22
	 * @param code
	 * @return
	 */
	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}
		for (GreatStatusEnum param : GreatStatusEnum.values()) {
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
