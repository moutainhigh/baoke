package com.baoke.item.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.CategoryDictDto;

public class CategoryDict extends BaseDomain {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parentId;

	private String name;

	private Integer level;

	private Integer sort;

	private Integer status;

	private Date createTime;

	public CategoryDict() {
		super();
	}

	public CategoryDictDto convert() {
		CategoryDictDto categoryDictDto = new CategoryDictDto();
		categoryDictDto.setCategoryId(this.id);
		categoryDictDto.setCategoryName(this.name);
		return categoryDictDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}