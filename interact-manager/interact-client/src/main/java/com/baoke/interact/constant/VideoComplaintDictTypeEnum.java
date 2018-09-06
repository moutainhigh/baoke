package com.baoke.interact.constant;

/**
 * 举报字典操作
 */
public enum VideoComplaintDictTypeEnum {

	INPUT(1, "自填"),

	SELECT(0, "非自填");

	private int code;

	private String name;

	private VideoComplaintDictTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * 根据code获取枚举
	 * 
	 * @author zjm
	 * @date: 2018年6月5日 上午10:13:49
	 * @return
	 */
	public VideoComplaintDictTypeEnum getVideoComplaintDictTypeEnumByCode(Integer code) {
		for (VideoComplaintDictTypeEnum param : VideoComplaintDictTypeEnum.values()) {
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
