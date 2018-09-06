package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 获取手机验证码
 * 
 * @author zjm
 * @Date: 2018年6月6日 上午11:26:11
 */
public class CreateLoginPhoneCodeRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 手机号 */
	private String phone;

	/** 类型 */
	private UserPhoneCodeTypeEnum userPhoneCodeTypeEnum = UserPhoneCodeTypeEnum.APP_LOGIN;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserPhoneCodeTypeEnum getUserPhoneCodeTypeEnum() {
		return userPhoneCodeTypeEnum;
	}

	public void setUserPhoneCodeTypeEnum(UserPhoneCodeTypeEnum userPhoneCodeTypeEnum) {
		this.userPhoneCodeTypeEnum = userPhoneCodeTypeEnum;
	}

	@Override
	public BaseDto convert() throws MainException {
		SmsDto smsDto = new SmsDto();
		smsDto.setPhone(this.phone);
		smsDto.setUserPhoneCodeTypeEnum(userPhoneCodeTypeEnum);
		return smsDto;
	}

}
