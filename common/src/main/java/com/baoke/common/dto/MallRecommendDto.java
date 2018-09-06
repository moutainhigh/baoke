package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.MallRecommendInfoDto;

/**
 * 商城－明星推荐
 * 
 * @author zdy
 * @date: 2018年7月4日 下午5:04:15
 */
public class MallRecommendDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<MallRecommendInfoDto> mallRecommendList;// banner集合

	private String title;// 显示标题

	public MallRecommendDto() {
		super();
	}

	public MallRecommendDto(List<MallRecommendInfoDto> mallRecommendList, String title) {
		super();
		this.mallRecommendList = mallRecommendList;
		this.title = title;
	}

	public List<MallRecommendInfoDto> getMallRecommendList() {
		return mallRecommendList;
	}

	public void setMallRecommendList(List<MallRecommendInfoDto> mallRecommendList) {
		this.mallRecommendList = mallRecommendList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
