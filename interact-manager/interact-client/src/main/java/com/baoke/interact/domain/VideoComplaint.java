package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.VideoComplaintInfoDto;
import com.baoke.interact.constant.VideoComplaintStatusEnum;

public class VideoComplaint extends BaseDomain {

	private static final long serialVersionUID = -6694303215981005675L;

	private Long id;

	/** 视频id */
	private Long videoId;

	/** 播主id */
	private Long sellerId;

	/** 举报者id */
	private Long userId;

	/** 举报内容 多个用;分隔 */
	private String content;

	/** 类型 0：非自填；1：自填 */
	private Integer type;

	/** 状态 0：举报中；1：举报成功；2：举报失败 */
	private Integer status;

	private Date createTime;

	private Date updateTime;

	public VideoComplaint() {
		super();
	}

	public VideoComplaint(Long id, VideoComplaintStatusEnum videoComplaintResultEnum) {
		super();
		this.id = id;
		this.status = videoComplaintResultEnum.getCode();
	}

	public VideoComplaint(Long videoId, Long userId) {
		super();
		this.videoId = videoId;
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public VideoComplaintInfoDto convert() {
		VideoComplaintInfoDto videoComplaintInfoDto = new VideoComplaintInfoDto();
		videoComplaintInfoDto.setComplaintId(this.id);
		videoComplaintInfoDto.setContent(this.content);
		videoComplaintInfoDto.setUserId(this.userId);
		videoComplaintInfoDto.setSellerId(this.sellerId);
		videoComplaintInfoDto.setVideoId(this.videoId);
		videoComplaintInfoDto.setStatus(this.status);
		videoComplaintInfoDto.setType(this.type);
		return videoComplaintInfoDto;
	}
}