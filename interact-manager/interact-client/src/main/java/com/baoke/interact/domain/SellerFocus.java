package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.interact.constant.SellerFocusStatusEnum;

/**
 * 关注商户domain
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:04:47
 */
public class SellerFocus extends BaseDomain {

	private static final long serialVersionUID = 3103450136616635241L;

	private Long id;

	/** 主播id */
	private Long sellerId;

	/** 用户id */
	private Long userId;

	/** 状态 0：取消关注；1：关注 */
	private Integer status;

	private Date createTime;

	private Date updateTime;

	public SellerFocus() {
		super();
	}

	public SellerFocus(Long userId, SellerFocusStatusEnum sellerFocusEnum, Long sellerId) {
		this.userId = userId;
		this.sellerId = sellerId;
		if (null != sellerFocusEnum) {
			this.status = sellerFocusEnum.getCode();
		}
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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