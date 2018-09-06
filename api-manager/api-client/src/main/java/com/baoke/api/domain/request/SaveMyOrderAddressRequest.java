package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.OrderAddressDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 修改订单地址和支付方式
 * 
 * @author zjj
 * @date 2018年7月7日 下午10:12:08
 */
public class SaveMyOrderAddressRequest extends BaseRequestParam {

	private static final long serialVersionUID = -3265800086286469053L;

	private String parentOrderNo;

	private Long addressId;

	private Integer payType;

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Override
	public BaseDto convert() throws MainException {
		OrderAddressDto orderAddressDto = new OrderAddressDto();
		orderAddressDto.setParentOrderNo(parentOrderNo);
		orderAddressDto.setAddressId(addressId);
		orderAddressDto.setPayType(payType);
		return orderAddressDto;
	}

}
