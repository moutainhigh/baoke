package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 微信支付记录实体
 * 
 * @author: wyj
 * @date: 2018年6月25日 下午1:18:02
 */
public class PayWechatLog extends BaseDomain {

	private static final long serialVersionUID = 4285320750328565429L;

	// 主键id
	private Long id;

	// 用户id
	private Long userId;

	// 父订单号
	private String parentOrderNo;

	// 用户在商户appid下的唯一标识
	private String openId;

	// 微信开放平台审核通过的应用APPID
	private String appid;

	// 支付状态 1:支付成功,2:支付失败
	private Integer status;

	// 微信支付分配的商户号
	private String mchId;

	// 业务状态 SUCCESS FAIL
	private String resultCode;

	// 随机字符串，不长于32位
	private String nonceStr;

	// 签名
	private String sign;

	// 订单总金额，单位为分
	private Integer totalFee;

	// 支付类型,固定为APP
	private String tradeType;

	// 微信支付订单号
	private String transactionId;

	// 支付完成时间，格式为yyyyMMddHHmmss
	private String timeEnd;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public PayWechatLog() {
	}

	public PayWechatLog(Long userId, String parentOrderNo, Integer totalFee) {
		this.userId = userId;
		this.parentOrderNo = parentOrderNo;
		this.totalFee = totalFee;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
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
