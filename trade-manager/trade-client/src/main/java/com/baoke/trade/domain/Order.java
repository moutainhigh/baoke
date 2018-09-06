package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 订单实体
 *
 * @author zjj
 * @date 2018年7月3日 上午9:34:37
 */
public class Order extends BaseDomain {

	private static final long serialVersionUID = -1268212754009166822L;

	// 主键id
	private Long id;

	// 用户id
	private Long userId;

	// 卖家id
	private Long sellerId;

	// 父订单号
	private String parentOrderNo;

	// 子订单号
	private String orderNo;

	// 商品总价
	private Integer totalItemPrice;

	// 商品总数量
	private Integer totalItemNum;

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

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public Order() {
	}

	public Order(Long userId, Integer status) {
		this.userId = userId;
		this.status = status;
	}

	public Order(Long sellerId, String orderNo) {
		super();
		this.sellerId = sellerId;
		this.orderNo = orderNo;
	}

	public Order(String parentOrderNo, Long addressId, String addressName, String addressPhone, String addressDetail) {
		super();
		this.parentOrderNo = parentOrderNo;
		this.addressId = addressId;
		this.addressName = addressName;
		this.addressPhone = addressPhone;
		this.addressDetail = addressDetail;
	}

	public Order(Long userId, Long sellerId, String parentOrderNo, String orderNo, Long addressId, String addressName,
			String addressPhone, Integer status, Integer totalItemPrice, Integer totalItemNum, Integer totalPostage) {
		this.userId = userId;
		this.sellerId = sellerId;
		this.parentOrderNo = parentOrderNo;
		this.orderNo = orderNo;
		this.addressId = addressId;
		this.addressName = addressName;
		this.addressPhone = addressPhone;
		this.status = status;
		this.totalItemPrice = totalItemPrice;
		this.totalItemNum = totalItemNum;
		this.totalPostage = totalPostage;
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

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getTotalItemPrice() {
		return totalItemPrice;
	}

	public void setTotalItemPrice(Integer totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}

	public Integer getTotalItemNum() {
		return totalItemNum;
	}

	public void setTotalItemNum(Integer totalItemNum) {
		this.totalItemNum = totalItemNum;
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
