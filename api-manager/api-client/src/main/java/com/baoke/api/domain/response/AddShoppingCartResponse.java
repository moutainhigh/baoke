package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 加入购物车响应体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:53:19
 */
public class AddShoppingCartResponse extends BaseResponseParam {

	private static final long serialVersionUID = -1391599824654127215L;

	public AddShoppingCartResponse() {
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
