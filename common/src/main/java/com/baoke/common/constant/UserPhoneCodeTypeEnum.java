package com.baoke.common.constant;

/**
 * 用户手机验证码类型
 */
public enum UserPhoneCodeTypeEnum {

	APP_LOGIN(10, "App用户登录", 300000, 3, "您的登录验证码为{code}"),

	SELLER_AUTH(60, "店铺认证", 300000, 3, "您的认证验证码为{code}"),

	SELLER_LOGIN(50, "后台用户登录", 300000, 10, "您的登录验证码为{code}"),

	APP_BIND(20, "App帐号绑定", 300000, 3, "您的绑定验证码为{code}");

	private int code;

	private String name;

	/** 短信发送时效（毫秒） */
	private int validMillisecond;

	/** 短信每日发送次数 */
	private int sendLimit;

	/** 登录短信模板 */
	private String messageTemplate;

	private UserPhoneCodeTypeEnum(int code, String name, int validMillisecond, int sendLimit, String messageTemplate) {
		this.code = code;
		this.name = name;
		this.validMillisecond = validMillisecond;
		this.sendLimit = sendLimit;
		this.messageTemplate = messageTemplate;
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
	public UserPhoneCodeTypeEnum getUserPhoneCodeTypeEnumByCode(Integer code) {
		for (UserPhoneCodeTypeEnum param : UserPhoneCodeTypeEnum.values()) {
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

	public int getValidMillisecond() {
		return validMillisecond;
	}

	public void setValidMillisecond(int validMillisecond) {
		this.validMillisecond = validMillisecond;
	}

	public int getSendLimit() {
		return sendLimit;
	}

	public void setSendLimit(int sendLimit) {
		this.sendLimit = sendLimit;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

}
