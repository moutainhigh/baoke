package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.ShoppingCartDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 加入购物车的请求
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:44:35
 */
public class AddShoppingCartRequest extends BaseRequestParam {

	private static final long serialVersionUID = -6706056216907370198L;

	// 商品id
	private Long itemId;

	// 商品明细id
	private Long itemDetailId;

	// 商品数量
	private Integer num;

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
		ShoppingCartDto shoppingCartParamDto = new ShoppingCartDto();
		shoppingCartParamDto.setItemId(itemId);
		shoppingCartParamDto.setItemDetailId(itemDetailId);
		shoppingCartParamDto.setNum(num);
		return shoppingCartParamDto;
	}

}
