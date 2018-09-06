package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 用户信息响应实体类
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午5:36:40
 */
public class QueryMyUserInfoResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1920966272952766336L;

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
		if (baseResultDto == null) {
			return this;
		}
		this.userInfo = (UserInfoDto) baseResultDto;
		this.userInfo.setUserCode(this.getCodeFromId(this.userInfo.getUserId()));
		this.userInfo.setUserId(null);
		return this;
	}
}
