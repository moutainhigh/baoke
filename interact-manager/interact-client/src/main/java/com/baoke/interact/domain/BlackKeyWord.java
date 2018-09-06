package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.BlackKeyWordDto;

/**
 * 评论敏感词
 *
 * @author lcl
 * @date: 2018年7月25日 上午10:54:29
 */
public class BlackKeyWord extends BaseDomain {

	private static final long serialVersionUID = -4708674822231995473L;

	private Long id;

	// 敏感词
	private String name;

	// 状态 0 不可用 1 可用
	private Integer status;

	// 添加、修改人
	private Long bkUserId;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public BlackKeyWord() {

	}

	public BlackKeyWord(Long id, BooleanEnum booleanEnum) {
		this.id = id;
		if (null != booleanEnum) {
			this.status = booleanEnum.getCode();
		}
	}

	public BlackKeyWord(Long id, String name, BooleanEnum booleanEnum) {
		this.id = id;
		this.name = name;
		if (null != booleanEnum) {
			this.status = booleanEnum.getCode();
		}
	}

	public BlackKeyWordDto convert() {
		BlackKeyWordDto blackKeyWordDto = new BlackKeyWordDto();
		blackKeyWordDto.setBlackKeyWordId(this.id);
		blackKeyWordDto.setName(this.name);
		blackKeyWordDto.setStatus(this.status);
		blackKeyWordDto.setBkUserId(this.bkUserId);
		blackKeyWordDto.setCreateTime(this.createTime);
		blackKeyWordDto.setUpdateTime(this.updateTime);
		return blackKeyWordDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBkUserId() {
		return bkUserId;
	}

	public void setBkUserId(Long bkUserId) {
		this.bkUserId = bkUserId;
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
