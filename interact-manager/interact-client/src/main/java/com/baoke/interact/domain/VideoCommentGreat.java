package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.interact.constant.GreatStatusEnum;

/**
 * 视频评论点赞关联domain
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:06:00
 */
public class VideoCommentGreat extends BaseDomain {
	private static final long serialVersionUID = 1L;

	private Long id;//
	private Long commentId;// 评论id
	private Long userId;// 用户id
	private Integer status;// 状态 0：未点赞；1：已点赞
	private Date createTime;//
	private Date updateTime;//

	public VideoCommentGreat() {
		super();
	}

	public VideoCommentGreat(Long commentId, Long userId) {
		this.commentId = commentId;
		this.userId = userId;
	}

	public VideoCommentGreat(long commentId, GreatStatusEnum videoGreatStatusEnum) {
		this.commentId = commentId;
		this.status = videoGreatStatusEnum.getCode();
	}

	public VideoCommentGreat(Long commentId, Long userId, GreatStatusEnum videoGreatStatusEnum) {
		this.commentId = commentId;
		this.userId = userId;
		this.status = videoGreatStatusEnum.getCode();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
}
