package com.baoke.act.domain.constant;

/**
 * 本周推荐状态枚举
 * 
 * @author zjm
 * @date: 2018年7月26日 下午5:45:08
 */
public enum MallItemRecommandStatusEnum {

	UNENABLE(0, "不可用"),

	ENABLE(1, "可用");

	private int code;

	private String name;

	MallItemRecommandStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 根据code获取枚举
	 * 
	 * @author zjm
	 * @date: 2018年7月26日 下午5:45:51
	 * @param code
	 * @return
	 */
	public static MallItemRecommandStatusEnum getByCode(Integer code) {
		if (null == code) {
			return null;
		}
		for (MallItemRecommandStatusEnum param : MallItemRecommandStatusEnum.values()) {
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
