package com.baoke.common.dto;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;

/**
 * 登录返回dto
 *
 * @author zjj
 * @date 2018年6月26日 下午5:15:24
 */
public class LoginResultDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	UserInfoDto userInfo;

	public LoginResultDto() {
	}

	public LoginResultDto(boolean success, String message, UserInfoDto userInfoDto) {
		super(success, message);
		this.userInfo = userInfoDto;
	}

	public LoginResultDto(Long userId, Long deviceId, boolean success, String message, UserInfoDto userInfoDto) {
		super(userId, deviceId, success, message);
		this.userInfo = userInfoDto;
	}

	public UserInfoDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDto userInfoDto) {
		this.userInfo = userInfoDto;
	}

}
