package com.baoke.common.constant;

/**
 * 微信用户来源
 */
public enum WechatUserSourceTypeEnum {

	MOBILE_APP(10, "app"),

	WECHAT_OPEN(20, "微信公众号"),

	SMALL_APP(30, "小程序");

	private int code;

	private String name;

	private WechatUserSourceTypeEnum(int code, String name) {
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
	public static WechatUserSourceTypeEnum getWechatSourceTypeByCode(Integer code) {
		for (WechatUserSourceTypeEnum param : WechatUserSourceTypeEnum.values()) {
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
