package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 热门商品dto
 * 
 * @author: wyj
 * @date: 2018年7月11日 上午10:33:19
 */
public class MallItemHotInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 294496293574637588L;

	private Long mallItemHotId;

	private Long itemId;

	private Integer sort;

	private Integer status;

	public Long getMallItemHotId() {
		return mallItemHotId;
	}

	public void setMallItemHotId(Long mallItemHotId) {
		this.mallItemHotId = mallItemHotId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

}
