package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.SellerInfoDto;

/**
 * 购物车商家、商品信息
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:50:52
 */
public class ShoppingCartItemDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private SellerInfoDto sellerInfo;// 播主（卖家）信息

	/** 商品集合 */
	private List<ShoppingCartItemDetailDto> shoppingCartItemDetailList;

	public ShoppingCartItemDto() {
	}

	public ShoppingCartItemDto(Long userId, Long deviceId) {
		super(userId, deviceId);
	}

	public ShoppingCartItemDto(Long userId, Long deviceId, SellerInfoDto sellerInfo,
			List<ShoppingCartItemDetailDto> shoppingCartItemDetailList) {
		super(userId, deviceId);
		this.sellerInfo = sellerInfo;
		this.shoppingCartItemDetailList = shoppingCartItemDetailList;
	}

	public SellerInfoDto getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfoDto sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public List<ShoppingCartItemDetailDto> getShoppingCartItemDetailList() {
		return shoppingCartItemDetailList;
	}

	public void setShoppingCartItemDetailList(List<ShoppingCartItemDetailDto> shoppingCartItemDetailList) {
		this.shoppingCartItemDetailList = shoppingCartItemDetailList;
	}
}
