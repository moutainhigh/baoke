package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 支付宝支付记录实体
 * 
 * @author: wyj
 * @date: 2018年6月21日 下午6:21:12
 */
public class PayAlipayLog extends BaseDomain {

	private static final long serialVersionUID = 2847776144891791832L;

	// 主键id
	private Long id;

	// 支付人userId
	private Long userId;

	// 父订单号
	private String parentOrderNo;

	// 订单标题
	private String subject;

	// 订单支付状态
	private Integer status;

	// 支付总金额
	private Integer totalAmount;

	// 支付宝支付流水id
	private String tradeNo;

	// 买家支付宝用户号
	private String buyerId;

	// 买家支付宝账号
	private String buyerLogonId;

	// 卖家支付宝用户号
	private String paySellerId;

	// 卖家支付宝账号
	private String paySellerEmail;

	// 支付宝返回交易状态
	private String tradeStatus;

	// 实收金额
	private String receiptAmount;

	// 付款金额
	private String buyerPayAmount;

	// 本次交易支付的订单金额，单位为人民币（元）
	private String payTotalAmount;

	// 应用id
	private String appId;

	// 编码格式
	private String charset;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public PayAlipayLog() {
	}

	public PayAlipayLog(Long id, int status, String tradeStatus) {
		this.id = id;
		this.status = status;
		this.tradeStatus = tradeStatus;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerLogonId() {
		return buyerLogonId;
	}

	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}

	public String getPaySellerEmail() {
		return paySellerEmail;
	}

	public void setPaySellerEmail(String paySellerEmail) {
		this.paySellerEmail = paySellerEmail;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public String getBuyerPayAmount() {
		return buyerPayAmount;
	}

	public void setBuyerPayAmount(String buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}

	public String getPayTotalAmount() {
		return payTotalAmount;
	}

	public void setPayTotalAmount(String payTotalAmount) {
		this.payTotalAmount = payTotalAmount;
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

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPaySellerId() {
		return paySellerId;
	}

	public void setPaySellerId(String paySellerId) {
		this.paySellerId = paySellerId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
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
