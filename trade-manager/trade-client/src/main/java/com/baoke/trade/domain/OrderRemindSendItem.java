package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 提醒发货
 * 
 * @author zjj
 * @date 2018年7月20日 下午3:25:35
 */
public class OrderRemindSendItem extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;

	// 买家ID
	private Long userId;

	// 卖家ID
	private Long sellerId;

	// 订单编号
	private String orderNo;

	// 订单创建时间
	private Date orderTime;

	// 记录创建时间
	private Date createTime;

	// 记录更新时间
	private Date updateTime;

	public OrderRemindSendItem() {
	}

	public OrderRemindSendItem(Order order) {
		this.userId = order.getUserId();
		this.sellerId = order.getSellerId();
		this.orderNo = order.getOrderNo();
		this.orderTime = order.getCreateTime();
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

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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
