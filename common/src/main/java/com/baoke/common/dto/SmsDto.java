package com.baoke.common.dto;

import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 短信验证码
 * 
 * @author zjm
 * @Date: 2018年6月11日 下午2:43:11
 */
public class SmsDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 手机号 */
	private String phone;

	/** 类型 */
	private UserPhoneCodeTypeEnum userPhoneCodeTypeEnum;

	/** 验证码 */
	private String code;

	public UserPhoneCodeTypeEnum getUserPhoneCodeTypeEnum() {
		return userPhoneCodeTypeEnum;
	}

	public void setUserPhoneCodeTypeEnum(UserPhoneCodeTypeEnum userPhoneCodeTypeEnum) {
		this.userPhoneCodeTypeEnum = userPhoneCodeTypeEnum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
