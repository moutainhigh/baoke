package com.baoke.item.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.util.MoneyUtil;
import com.baoke.item.constant.ItemDetailStatusEnum;

/**
 * 商品明细表
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:31:57
 */
public class ItemDetailInfo extends BaseDomain {

	private static final long serialVersionUID = 1L;

	/** id */
	private Long id;

	/** 商品id */
	private Long itemId;

	/** 商家的id */
	private Long sellerId;

	/** 属性1 */
	private Long attr1;

	/** 属性2 */
	private Long attr2;

	/** 剩余库存 */
	private Integer totalNum;

	/** 已售数量 */
	private Integer saleNum;

	/** 市场价格 */
	private Integer marketPrice;

	/** 销售价格 */
	private Integer price;

	private Integer status;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	/** */
	private Date createTime;

	/** */
	private Date updateTime;

	public ItemDetailInfo() {
		super();
	}

	public ItemDetailInfo(Long itemId, ItemDetailStatusEnum itemDetailStatusEnum) {
		super();
		this.itemId = itemId;
		if (null != itemDetailStatusEnum) {
			this.status = itemDetailStatusEnum.getCode();
		}
	}

	public ItemDetailInfoDto convert() {
		ItemDetailInfoDto itemDetailInfoDto = new ItemDetailInfoDto();
		itemDetailInfoDto.setItemDetailId(this.id);
		itemDetailInfoDto.setItemId(this.itemId);
		itemDetailInfoDto.setTotalNum(this.totalNum);
		itemDetailInfoDto.setSaleNum(this.saleNum);
		itemDetailInfoDto.setMarketPrice(MoneyUtil.changeF2Y(this.marketPrice));
		itemDetailInfoDto.setPrice(MoneyUtil.changeF2Y(this.price));
		itemDetailInfoDto.setStatus(this.status);
		itemDetailInfoDto.setAttr1Code("100");
		itemDetailInfoDto.setAttr1Name("通用");
		itemDetailInfoDto.setAttr2Code("100");
		itemDetailInfoDto.setAttr2Name("通用");
		if (null != this.startTime) {
			itemDetailInfoDto.setStartTime(this.startTime.getTime());
		}
		return itemDetailInfoDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getAttr1() {
		return attr1;
	}

	public void setAttr1(Long attr1) {
		this.attr1 = attr1;
	}

	public Long getAttr2() {
		return attr2;
	}

	public void setAttr2(Long attr2) {
		this.attr2 = attr2;
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

	public Integer getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Integer marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
