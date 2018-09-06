package com.baoke.item.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 视频商品关联表
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:33:01
 */
public class VideoItemRelation extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;

	/** 视频id */
	private Long videoId;

	/** 播主id */
	private Long sellerId;

	/** 商品id */
	private Long itemId;

	/**   */
	private Date createTime;

	/**   */
	private Date updateTime;

	public VideoItemRelation() {
	}

	public VideoItemRelation(Long videoId) {
		super();
		this.videoId = videoId;
	}

	public VideoItemRelation(Long videoId, Long itemId) {
		super();
		this.videoId = videoId;
		this.itemId = itemId;
	}

	public VideoItemRelation(Long videoId, Long sellerId, Long itemId) {
		super();
		this.videoId = videoId;
		this.sellerId = sellerId;
		this.itemId = itemId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVideoId() {
		return this.videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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
