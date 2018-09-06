package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.OrderPayDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.dto.info.OrderInfoDto;

/**
 * 修改订单
 * 
 * @author zjj
 * @date 2018年7月9日 上午11:31:41
 */
public class SaveMyOrderItemNumResponse extends BaseResponseParam {

	private static final long serialVersionUID = -2899785654035349571L;

	// 父订单号
	private String parentOrderNo;

	// 总价
	private String totalPrice;

	// 默认支付方式 1支付宝 2微信
	private Integer payType;

	// 收货地址
	private AddressInfoDto addressInfo;

	// 订单列表
	private List<OrderInfoDto> orderList;

	// 是否来自购物车
	private boolean isShoppingCart;

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

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		OrderPayDto orderPayDto = (OrderPayDto) baseResultDto;
		this.parentOrderNo = orderPayDto.getParentOrderNo();
		this.totalPrice = orderPayDto.getTotalPrice();
		this.payType = orderPayDto.getPayType();
		this.addressInfo = orderPayDto.getAddressInfo();
		this.orderList = orderPayDto.getOrderList();
		this.isShoppingCart = orderPayDto.isShoppingCart();
		return this;
	}

}
