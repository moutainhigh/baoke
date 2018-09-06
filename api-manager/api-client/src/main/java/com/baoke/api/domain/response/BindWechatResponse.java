package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;

/**
 * 手机号绑定微信返回
 * 
 * @author zjm
 * @date: 2018年7月5日 上午10:50:48
 */
public class BindWechatResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private UserInfoDto userInfo;

	public UserInfoDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDto userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (null == baseResultDto)
			return this;

		UserInfoDto userInfoDto = (UserInfoDto) baseResultDto;
		this.userInfo = userInfoDto;
		return this;
	}

}