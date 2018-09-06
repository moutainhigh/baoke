package com.baoke.item.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 商品字典表
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:32:31
 */
public class ItemPropDict extends BaseDomain {

	private static final long serialVersionUID = -8472399736217899496L;

	// 属性id
	private Long id;

	// 属性名称
	private String name;

	// 父属性id
	private Long parentId;

	// 状态
	private Integer status;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public ItemPropDict() {
		super();
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
