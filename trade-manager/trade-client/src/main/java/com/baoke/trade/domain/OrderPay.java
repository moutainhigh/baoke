package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 父订单实体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:05:18
 */
public class OrderPay extends BaseDomain {

	private static final long serialVersionUID = -3039153736688204903L;

	// 订单主键id
	private Long id;

	// 用户id
	private Long userId;

	// 父订单号
	private String parentOrderNo;

	// 总价
	private Integer totalPrice;

	// 总运费
	private Integer totalPostage;

	// 地址id
	private Long addressId;

	// 收货人姓名
	private String addressName;

	// 收货人手机号
	private String addressPhone;

	// 收货人地址
	private String addressDetail;

	// 状态
	private Integer status;

	// 是否来自购物车
	private Integer fromShoppingCart;

	// 支付方式
	private Integer payType;

	// 支付描述
	private String payDesc;

	// 第三方支付订单号
	private String sourceOrderNo;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public OrderPay() {
	}

	public OrderPay(Long id, Integer payType) {
		this.id = id;
		this.payType = payType;
	}

	public OrderPay(Long userId, String parentOrderNo, Integer fromShoppingCart, Integer status, Integer payType) {
		this.userId = userId;
		this.parentOrderNo = parentOrderNo;
		this.fromShoppingCart = fromShoppingCart;
		this.status = status;
		this.payType = payType;
	}

	/**
	 * 组装地址明细
	 * 
	 * @author wyh
	 * @date 2018年7月7日 下午9:33:52
	 * 
	 * @param province
	 * @param city
	 * @param area
	 * @param address
	 */
	public void setAddressDetail(String province, String city, String area, String address) {
		this.addressDetail = province + " " + city + " " + area + " " + address;
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

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalPostage() {
		return totalPostage;
	}

	public void setTotalPostage(Integer totalPostage) {
		this.totalPostage = totalPostage;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFromShoppingCart() {
		return fromShoppingCart;
	}

	public void setFromShoppingCart(Integer fromShoppingCart) {
		this.fromShoppingCart = fromShoppingCart;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	public String getSourceOrderNo() {
		return sourceOrderNo;
	}

	public void setSourceOrderNo(String sourceOrderNo) {
		this.sourceOrderNo = sourceOrderNo;
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
