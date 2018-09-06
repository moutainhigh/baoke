package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.ShoppingCartItemDto;
import com.baoke.common.dto.api.ShoppingCartItemListDto;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 查询购物车
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:59:53
 */
public class QueryShoppingCartResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private List<ShoppingCartItemDto> shoppingCartItemList;// 卖家商品列表

	private Integer itemNum;// 商品数量

	public List<ShoppingCartItemDto> getShoppingCartItemList() {
		return shoppingCartItemList;
	}

	public void setShoppingCartItemList(List<ShoppingCartItemDto> shoppingCartItemList) {
		this.shoppingCartItemList = shoppingCartItemList;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		ShoppingCartItemListDto shoppingCartItemListDto = (ShoppingCartItemListDto) baseResultDto;
		this.shoppingCartItemList = shoppingCartItemListDto.getShoppingCartItemList();
		this.itemNum = shoppingCartItemListDto.getItemNum();
		return this;
	}
}
