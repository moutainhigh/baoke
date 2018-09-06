package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 微信绑定手机号—获取短信验证码请求
 * 
 * @author zjm
 * @date: 2018年7月4日 下午5:27:39
 */
public class CreateBindPhoneCodeRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 手机号 */
	private String phone;

	/** 类型 */
	private UserPhoneCodeTypeEnum userPhoneCodeTypeEnum = UserPhoneCodeTypeEnum.APP_BIND;

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

	@Override
	public BaseDto convert() throws MainException {
		SmsDto smsDto = new SmsDto();
		smsDto.setPhone(this.phone);
		smsDto.setUserPhoneCodeTypeEnum(this.userPhoneCodeTypeEnum);
		return smsDto;
	}

}
