package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.ShoppingCartDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 修改购物车商品数量
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:52:55
 */
public class UpdateShoppingCartNumRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1284733988788717736L;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public BaseDto convert() throws MainException {
		ShoppingCartDto shoppingCartParamDto = new ShoppingCartDto();
		shoppingCartParamDto.setContent(content);
		return shoppingCartParamDto;
	}

}
