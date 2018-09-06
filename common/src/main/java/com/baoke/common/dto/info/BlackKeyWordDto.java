package com.baoke.common.dto.info;

import java.util.Date;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 敏感词
 *
 * @author lcl
 * @date: 2018年7月25日 上午11:27:50
 */
public class BlackKeyWordDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	// 评论敏感词表id
	private Long blackKeyWordId;

	// 敏感词
	private String name;

	// 状态 0不可用 1可用
	private int status;

	// 添加、修改人
	private String bkUserName;

	// 添加、修改人id
	private Long bkUserId;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	private PageInfo pageInfo;

	public BlackKeyWordDto() {
	}

	public Long getBlackKeyWordId() {
		return blackKeyWordId;
	}

	public void setBlackKeyWordId(Long blackKeyWordId) {
		this.blackKeyWordId = blackKeyWordId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getBkUserId() {
		return bkUserId;
	}

	public void setBkUserId(Long bkUserId) {
		this.bkUserId = bkUserId;
	}

	public BlackKeyWordDto(boolean success, String message) {
		super(success, message);
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

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getBkUserName() {
		return bkUserName;
	}

	public void setBkUserName(String bkUserName) {
		this.bkUserName = bkUserName;
	}

}
