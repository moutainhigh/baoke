package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.OrderItemInfoDto;
import com.baoke.common.util.MoneyUtil;

/**
 * 子订单实体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:05:33
 */
public class OrderItem extends BaseDomain {

	private static final long serialVersionUID = -1268212754009166822L;

	// 子订单表主键id
	private Long id;

	// 用户id
	private Long userId;

	// 卖家id
	private Long sellerId;

	// 父订单号
	private String parentOrderNo;

	// 订单号
	private String orderNo;

	// 商品id
	private Long itemId;

	// 商品明细id
	private Long itemDetailId;

	// 商品标题
	private String itemTitle;

	// 商品图片
	private String itemImgUrl;

	// 属性一
	private Long itemAttr1Code;

	// 属性一名称
	private String itemAttr1Name;

	// 属性二
	private Long itemAttr2Code;

	// 属性二名称
	private String itemAttr2Name;

	// 总价
	private Integer itemTotalPrice;

	// 商品价格
	private Integer itemPrice;

	// 商品数量
	private Integer itemTotalNum;

	// 运费
	private Integer postage;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public OrderItem() {
	}

	public OrderItem(String orderNo, Long sellerId) {
		super();
		this.sellerId = sellerId;
		this.orderNo = orderNo;
	}

	public OrderItem(Long userId, String parentOrderNo) {
		this.userId = userId;
		this.parentOrderNo = parentOrderNo;
	}

	public OrderItem(Long userId, String parentOrderNo, Long itemId, Long itemDetailId) {
		super();
		this.userId = userId;
		this.parentOrderNo = parentOrderNo;
		this.itemId = itemId;
		this.itemDetailId = itemDetailId;
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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemDetailId() {
		return itemDetailId;
	}

	public void setItemDetailId(Long itemDetailId) {
		this.itemDetailId = itemDetailId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemImgUrl() {
		return itemImgUrl;
	}

	public void setItemImgUrl(String itemImgUrl) {
		this.itemImgUrl = itemImgUrl;
	}

	public Long getItemAttr1Code() {
		return itemAttr1Code;
	}

	public void setItemAttr1Code(Long itemAttr1Code) {
		this.itemAttr1Code = itemAttr1Code;
	}

	public String getItemAttr1Name() {
		return itemAttr1Name;
	}

	public void setItemAttr1Name(String itemAttr1Name) {
		this.itemAttr1Name = itemAttr1Name;
	}

	public Long getItemAttr2Code() {
		return itemAttr2Code;
	}

	public void setItemAttr2Code(Long itemAttr2Code) {
		this.itemAttr2Code = itemAttr2Code;
	}

	public String getItemAttr2Name() {
		return itemAttr2Name;
	}

	public void setItemAttr2Name(String itemAttr2Name) {
		this.itemAttr2Name = itemAttr2Name;
	}

	public Integer getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(Integer itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemTotalNum() {
		return itemTotalNum;
	}

	public void setItemTotalNum(Integer itemTotalNum) {
		this.itemTotalNum = itemTotalNum;
	}

	public Integer getPostage() {
		return postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
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

	public OrderItemInfoDto convert() {
		OrderItemInfoDto orderItemInfoDto = new OrderItemInfoDto();
		orderItemInfoDto.setOrderItemId(this.id);
		orderItemInfoDto.setUserId(this.userId);
		orderItemInfoDto.setItemId(this.itemId);
		orderItemInfoDto.setItemDetailId(this.itemDetailId);
		orderItemInfoDto.setItemTitle(this.itemTitle);
		orderItemInfoDto.setItemImgUrl(this.itemImgUrl);
		orderItemInfoDto.setItemAttr1Name("通用");
		orderItemInfoDto.setItemAttr2Name("通用");
		orderItemInfoDto.setItemTotalNum(this.itemTotalNum);
		orderItemInfoDto.setItemPrice(MoneyUtil.changeF2Y(this.itemPrice));
		orderItemInfoDto.setItemTotalPrice(MoneyUtil.changeF2Y(this.itemTotalPrice));
		return orderItemInfoDto;
	}

}
