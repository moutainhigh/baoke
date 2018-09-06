package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.RecommendHomeDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.BannerConfigDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 查询首页推荐列表响应实体类
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:17:46
 */
public class QueryRecommendHomeResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	// banner集合
	private List<BannerConfigDto> bannerList;

	// 视频集合
	private List<VideoInfoDto> videoList;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

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

	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {

		if (baseResultDto == null) {
			return this;
		}

		RecommendHomeDto recommendHomeDto = (RecommendHomeDto) baseResultDto;
		this.bannerList = recommendHomeDto.getBannerList();
		this.videoList = recommendHomeDto.getVideoList();
		if (recommendHomeDto.getPageInfo() != null) {
			this.curPageNo = recommendHomeDto.getPageInfo().getCurrent();
			this.pageSize = recommendHomeDto.getPageInfo().getPageSize();
		}
		return this;
	}

}
