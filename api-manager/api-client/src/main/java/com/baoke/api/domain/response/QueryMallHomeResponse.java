package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.MallRecommendDto;
import com.baoke.common.dto.api.MallHomeDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.ItemInfoDto;

/**
 * 商城－查询商城首页
 * 
 * @author zdy
 * @date: 2018年7月4日 下午5:24:28
 */
public class QueryMallHomeResponse extends BaseResponseParam {
	private static final long serialVersionUID = 1L;

	private List<ItemInfoDto> itemHotList;// 人气热门商品信息

	private List<ItemInfoDto> itemNewList;// 新品信息

	private List<MallRecommendDto> recommendList;// 本周明星推荐

	public List<ItemInfoDto> getItemHotList() {
		return itemHotList;
	}

	public void setItemHotList(List<ItemInfoDto> itemHotList) {
		this.itemHotList = itemHotList;
	}

	public List<ItemInfoDto> getItemNewList() {
		return itemNewList;
	}

	public void setItemNewList(List<ItemInfoDto> itemNewList) {
		this.itemNewList = itemNewList;
	}

	public List<MallRecommendDto> getRecommendList() {
		return recommendList;
	}

	public void setRecommendList(List<MallRecommendDto> recommendList) {
		this.recommendList = recommendList;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		MallHomeDto mallHomeDto = (MallHomeDto) baseResultDto;
		this.itemHotList = mallHomeDto.getItemHotList();
		this.itemNewList = mallHomeDto.getItemNewList();
		this.recommendList = mallHomeDto.getRecommendList();
		return this;
	}
}
