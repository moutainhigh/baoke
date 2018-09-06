package com.baoke.item.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.item.constant.VideoStatusEnum;

/**
 * 视频
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:32:47
 */
public class VideoInfo extends BaseDomain {

	private static final long serialVersionUID = 1L;
	/** */
	private Long id;

	/** 主播ID */
	private Long sellerId;

	/** 视频标题 */
	private String title;

	/** 视频地址 */
	private String url;

	/** 视频图片地址 */
	private String iconImgUrl;

	/** 状态 0：未上线；1;已上线；2：下线 */
	private Integer status;

	/** 审核结果描述 */
	private String auditResult;

	/** 标签 用;分隔 */
	private String tags;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	/** */
	private Date createTime;

	/** */
	private Date updateTime;

	public VideoInfo() {
	}

	public VideoInfo(VideoStatusEnum videoStatusEnum) {
		super();
		if (null != videoStatusEnum) {
			this.status = videoStatusEnum.getCode();
		}
	}

	public VideoInfo(Long id) {
		this.id = id;
	}

	public VideoInfo(Long id, Long sellerId) {
		this.id = id;
		this.sellerId = sellerId;
	}

	public VideoInfo(Long sellerId, VideoStatusEnum videoStatusEnum) {
		this.sellerId = sellerId;
		if (null != videoStatusEnum) {
			this.status = videoStatusEnum.getCode();
		}
	}

	public VideoInfo(Long sellerId, VideoStatusEnum videoStatusEnum, String title) {
		this.sellerId = sellerId;
		if (null != videoStatusEnum) {
			this.status = videoStatusEnum.getCode();
		}
		this.title = title;
	}

	public VideoInfoDto convert() {
		VideoInfoDto videoInfoDto = new VideoInfoDto();
		videoInfoDto.setSellerId(this.sellerId);
		videoInfoDto.setVideoId(this.id);
		videoInfoDto.setIconImgUrl(this.iconImgUrl);
		videoInfoDto.setTitle(this.title);
		videoInfoDto.setUrl(this.url);
		videoInfoDto.setStatus(this.status);
		videoInfoDto.setAuditResult(this.auditResult);
		videoInfoDto.setTags(this.tags);
		if (null != this.startTime) {
			videoInfoDto.setStartTime(this.startTime.getTime());
		}
		return videoInfoDto;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
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

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
