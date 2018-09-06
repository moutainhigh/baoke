package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 手机登录并获取用户信息
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午2:45:20
 */
public class LoginSmsRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 手机号 */
	private String phone;

	/** 验证码 */
	private String smsCode;

	/** 类型 */
	private UserPhoneCodeTypeEnum userPhoneCodeTypeEnum = UserPhoneCodeTypeEnum.APP_LOGIN;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	@Override
	public BaseDto convert() throws MainException {
		SmsDto smsDto = new SmsDto();
		smsDto.setPhone(this.phone);
		smsDto.setCode(this.smsCode);
		smsDto.setUserPhoneCodeTypeEnum(this.userPhoneCodeTypeEnum);

		return smsDto;
	}

}
