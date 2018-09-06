package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 删除购物车响应
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:53:46
 */
public class DeleteShoppingCartResponse extends BaseResponseParam {

	private static final long serialVersionUID = -1391599824654127215L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
