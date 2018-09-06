package com.baoke.common.domain.message;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 发送PUSH消息
 * 
 * @author wyh
 * @date 2018年7月14日 下午4:12:40
 *
 */
public class PushMessage extends BaseDomain {

	private static final long serialVersionUID = -6860270792408594254L;

	private Long userId;

	private Long deviceId;

	private String cid;

	private String title;

	private String content;

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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
