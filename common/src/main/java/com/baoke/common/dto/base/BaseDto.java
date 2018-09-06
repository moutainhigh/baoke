package com.baoke.common.dto.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 所有DTO的顶级父类
 */
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 4092344837536789908L;

	/** 当前登陆用户ID */
	private Long userId;

	// /** 当前登陆用户CODE */
	// private String userCode;

	/** 当前设备ID */
	private Long deviceId;

	// /** 当前设备CODE */
	// private String deviceCode;

	public BaseDto() {
	}

	public BaseDto(Long userId, Long deviceId) {
		this.userId = userId;
		this.deviceId = deviceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
