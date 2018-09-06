package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 设置默认收获地址响应
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:01:04
 */
public class SaveMyDefaultAddressResponse extends BaseResponseParam {

	private static final long serialVersionUID = -8893425165437709959L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
