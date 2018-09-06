package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.interact.constant.VideoComplaintDictTypeEnum;

public class VideoComplaintDict extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;

	/** 内容 */
	private String content;

	/** 状态 0:禁用 1：启用 */
	private Integer status;

	/** 类型 0：非自填；1：自填 */
	private Integer type;

	/** 排序值 */
	private Integer sort;

	private Date createTime;

	private Date updateTime;

	public VideoComplaintDict() {

	}

	public VideoComplaintDict(BooleanEnum booleanEnum) {
		this.status = booleanEnum.getCode();
	}

	public VideoComplaintDict(VideoComplaintDictTypeEnum type, BooleanEnum booleanEnum) {
		if (null != booleanEnum) {
			this.status = booleanEnum.getCode();
		}
		if (null != type) {
			this.type = type.getCode();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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