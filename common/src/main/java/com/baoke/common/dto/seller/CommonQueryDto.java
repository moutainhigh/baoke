package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 
 * @author zjm
 * @date: 2018年7月6日 下午4:30:38
 */
public class CommonQueryDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	/** id列表 */
	private List<Long> ids;

	/** id */
	private Long videoId;

	/** 视频标题 */
	private String videoTitle;

	/** 商品标题 */
	private String itemTitle;

	/** 状态 */
	private Integer status;

	/** 昵称 */
	private String nickName;

	/** 禁言截止时间 */
	private Long userBannedEndTime;

	/** 禁言原因 */
	private String userBannedReason;

	/** 强制下线视频标识 */
	private boolean downLineVideoFlag = false;

	/** 内容 */
	private String content;

	/** 结果 */
	private String auditResult;

	/** 分页 */
	private PageInfo pageInfo;

	public CommonQueryDto(PageInfo pageInfo) {
		super();
		this.pageInfo = pageInfo;
	}

	public boolean isDownLineVideoFlag() {
		return downLineVideoFlag;
	}

	public void setDownLineVideoFlag(boolean downLineVideoFlag) {
		this.downLineVideoFlag = downLineVideoFlag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserBannedEndTime() {
		return userBannedEndTime;
	}

	public void setUserBannedEndTime(Long userBannedEndTime) {
		this.userBannedEndTime = userBannedEndTime;
	}

	public String getUserBannedReason() {
		return userBannedReason;
	}

	public void setUserBannedReason(String userBannedReason) {
		this.userBannedReason = userBannedReason;
	}

	public CommonQueryDto() {

	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
