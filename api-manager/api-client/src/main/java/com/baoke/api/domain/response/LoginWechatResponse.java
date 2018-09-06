package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 微信登录
 * 
 * @author zjm
 * @date: 2018年6月13日 下午1:34:38
 */
public class LoginWechatResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

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

		LoginResultDto loginResultDto = (LoginResultDto) baseResultDto;
		this.userInfo = loginResultDto.getUserInfo();
		if (null != this.userInfo) {
			this.userInfo.setUserCode(this.getCodeFromId(this.userInfo.getUserId()));
			this.userInfo.setUserId(null);
		}
		return this;
	}

}
