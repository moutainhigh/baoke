package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.MallRecommendDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 明星推荐列表
 * 
 * @author: wyj
 * @date: 2018年7月12日 下午4:54:20
 */
public class MallRecommendListDto extends BaseResultDto {

	private static final long serialVersionUID = -110526442114494560L;

	private List<MallRecommendDto> mallRecommendList;

	private PageInfo pageInfo;

	public List<MallRecommendDto> getMallRecommendList() {
		return mallRecommendList;
	}

	public void setMallRecommendList(List<MallRecommendDto> mallRecommendList) {
		this.mallRecommendList = mallRecommendList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
