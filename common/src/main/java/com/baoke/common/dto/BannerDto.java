package com.baoke.common.dto;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BannerConfigDto;

/**
 * banner的请求数据
 * 
 * @author ljj
 * @date: 2018年7月9日 下午2:38:41
 */
public class BannerDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private BannerConfigDto bannerConfigDto;
	private PageInfo pageInfo;

	public BannerDto(BannerConfigDto bannerConfigDto, PageInfo pageInfo) {
		super();
		this.bannerConfigDto = bannerConfigDto;
		this.pageInfo = pageInfo;
	}

	public BannerConfigDto getBannerConfigDto() {
		return bannerConfigDto;
	}

	public void setBannerConfigDto(BannerConfigDto bannerConfigDto) {
		this.bannerConfigDto = bannerConfigDto;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
