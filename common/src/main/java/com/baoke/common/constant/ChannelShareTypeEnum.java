package com.baoke.common.constant;

/**
 * 渠道分享类型
 */
public enum ChannelShareTypeEnum {

	WECHAT("WECHAT", "微信朋友圈"),

	WECHAT_FRIEND("WECHAT_FRIEND", "微信好友"),

	QQ("QQ", "QQ"),

	QQ_ZONE("QQ_ZONE", "QQ空间");

	private String code;
	private String name;

	ChannelShareTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
