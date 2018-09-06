package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemInfoDto;

/**
 * 商品请求数据
 * 
 * @author ljj
 * @date: 2018年7月4日 下午5:03:00
 */
public class ItemDto extends BaseResultDto {

	private static final long serialVersionUID = -7401677991804216794L;

	/** 商品基础信息 */
	private ItemInfoDto itemInfo;

	/** 商品状态列表 */
	private List<Integer> statuses;

	/** 分页数据 */
	private PageInfo pageInfo;

	public ItemDto(ItemInfoDto itemInfo, PageInfo pageInfo, List<Integer> statuses) {
		super();
		this.itemInfo = itemInfo;
		this.pageInfo = pageInfo;
		this.statuses = statuses;
	}

	public ItemDto(Integer curPageNo, Integer pageSize) {
		PageInfo pageInfo = new PageInfo(curPageNo, pageSize);
		this.pageInfo = pageInfo;
	}

	public List<Integer> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Integer> statuses) {
		this.statuses = statuses;
	}

	public ItemInfoDto getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfoDto itemInfo) {
		this.itemInfo = itemInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
