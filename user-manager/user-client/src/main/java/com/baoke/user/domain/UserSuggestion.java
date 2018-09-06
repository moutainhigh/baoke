package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

public class UserSuggestion extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;//
	private Long userId;// 投诉与建议人
	private String content;// 内容
	private Date createTime;//
	private Date updateTime;//

	public UserSuggestion() {
		super();
	}

	public UserSuggestion(Long userId, String content) {
		super();
		this.userId = userId;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
