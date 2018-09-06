package com.baoke.interact.constant;

/**
 * 消息排序类型
 */
public enum MessageInfoSortEnum {

	OTHERS(0, "其他"),

	SYSTEM(1, "系统");

	private int code;

	private String name;

	private MessageInfoSortEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * 根据code获取枚举
	 * 
	 * date: 2018年6月5日 上午10:13:49
	 * 
	 * @author zjm
	 * @param code
	 * @return
	 */
	public static MessageInfoSortEnum getMessageInfoSortEnum(Integer code) {
		if (null == code) {
			return null;
		}
		for (MessageInfoSortEnum param : MessageInfoSortEnum.values()) {
			if (code.equals(param.getCode())) {
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
