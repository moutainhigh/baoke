package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.OrderPaySignDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 支付订单
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:50:29
 */
public class PayMyOrderRequest extends BaseRequestParam {

	private static final long serialVersionUID = 4949324958233250286L;

	// 父订单号
	private String parentOrderNo;

	// 支付类型
	private Integer payType;

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Override
	public BaseDto convert() throws MainException {
		OrderPaySignDto orderPaySignDto = new OrderPaySignDto();
		orderPaySignDto.setParentOrderNo(parentOrderNo);
		orderPaySignDto.setPayType(payType);
		orderPaySignDto.setIp(getIp());
		return orderPaySignDto;
	}

}
