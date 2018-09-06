package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询视频详情入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:29:50
 */
public class QueryItemDetailRequest extends BaseRequestParam {
	private static final long serialVersionUID = -2455517648055390074L;

	private Long itemId;// 商品Id

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Override
	public BaseDto convert() throws MainException {
		ItemInfoDto itemInfoDto = new ItemInfoDto();
		itemInfoDto.setItemId(this.itemId);
		return itemInfoDto;
	}
}
