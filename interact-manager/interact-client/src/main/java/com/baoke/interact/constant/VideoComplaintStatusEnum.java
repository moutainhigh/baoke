package com.baoke.interact.constant;

/**
 * 视频举报枚举
 */
public enum VideoComplaintStatusEnum {

	PROCESS(0, "举报中"),

	SUCCESS(1, "举报成功"),

	FAIL(2, "举报失败");

	private int code;

	private String name;

	private VideoComplaintStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 根据code获取枚举
	 */
	public static VideoComplaintStatusEnum getVideoComplaintResultEnumByCode(Integer code) {
		if (null == code) {
			return null;
		}
		for (VideoComplaintStatusEnum param : VideoComplaintStatusEnum.values()) {
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
