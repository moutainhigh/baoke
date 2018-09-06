package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 获取手机验证码响应体
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午1:09:53
 */
public class CreateLoginPhoneCodeResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
