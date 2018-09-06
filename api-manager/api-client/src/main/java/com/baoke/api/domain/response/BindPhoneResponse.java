package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 微信绑定手机号响应体
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午4:43:30
 */
public class BindPhoneResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	/** 用户信息 */
	private UserInfoDto userInfo;

	public UserInfoDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDto userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {

		if (null == baseResultDto)
			return this;

		this.userInfo = (UserInfoDto) baseResultDto;
		this.userInfo.setUserCode(this.getCodeFromId(this.userInfo.getUserId()));
		this.userInfo.setUserId(null);
		return this;
	}

}
