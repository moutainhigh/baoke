package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 举报
 * 
 * @author zjm
 * @date: 2018年6月28日 上午11:02:13
 */
public class VideoComplaintInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Long complaintId;

	/** 视频id */
	private Long videoId;

	/** 视频标题 */
	private String title;

	/** 播主id */
	private Long sellerId;

	/** 举报者id */
	private Long userId;

	/** 举报者昵称 */
	private String nickName;

	/** 举报内容 多个用;分隔 */
	private String content;

	/** 类型 0：非自填；1：自填 */
	private Integer type;

	/** 状态 0：举报中；1：举报成功；2：举报失败 */
	private Integer status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
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
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
