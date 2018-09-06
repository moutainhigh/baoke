package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 提交订单
 * 
 * @author wyh
 * @date 2018年6月30日 下午3:05:50
 *
 */
public class OrderSubmitDto extends BaseResultDto {

	private static final long serialVersionUID = -3265800086286469053L;

	// 是否来自购物车 0否 1是
	private Integer isShoppingCart;

	// 购物车主键id,逗号隔开
	private String shoppingCartIds;

	// 商品id
	private Long itemId;

	// 商品明细id
	private Long itemDetailId;

	// 数量
	private Integer num;

	public Integer getIsShoppingCart() {
		return isShoppingCart;
	}

	public void setIsShoppingCart(Integer isShoppingCart) {
		this.isShoppingCart = isShoppingCart;
	}

	public String getShoppingCartIds() {
		return shoppingCartIds;
	}

	public void setShoppingCartIds(String shoppingCartIds) {
		this.shoppingCartIds = shoppingCartIds;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemDetailId() {
		return itemDetailId;
	}

	public void setItemDetailId(Long itemDetailId) {
		this.itemDetailId = itemDetailId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
