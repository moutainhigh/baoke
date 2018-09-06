package com.baoke.item.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.util.MoneyUtil;
import com.baoke.item.constant.ItemStatusEnum;

/**
 * 商品表
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:32:19
 */
public class ItemInfo extends BaseDomain {

	private static final long serialVersionUID = -5390231970090063302L;

	/** */
	private Long id;

	/** 商品标题 */
	private String title;

	/** 主播ID */
	private Long sellerId;

	/** 品牌 */
	private String brand;

	/** 小图标地址 */
	private String mainImgUrl;

	/** 市场价格 */
	private Integer marketPrice;

	/** 销售价格 */
	private Integer price;

	/** 运费 */
	private Integer postage;

	/** 附加地区 */
	private String addAreaCodes;

	/** 附加地区邮费 */
	private Integer addAreaPostage;

	/** 详情描述 */
	private String desc;

	/** 状态 0：不可用；1：可用 2：待上架 3：已售罄 4：可预订 */
	private Integer status;

	/** 审核结果描述 */
	private String auditResult;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	/** */
	private Date createTime;

	/** */
	private Date updateTime;

	public ItemInfo() {
	}

	public ItemInfo(Long id) {
		super();
		this.id = id;
	}

	public ItemInfo(ItemStatusEnum itemStatusEnum) {
		this.status = itemStatusEnum.getCode();
	}

	public ItemInfo(Long sellerId, ItemStatusEnum itemStatusEnum) {
		this.sellerId = sellerId;
		if (null != itemStatusEnum) {
			this.status = itemStatusEnum.getCode();
		}
	}

	public ItemInfo(ItemStatusEnum itemStatusEnum, String auditResult) {
		this.status = itemStatusEnum.getCode();
		this.auditResult = auditResult;
	}

	public ItemInfo(Long sellerId, String title, ItemStatusEnum itemStatusEnum) {
		this.sellerId = sellerId;
		this.title = title;
		if (null != itemStatusEnum) {
			this.status = itemStatusEnum.getCode();
		}
	}

	public ItemInfoDto convert() {
		ItemInfoDto itemInfoDto = new ItemInfoDto();
		itemInfoDto.setItemId(this.id);
		itemInfoDto.setBrand(this.brand);
		itemInfoDto.setMarketPrice(MoneyUtil.changeF2Y(this.marketPrice));
		itemInfoDto.setMainImgUrl(this.mainImgUrl);
		itemInfoDto.setPrice(MoneyUtil.changeF2Y(this.price));
		itemInfoDto.setTitle(this.title);
		return itemInfoDto;
	}

	public ItemInfoDto convert(List<ItemDetailInfo> itemDetailInfoList) {
		ItemInfoDto itemInfoDto = new ItemInfoDto();
		itemInfoDto.setSellerId(this.sellerId);
		itemInfoDto.setItemId(this.id);
		itemInfoDto.setBrand(this.brand);
		itemInfoDto.setDesc(this.desc);
		itemInfoDto.setMarketPrice(MoneyUtil.changeF2Y(this.marketPrice));
		itemInfoDto.setMainImgUrl(this.mainImgUrl);
		itemInfoDto.setPrice(MoneyUtil.changeF2Y(this.price));
		itemInfoDto.setStatus(this.status);
		itemInfoDto.setTitle(this.title);
		itemInfoDto.setPostage(MoneyUtil.changeF2Y(this.postage));
		itemInfoDto.setAddPostage(MoneyUtil.changeF2Y(this.addAreaPostage));
		itemInfoDto.setAddPostageAreaCodes(this.addAreaCodes);
		itemInfoDto.setAuditResult(this.auditResult);

		if (null != itemDetailInfoList) {
			List<ItemDetailInfoDto> itemDetailInfoDtoList = new ArrayList<ItemDetailInfoDto>();
			for (ItemDetailInfo itemDetailInfo : itemDetailInfoList) {
				itemDetailInfoDtoList.add(itemDetailInfo.convert());
			}
			itemInfoDto.setItemDetailList(itemDetailInfoDtoList);
		}
		return itemInfoDto;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getAddAreaCodes() {
		return addAreaCodes;
	}

	public void setAddAreaCodes(String addAreaCodes) {
		this.addAreaCodes = addAreaCodes;
	}

	public Integer getAddAreaPostage() {
		return addAreaPostage;
	}

	public void setAddAreaPostage(Integer addAreaPostage) {
		this.addAreaPostage = addAreaPostage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMainImgUrl() {
		return mainImgUrl;
	}

	public void setMainImgUrl(String mainImgUrl) {
		this.mainImgUrl = mainImgUrl;
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

	public Integer getPostage() {
		return postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
