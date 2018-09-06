package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.OrderSubmitDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 保存确认订单（去支付前）
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:51:38
 */
public class SaveMyOrderRequest extends BaseRequestParam {

	private static final long serialVersionUID = -3265800086286469053L;

	// 是否来自购物车
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

	@Override
	public BaseDto convert() throws MainException {
		OrderSubmitDto saveOrderDto = new OrderSubmitDto();
		saveOrderDto.setIsShoppingCart(isShoppingCart);
		saveOrderDto.setItemDetailId(itemDetailId);
		saveOrderDto.setItemId(itemId);
		saveOrderDto.setNum(num);
		saveOrderDto.setShoppingCartIds(shoppingCartIds);
		return saveOrderDto;
	}

}
