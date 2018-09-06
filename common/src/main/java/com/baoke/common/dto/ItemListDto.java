package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemInfoDto;

/**
 * 商品信息dto
 * 
 * @author ljj
 * @date: 2018年7月3日 下午5:33:51
 */
public class ItemListDto extends BaseResultDto {

	private static final long serialVersionUID = 8151699406532360391L;

	private List<ItemInfoDto> itemList;

	private PageInfo pageInfo;

	public ItemListDto() {
		super();
	}

	public ItemListDto(List<ItemInfoDto> itemList, PageInfo pageInfo) {
		super();
		this.itemList = itemList;
		this.pageInfo = pageInfo;
	}

	public List<ItemInfoDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemInfoDto> itemList) {
		this.itemList = itemList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
