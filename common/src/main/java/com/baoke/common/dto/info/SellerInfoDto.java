package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 播主（卖家）信息
 * 
 * @author zjm
 * @date: 2018年6月21日 下午5:26:49
 */
public class SellerInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 卖家id */
	private Long sellerId;

	/** 主播名称 */
	private String sellerNickName;

	/** 播主头像 */
	private String sellerImgUrl;

	/** 被关注数(粉丝数) */
	private Integer beFocusNum;

	/** 是否关注 */
	private Integer isFocus;

	/** 总点赞数量 */
	private Integer beGreatNum;

	/** 状态：10：审核中；20：已通过；30：已拒绝 */
	private Integer status;

	/** 总视频数 */
	private Integer videoNum;

	/** 类目id */
	private String categoryIds;

	private PageInfo pageInfo;// 分页

	public SellerInfoDto() {
	}

	public SellerInfoDto(long sellerId) {
		this.sellerId = sellerId;
	}

	public SellerInfoDto(Long id, String sellerNickName, String sellerImgUrl) {
		this.sellerId = id;
		this.sellerNickName = sellerNickName;
		this.sellerImgUrl = sellerImgUrl;
	}

	public SellerInfoDto(Integer curPageNo, Integer pageSize) {
		this.pageInfo = new PageInfo(curPageNo, pageSize);
	}

	public SellerInfoDto(Long sellerId, String sellerImgUrl, String nickName, Integer beFocusNum, Integer isFocus,
			Integer videoNum) {
		super();
		this.sellerId = sellerId;
		this.sellerImgUrl = sellerImgUrl;
		this.sellerNickName = nickName;
		this.beFocusNum = beFocusNum;
		this.isFocus = isFocus;
		this.videoNum = videoNum;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getSellerImgUrl() {
		return sellerImgUrl;
	}

	public void setSellerImgUrl(String sellerImgUrl) {
		this.sellerImgUrl = sellerImgUrl;
	}

	public Integer getBeFocusNum() {
		return beFocusNum;
	}

	public void setBeFocusNum(Integer beFocusNum) {
		this.beFocusNum = beFocusNum;
	}

	public Integer getBeGreatNum() {
		return beGreatNum;
	}

	public void setBeGreatNum(Integer beGreatNum) {
		this.beGreatNum = beGreatNum;
	}

	public Integer getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(Integer isFocus) {
		this.isFocus = isFocus;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
