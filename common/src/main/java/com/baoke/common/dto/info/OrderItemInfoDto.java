package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 子订单dto
 * 
 * @author zdy
 * @date: 2018年6月13日 下午6:08:07
 */
public class OrderItemInfoDto extends BaseResultDto {

	private static final long serialVersionUID = -2890111557669854634L;

	// id
	private Long orderItemId;

	private Long itemId;

	private Long itemDetailId;

	// 商品标题
	private String itemTitle;

	// 商品图片
	private String itemImgUrl;

	// 属性1名称
	private String itemAttr1Name;

	// 属性2名称
	private String itemAttr2Name;

	// 商品总价
	private String itemTotalPrice;

	// 商品单价
	private String itemPrice;

	// 总数量
	private Integer itemTotalNum;

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
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

	public String getItemAttr1Name() {
		return itemAttr1Name;
	}

	public void setItemAttr1Name(String itemAttr1Name) {
		this.itemAttr1Name = itemAttr1Name;
	}

	public String getItemAttr2Name() {
		return itemAttr2Name;
	}

	public void setItemAttr2Name(String itemAttr2Name) {
		this.itemAttr2Name = itemAttr2Name;
	}

	public String getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(String itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemTotalNum() {
		return itemTotalNum;
	}

	public void setItemTotalNum(Integer itemTotalNum) {
		this.itemTotalNum = itemTotalNum;
	}

}
