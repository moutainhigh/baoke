package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 购物车
 * 
 * @author zjm
 * @Date: 2018年6月11日 下午4:59:12
 */
public class ShoppingCartItemListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Integer itemNum;// 商品数量

	// 卖家商品列表
	private List<ShoppingCartItemDto> shoppingCartItemList;

	public ShoppingCartItemListDto() {
		super();
	}

	public ShoppingCartItemListDto(boolean success, String message) {
		super(success, message);
	}

	public ShoppingCartItemListDto(Long userId, Long deviceId) {
		super(userId, deviceId);
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public List<ShoppingCartItemDto> getShoppingCartItemList() {
		return shoppingCartItemList;
	}

	public void setShoppingCartItemList(List<ShoppingCartItemDto> shoppingCartItemList) {
		this.shoppingCartItemList = shoppingCartItemList;
	}
}
