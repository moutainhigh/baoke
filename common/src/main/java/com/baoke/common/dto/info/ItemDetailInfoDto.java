package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 商品明细表
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:31:57
 */
public class ItemDetailInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 商品id */
	private Long itemId;

	/** 商品详情id */
	private Long itemDetailId;

	/** 属性1 code */
	private String attr1Code;

	/** 属性2 code */
	private String attr2Code;

	/** 属性1 name */
	private String attr1Name;

	/** 属性2 name */
	private String attr2Name;

	/** 剩余库存 */
	private Integer totalNum;

	/** 已售数量 */
	private Integer saleNum;

	/** 市场价格 */
	private String marketPrice;

	/** 销售价格 */
	private String price;

	private Integer status;

	/** 发布时间 */
	private Long startTime;

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public ItemDetailInfoDto() {
		super();
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

	public String getAttr1Code() {
		return attr1Code;
	}

	public void setAttr1Code(String attr1Code) {
		this.attr1Code = attr1Code;
	}

	public String getAttr2Code() {
		return attr2Code;
	}

	public void setAttr2Code(String attr2Code) {
		this.attr2Code = attr2Code;
	}

	public String getAttr1Name() {
		return attr1Name;
	}

	public void setAttr1Name(String attr1Name) {
		this.attr1Name = attr1Name;
	}

	public String getAttr2Name() {
		return attr2Name;
	}

	public void setAttr2Name(String attr2Name) {
		this.attr2Name = attr2Name;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
