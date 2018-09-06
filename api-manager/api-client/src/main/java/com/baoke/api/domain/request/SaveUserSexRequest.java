package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 设置－保存用户性别
 * 
 * @author zdy
 * @date: 2018年7月9日 下午8:28:17
 */
public class SaveUserSexRequest extends BaseRequestParam {
	private static final long serialVersionUID = 1L;

	/** 性别：1男性，2女性，0未知 */
	private Integer sex;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public BaseDto convert() throws MainException {
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setSex(this.sex);
		return userInfoDto;
	}

}
