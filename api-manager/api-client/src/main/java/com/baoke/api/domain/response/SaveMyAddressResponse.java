package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 保存收货地址响应
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:00:54
 */
public class SaveMyAddressResponse extends BaseResponseParam {

	private static final long serialVersionUID = -4999593740240480976L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
