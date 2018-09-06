
package com.baoke.common.dto.info;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 视频详情信息
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:57:14
 */
public class VideoInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private Long videoId;

	/** 主播ID */
	private Long sellerId;

	/** 视频标题 */
	private String title;

	/** 视频地址 */
	private String url;

	/** 视频图片地址 */
	private String iconImgUrl;

	/** 点赞数量 */
	private Integer greatNum;

	/** 点赞日期 */
	private Long greatTime;

	/** 是否点赞 */
	private Integer isGreat;

	/** 审核结果描述 */
	private String auditResult;

	/** 标签 用;分隔 */
	private String tags;

	/** 评论量 */
	private Long commentNum;

	private Integer status;

	/** 播放量 */
	private Long playNum;

	/** 发布时间 */
	private Long startTime;

	private Long endTime;

	private SellerInfoDto sellerInfo;// 播主（卖家）信息

	/** 商品集合 */
	private List<ItemInfoDto> itemList;

	private PageInfo pageInfo;

	public VideoInfoDto() {
	}

	public VideoInfoDto(Long videoId) {
		super();
		this.videoId = videoId;
	}

	public VideoInfoDto(Long videoId, Long userId) {
		super();
		this.videoId = videoId;
		this.setUserId(userId);
	}

	public VideoInfoDto(Integer curPageNo, Integer pageSize) {
		this.pageInfo = new PageInfo(curPageNo, pageSize);
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconImgUrl() {
		return iconImgUrl;
	}

	public void setIconImgUrl(String iconImgUrl) {
		this.iconImgUrl = iconImgUrl;
	}

	public Integer getGreatNum() {
		return greatNum;
	}

	public void setGreatNum(Integer greatNum) {
		this.greatNum = greatNum;
	}

	public Integer getIsGreat() {
		return isGreat;
	}

	public void setIsGreat(Integer isGreat) {
		this.isGreat = isGreat;
	}

	public Long getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Long playNum) {
		this.playNum = playNum;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getGreatTime() {
		return greatTime;
	}

	public void setGreatTime(Long greatTime) {
		this.greatTime = greatTime;
	}

	public SellerInfoDto getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfoDto sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public List<ItemInfoDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemInfoDto> itemList) {
		this.itemList = itemList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
