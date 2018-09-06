package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询我的地址列表请求体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:49:49
 */
public class QueryMyAddressRequest extends BaseRequestParam {

	private static final long serialVersionUID = -6110536313628955255L;

	@Override
	public BaseDto convert() throws MainException {
		return new AddressInfoDto();
	}

}
