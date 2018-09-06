package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 热门商品dto
 * 
 * @author: wyj
 * @date: 2018年7月11日 上午10:33:19
 */
public class MallItemHotDto extends BaseResultDto {

	private static final long serialVersionUID = 294496293574637588L;

	private Long mallItemHotId;

	private ItemInfoDto itemInfo;

	public ItemInfoDto getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfoDto itemInfo) {
		this.itemInfo = itemInfo;
	}

	public Long getMallItemHotId() {
		return mallItemHotId;
	}

	public void setMallItemHotId(Long mallItemHotId) {
		this.mallItemHotId = mallItemHotId;
	}

}
