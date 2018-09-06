package com.baoke.api.domain.request.base;

import java.io.Serializable;

import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 基础请求参数
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午3:25:55
 */
public abstract class BaseRequestParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 加密的用户Id,只对当前登录用户做该处理 */
	private String userCode;

	/** 加密的设备ID,只对当前用户设备做该处理 */
	private String deviceCode;

	/** 服务端服务方法定义 */
	private String method;

	/** 请求时间戳 */
	private long timeStamp;

	/** 客户端IP，自动抓取 */
	private String ip;

	public abstract BaseDto convert() throws MainException;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
