package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

public class DeviceUserRelation extends BaseDomain {
	private static final long serialVersionUID = -3943885857652771063L;

	private Long id;

	private Long deviceId;

	private Long userId;

	private Date createTime;

	private Date updateTime;

	public DeviceUserRelation() {
	}

	public DeviceUserRelation(Long userId) {
		this.userId = userId;
	}

	public DeviceUserRelation(Long deviceId, Long userId) {
		this.deviceId = deviceId;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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