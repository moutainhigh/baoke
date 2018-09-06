package com.baoke.interact.constant;

/**
 * 视频评论状态
 */
public enum VideoCommentStatusEnum {

	DISABLE(0, "无效（删除）"),

	NORMAL(1, "有效");

	private int code;

	private String name;

	private VideoCommentStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 获取状态枚举
	 * 
	 * @author zdy
	 * @date: 2018年7月21日 下午4:01:31
	 * @param code
	 * @return
	 */
	public static VideoCommentStatusEnum getByCode(int code) {
		for (VideoCommentStatusEnum param : VideoCommentStatusEnum.values()) {
			if (param.getCode() == code) {
				return param;
			}
		}
		return null;
	}

	/**
	 * 是否存在
	 * 
	 * @author zdy
	 * @date: 2018年7月21日 下午4:01:48
	 * @param code
	 * @return
	 */
	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}
		for (VideoCommentStatusEnum param : VideoCommentStatusEnum.values()) {
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
