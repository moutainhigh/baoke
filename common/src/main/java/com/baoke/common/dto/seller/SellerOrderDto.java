package com.baoke.common.dto.seller;

import java.io.Serializable;

import com.baoke.common.dto.info.OrderInfoDto;

/**
 * 卖家订单dto
 * 
 * @author: wyj
 * @date: 2018年7月12日 下午1:54:30
 */
public class SellerOrderDto implements Serializable {

	private static final long serialVersionUID = 5582244113925552255L;

	private OrderInfoDto orderInfoDto;

	private String userNickName;

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public OrderInfoDto getOrderInfoDto() {
		return orderInfoDto;
	}

	public void setOrderInfoDto(OrderInfoDto orderInfoDto) {
		this.orderInfoDto = orderInfoDto;
	}

	public SellerOrderDto(OrderInfoDto orderInfoDto, String userNickName) {
		super();
		this.orderInfoDto = orderInfoDto;
		this.userNickName = userNickName;
	}

	public SellerOrderDto() {
		super();
	}

}
