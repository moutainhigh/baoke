package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.ShoppingCartDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询购物车请求体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:50:49
 */
public class QueryShoppingCartRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseDto convert() throws MainException {
		return new ShoppingCartDto();
	}

}
