package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 卖家入账明细实体
 * 
 * @author: wyj
 * @date: 2018年6月21日 下午6:29:38
 */
public class SellerIncomeAmountDetail extends BaseDomain {

	private static final long serialVersionUID = 4174703317329714601L;

	// 主键id
	private Long id;

	// 卖家id
	private Long sellerId;

	// 支付人id
	private Long userId;

	// 预计入账金额
	private Integer estimateAmount;

	// 实际入账金额
	private Integer amount;

	// 父订单No
	private String parentOrderNo;

	// 订单No
	private String orderNo;

	// 支付流水id
	private Long payLogId;

	// 支付类型
	private Integer payType;

	// 状态
	private Integer status;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getEstimateAmount() {
		return estimateAmount;
	}

	public void setEstimateAmount(Integer estimateAmount) {
		this.estimateAmount = estimateAmount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getPayLogId() {
		return payLogId;
	}

	public void setPayLogId(Long payLogId) {
		this.payLogId = payLogId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
