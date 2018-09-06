package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 微信绑定手机号—获取短信验证码
 * 
 * @author zjm
 * @date: 2018年7月4日 下午5:30:22
 */
public class CreateBindPhoneCodeResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}
}
