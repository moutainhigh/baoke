package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 获取支付签名数据
 * 
 * @author wyh
 * @date 2018年6月30日 下午3:41:35
 *
 */
public class OrderPaySignDto extends BaseResultDto {

	private static final long serialVersionUID = -6171577415755606486L;

	// 父类订单编号
	private String parentOrderNo;

	// 订单总价
	private String totalPrice;

	// 支付方式
	private Integer payType;

	// IP
	private String ip;

	// 订单签名CODE，支付时需要
	private String orderSignCode;

	public OrderPaySignDto() {
	}

	public OrderPaySignDto(boolean success, String message) {
		super(success, message);
	}

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOrderSignCode() {
		return orderSignCode;
	}

	public void setOrderSignCode(String orderSignCode) {
		this.orderSignCode = orderSignCode;
	}

}
