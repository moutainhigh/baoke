package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 版本
 * 
 * @author wyh
 * @date 2018年6月10日 下午9:09:41
 *
 */
public class AppVersionDto extends BaseResultDto {

	private static final long serialVersionUID = -4673060754428850721L;

	private Long deviceId;// 设备ID

	private String channel;// 渠道

	private String versionNo;// 版本号

	private Integer hasNew; // 是否有新版本

	private Integer isForce;// 是否强制更新 0：否；1：是

	private String updateUrl;// 升级地址

	private String content;// 更新内容

	public AppVersionDto() {
	}

	public AppVersionDto(boolean success, String message) {
		super(success, message);
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getHasNew() {
		return hasNew;
	}

	public void setHasNew(Integer hasNew) {
		this.hasNew = hasNew;
	}

	public Integer getIsForce() {
		return isForce;
	}

	public void setIsForce(Integer isForce) {
		this.isForce = isForce;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
