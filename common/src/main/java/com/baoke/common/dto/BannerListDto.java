package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BannerConfigDto;

/**
 * banner分页返回数据
 * 
 * @author ljj
 * @date: 2018年7月9日 下午1:09:43
 */
public class BannerListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<BannerConfigDto> bannerConfigList;

	private PageInfo pageInfo;

	public List<BannerConfigDto> getBannerConfigList() {
		return bannerConfigList;
	}

	public void setBannerConfigList(List<BannerConfigDto> bannerConfigList) {
		this.bannerConfigList = bannerConfigList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
