package com.baoke.item.domain;

import java.util.Date;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.domain.base.BaseDomain;

/**
 * 人气热门商品推荐
 * 
 * @author zdy
 * @date: 2018年7月4日 下午7:30:30
 */
public class MallItemHot extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long id;//
	private Long itemId;// 商品id
	private Integer sort;// 排序， 大的排前面
	private Integer status;// 状态 ： 0：不可用；1：可用
	private Date createTime;//
	private Date updateTime;//

	public MallItemHot() {
		super();
	}

	public MallItemHot(Long itemId, Integer sort, Integer status) {
		super();
		this.itemId = itemId;
		this.sort = sort;
		this.status = status;
	}

	public MallItemHot(BooleanEnum booleanEnum) {
		super();
		this.status = booleanEnum.getCode();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
