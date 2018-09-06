package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BannerConfigDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 查询首页推荐
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:50:31
 */
public class RecommendHomeDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	// banner集合
	private List<BannerConfigDto> bannerList;

	// 视频集合
	private List<VideoInfoDto> videoList;

	private PageInfo pageInfo;// 分页

	public RecommendHomeDto() {
	}

	public RecommendHomeDto(List<VideoInfoDto> videoList) {
		super();
		this.videoList = videoList;
	}

	public RecommendHomeDto(List<BannerConfigDto> bannerList, List<VideoInfoDto> videoList, PageInfo pageInfo) {
		this.bannerList = bannerList;
		this.videoList = videoList;
		this.pageInfo = pageInfo;
	}

	public RecommendHomeDto(boolean success, String message, List<BannerConfigDto> bannerList,
			List<VideoInfoDto> videoList, PageInfo pageInfo) {
		super(success, message);
		this.bannerList = bannerList;
		this.videoList = videoList;
		this.pageInfo = pageInfo;
	}

	public List<BannerConfigDto> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<BannerConfigDto> bannerList) {
		this.bannerList = bannerList;
	}

	public List<VideoInfoDto> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<VideoInfoDto> videoList) {
		this.videoList = videoList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
