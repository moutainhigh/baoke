package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 修改订单地址和支付方式
 * 
 * @author zjj
 * @date 2018年7月7日 下午3:36:44
 */
public class OrderAddressDto extends BaseResultDto {

	private static final long serialVersionUID = -3265800086286469053L;

	// 父订单编号
	private String parentOrderNo;

	// 地址id
	private Long addressId;

	// 支付方式
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
}
