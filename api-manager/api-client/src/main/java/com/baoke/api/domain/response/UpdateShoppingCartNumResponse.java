package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 更新购物车商品数量响应
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:02:24
 */
public class UpdateShoppingCartNumResponse extends BaseResponseParam {

	private static final long serialVersionUID = 7427014789441033806L;

	public UpdateShoppingCartNumResponse() {
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
