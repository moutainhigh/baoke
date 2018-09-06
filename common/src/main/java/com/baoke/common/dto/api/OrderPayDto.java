package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.dto.info.OrderInfoDto;

/**
 * 支付订单
 * 
 * @author wyh
 * @date 2018年6月30日 下午3:05:27
 *
 */
public class OrderPayDto extends BaseResultDto {

	private static final long serialVersionUID = 7669711394672698976L;

	// 错误类型，0：正常； 1：未设置地址
	private Integer errorType;

	// 父类订单编号
	private String parentOrderNo;

	// 订单总价
	private String totalPrice;

	// 支付方式
	private Integer payType;

	private AddressInfoDto addressInfo;

	// 订单列表
	private List<OrderInfoDto> orderList;

	// 是否来自购物车
	private boolean isShoppingCart;

	public OrderPayDto() {
	}

	public OrderPayDto(boolean success, String message) {
		super(success, message);
	}

	public OrderPayDto(boolean success, String message, Integer errorType) {
		super(success, message);
		this.errorType = errorType;
	}

	public boolean isShoppingCart() {
		return isShoppingCart;
	}

	public void setShoppingCart(boolean isShoppingCart) {
		this.isShoppingCart = isShoppingCart;
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

	public AddressInfoDto getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfoDto addressInfo) {
		this.addressInfo = addressInfo;
	}

	public List<OrderInfoDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderInfoDto> orderList) {
		this.orderList = orderList;
	}

	public Integer getErrorType() {
		return errorType;
	}

	public void setErrorType(Integer errorType) {
		this.errorType = errorType;
	}

}
