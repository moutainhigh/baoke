package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.interact.constant.GreatStatusEnum;

/**
 * 视频点赞domain
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:06:26
 */
public class VideoGreat extends BaseDomain {

	private static final long serialVersionUID = -1549238224296453149L;

	private Long id;

	/** 播主id */
	private Long sellerId;

	/** 视频id */
	private Long videoId;

	/** 用户id */
	private Long userId;

	/** 状态 0：取消点赞；1：点赞 */
	private Integer greatStatus;

	/** 点赞时间 */
	private Date greatTime;

	/** 不感兴趣 0：否；1：是 */
	private Integer delikeStatus;

	/** 不感兴趣时间 */
	private Date delikeTime;

	private Date createTime;

	private Date updateTime;

	public VideoGreat() {
		super();
	}

	public VideoGreat(Long userId) {
		super();
		this.userId = userId;
	}

	public VideoGreat(GreatStatusEnum greatStatus, Long sellerId, Long voidId) {
		super();
		this.sellerId = sellerId;
		this.greatStatus = greatStatus.getCode();
		this.videoId = voidId;
	}

	public VideoGreat(Long userId, GreatStatusEnum greatStatus, Integer delikeStatus) {
		super();
		this.userId = userId;
		this.greatStatus = greatStatus.getCode();
		this.delikeStatus = delikeStatus;
	}

	public VideoGreat(Long sellerId, Long videoId, Long userId, GreatStatusEnum greatStatus, Integer delikeStatus) {
		super();
		this.sellerId = sellerId;
		this.videoId = videoId;
		this.userId = userId;
		this.greatStatus = greatStatus.getCode();
		this.delikeStatus = delikeStatus;
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

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getGreatStatus() {
		return greatStatus;
	}

	public void setGreatStatus(Integer greatStatus) {
		this.greatStatus = greatStatus;
	}

	public Date getGreatTime() {
		return greatTime;
	}

	public void setGreatTime(Date greatTime) {
		this.greatTime = greatTime;
	}

	public Integer getDelikeStatus() {
		return delikeStatus;
	}

	public void setDelikeStatus(Integer delikeStatus) {
		this.delikeStatus = delikeStatus;
	}

	public Date getDelikeTime() {
		return delikeTime;
	}

	public void setDelikeTime(Date delikeTime) {
		this.delikeTime = delikeTime;
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