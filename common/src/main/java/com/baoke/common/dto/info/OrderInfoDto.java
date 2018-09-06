package com.baoke.common.dto.info;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 订单dto
 * 
 * @author zdy
 * @date: 2018年6月13日 下午6:07:58
 */
public class OrderInfoDto extends BaseResultDto {

	private static final long serialVersionUID = -734750270543903676L;

	// 子订单编号
	private String orderNo;

	// 卖家昵称
	private String sellerNickName;

	// 卖家ID
	private Long sellerId;

	// 卖家电话
	private String sellerContactPhone;

	// 订单状态
	private Integer orderStatus;

	// 订单显示状态
	private String orderStatusDesc;

	// 订单总价
	private String totalPrice;

	// 父类订单编号
	private String parentOrderNo;

	// 数量
	private Integer totalNum;

	// 支付方式
	private Integer payType;

	// 单项运费
	private String postage;

	/** 下单时间 */
	private long createTime;

	/** 是否提醒发货 */
	private Integer isRemindSendItem;

	private AddressInfoDto addressInfo;

	// 商品列表
	List<OrderItemInfoDto> orderItemList;

	private PageInfo pageInfo;

	public OrderInfoDto() {
	}

	public OrderInfoDto(String orderNo, Long sellerId, String totalPrice, String postage, Integer totalNum) {
		this.orderNo = orderNo;
		this.sellerId = sellerId;
		this.totalPrice = totalPrice;
		this.postage = postage;
		this.totalNum = totalNum;
	}

	public OrderInfoDto(Integer curPageNo, Integer pageSize) {
		this.pageInfo = new PageInfo(curPageNo, pageSize);
	}

	public OrderInfoDto(boolean success, String message) {
		super(success, message);
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerNickName() {
		return sellerNickName;
	}

	public void setSellerNickName(String sellerNickName) {
		this.sellerNickName = sellerNickName;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}

	public Integer getIsRemindSendItem() {
		return isRemindSendItem;
	}

	public void setIsRemindSendItem(Integer isRemindSendItem) {
		this.isRemindSendItem = isRemindSendItem;
	}

	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public List<OrderItemInfoDto> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItemInfoDto> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getSellerContactPhone() {
		return sellerContactPhone;
	}

	public void setSellerContactPhone(String sellerContactPhone) {
		this.sellerContactPhone = sellerContactPhone;
	}

	public AddressInfoDto getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfoDto addressInfo) {
		this.addressInfo = addressInfo;
	}

}
