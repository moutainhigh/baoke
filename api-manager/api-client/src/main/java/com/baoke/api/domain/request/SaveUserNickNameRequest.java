package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 设置－保存用户昵称
 * 
 * @author zdy
 * @date: 2018年7月9日 下午8:27:24
 */
public class SaveUserNickNameRequest extends BaseRequestParam {
	private static final long serialVersionUID = 1L;

	/** 用户昵称 */
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public BaseDto convert() throws MainException {
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setNickName(nickName);
		return userInfoDto;
	}

}
