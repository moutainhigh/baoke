package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.MallItemHotDto;

/**
 * 热门商品列表
 * 
 * @author: wyj
 * @date: 2018年7月12日 下午6:23:29
 */
public class MallItemHotListDto extends BaseResultDto {

	private static final long serialVersionUID = 4253622952746601065L;

	private List<MallItemHotDto> mallItemHotList;

	private PageInfo pageInfo;

	public List<MallItemHotDto> getMallItemHotList() {
		return mallItemHotList;
	}

	public void setMallItemHotList(List<MallItemHotDto> mallItemHotList) {
		this.mallItemHotList = mallItemHotList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
