package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 购物车实体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:06:14
 */
public class OrderShoppingCart extends BaseDomain {

	private static final long serialVersionUID = -4555628070912304537L;

	// 主键id
	private Long id;

	// 用户id
	private Long userId;

	// 商品id
	private Long itemId;

	// 商品明细id
	private Long itemDetailId;

	// 商品数量
	private Integer num;

	// 总价
	private Integer totalPrice;

	// 单价
	private Integer price;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public OrderShoppingCart() {
	}

	public OrderShoppingCart(Long userId, Long itemId, Long itemDetailId) {
		this.userId = userId;
		this.itemId = itemId;
		this.itemDetailId = itemDetailId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
