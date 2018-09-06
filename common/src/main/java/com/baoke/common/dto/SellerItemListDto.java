package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemInfoDto;

/**
 * 播主（卖家）商品信息
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:50:52
 */
public class SellerItemListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<ItemInfoDto> itemList;

	private PageInfo pageInfo;// 分页

	public SellerItemListDto() {
	}

	public SellerItemListDto(Long userId, Long deviceId) {
		super(userId, deviceId);
	}

	public SellerItemListDto(boolean success, String message, List<ItemInfoDto> itemList) {
		super(success, message);
		this.itemList = itemList;
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
