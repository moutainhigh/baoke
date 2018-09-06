package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 购物车信息
 * 
 * @author zjm
 * @Date: 2018年6月11日 下午5:01:32
 */
public class ShoppingCartDto extends BaseResultDto {

	private static final long serialVersionUID = -6706056216907370198L;

	// 购物车主键id
	private Long shoppingCartId;

	// 购物车多主键IDS，多个用逗号分隔
	private String shoppingCartIds;

	// 商品id
	private Long itemId;

	// 商品明细id
	private Long itemDetailId;

	// 商品数量
	private Integer num;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
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
