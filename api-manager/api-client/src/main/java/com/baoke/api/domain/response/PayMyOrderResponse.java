package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.OrderPaySignDto;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 支付订单
 * 
 * @author: wyj
 * @date: 2018年6月7日 上午10:26:33
 */
public class PayMyOrderResponse extends BaseResponseParam {

	private static final long serialVersionUID = -3758674694865876039L;

	// 订单编号
	private String parentOrderNo;

	// 订单总价
	private String totalPrice;

	// 默认支付类型
	private Integer payType;

	// 支付验签字符串
	private String orderSignCode;

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

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public String getOrderSignCode() {
		return orderSignCode;
	}

	public void setOrderSignCode(String orderSignCode) {
		this.orderSignCode = orderSignCode;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		OrderPaySignDto orderPaySignDto = (OrderPaySignDto) baseResultDto;
		this.parentOrderNo = orderPaySignDto.getParentOrderNo();
		this.payType = orderPaySignDto.getPayType();
		this.totalPrice = orderPaySignDto.getTotalPrice();
		this.orderSignCode = orderPaySignDto.getOrderSignCode();
		return this;
	}

}
