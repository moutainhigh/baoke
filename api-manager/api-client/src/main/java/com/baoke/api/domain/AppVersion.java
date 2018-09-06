package com.baoke.api.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 版本
 * 
 * @author wyh
 * @date 2018年6月10日 下午9:09:41
 *
 */
public class AppVersion extends BaseDomain {

	private static final long serialVersionUID = -4673060754428850721L;

	private Long id;

	private String versionNo;// 版本号

	private String channel;// 渠道

	private Integer isForce;// 是否强制更新 0：否；1：是

	private String content;// 更新内容

	private Date startTime;// 启用时间

	private String updateUrl;// 升级地址

	private Date createTime;

	private Date updateTime;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsForce() {
		return isForce;
	}

	public void setIsForce(Integer isForce) {
		this.isForce = isForce;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
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

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
