package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 删除收获地址响应
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:53:35
 */
public class DeleteMyAddressResponse extends BaseResponseParam {

	private static final long serialVersionUID = 7264667117037160934L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
