package com.baoke.item.constant;

/**
 * 视频状态
 */
public enum VideoStatusEnum {

	WAIT_AUDIT(10, "审核中"),

	AUDIT_FAIL(20, "审核失败"),

	ONLINE(30, "审核通过(已上线)"),

	DOWNLINE(60, "已下线"),

	FORCE_DOWNLINE(80, "封禁中");

	private int code;

	private String name;

	VideoStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 根据code获取枚举
	 * 
	 * @author zjm
	 * @date: 2018年6月15日 下午6:40:10
	 * @param code
	 * @return
	 */
	public static VideoStatusEnum getVideoStatusEnumByCode(Integer code) {
		for (VideoStatusEnum param : VideoStatusEnum.values()) {
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
