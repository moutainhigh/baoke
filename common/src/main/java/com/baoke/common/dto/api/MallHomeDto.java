package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.MallRecommendDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.ItemInfoDto;

/**
 * 商城－查询商城首页
 * 
 * @author zdy
 * @date: 2018年7月4日 下午5:00:33
 */
public class MallHomeDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<ItemInfoDto> itemHotList;// 人气热门商品信息

	private List<ItemInfoDto> itemNewList;// 新品信息

	private List<MallRecommendDto> recommendList;// 本周明星推荐

	public List<ItemInfoDto> getItemHotList() {
		return itemHotList;
	}

	public MallHomeDto() {
		super();
	}

	public MallHomeDto(boolean success, String message) {
		super(success, message);
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

}
