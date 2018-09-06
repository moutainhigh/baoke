/** 
 * Project Name:api-client 
 * File Name:UserInfo.java 
 * Package Name:com.baoke.api.domain 
 * Date:2018年6月4日下午7:40:16 
 * Copyright (c) 2018, zjm All Rights Reserved. 
 * 
*/

package com.baoke.common.dto.info;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 商品详情 Date: 2018年6月5日
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:47:41
 */
public class ItemInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 商品ID */
	private Long itemId;

	/** 商品标题 */
	private String title;

	/** 主播ID */
	private Long sellerId;

	/** 品牌 */
	private String brand;

	/** 小图标地址 */
	private String mainImgUrl;

	/** 市场价格 */
	private String marketPrice;

	/** 销售价格 */
	private String price;

	/** 运费 */
	private String postage;

	/** 附加邮费地区code(省)(多个逗号分隔) */
	private String addPostageAreaCodes;

	/** 附加邮费地区(省)(多个逗号分隔) */
	private String addPostageAreaNames;

	/** 附加邮费 */
	private String addPostage;

	/** 详情描述 */
	private String desc;

	private Integer status;

	/** 排序值 */
	private Integer sort;

	/** 关联视频数 */
	private Integer videoNum;

	/** 审核结果描述 */
	private String auditResult;

	private List<ItemDetailInfoDto> itemDetailList;// 商品详情

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public String getAddPostageAreaCodes() {
		return addPostageAreaCodes;
	}

	public void setAddPostageAreaCodes(String addPostageAreaCodes) {
		this.addPostageAreaCodes = addPostageAreaCodes;
	}

	public String getAddPostageAreaNames() {
		return addPostageAreaNames;
	}

	public void setAddPostageAreaNames(String addPostageAreaNames) {
		this.addPostageAreaNames = addPostageAreaNames;
	}

	public String getAddPostage() {
		return addPostage;
	}

	public void setAddPostage(String addPostage) {
		this.addPostage = addPostage;
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

	public List<ItemDetailInfoDto> getItemDetailList() {
		return itemDetailList;
	}

	public void setItemDetailList(List<ItemDetailInfoDto> itemDetailList) {
		this.itemDetailList = itemDetailList;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
