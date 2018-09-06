package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 用户信息请求实体类
 * 
 * @author zjm
 * @Date: 2018年5月30日 下午1:58:48
 */
public class QueryMyUserInfoRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseDto convert() throws MainException {

		return new UserInfoDto();
	}

}
