package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.ShoppingCartDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 删除购物车中的商品请求
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:46:11
 */
public class DeleteShoppingCartRequest extends BaseRequestParam {
	private static final long serialVersionUID = 3282611347011688268L;

	// 购物车主键id
	private String shoppingCartIds;

	public String getShoppingCartIds() {
		return shoppingCartIds;
	}

	public void setShoppingCartIds(String shoppingCartIds) {
		this.shoppingCartIds = shoppingCartIds;
	}

	@Override
	public BaseDto convert() throws MainException {
		ShoppingCartDto shoppingCartParamDto = new ShoppingCartDto();
		shoppingCartParamDto.setShoppingCartIds(shoppingCartIds);
		return shoppingCartParamDto;
	}

}
