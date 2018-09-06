package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 微信绑定手机号（并重新下发用户信息）
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午4:42:03
 */
public class BindPhoneRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 手机号 */
	private String phone;

	/** 手机号 */
	private String smsCode;

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

		return smsDto;
	}

}
