package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 播主（卖家）视频信息
 * 
 * @author zdy
 * @date: 2018年6月27日 下午7:11:59
 */
public class SellerVideoListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<VideoInfoDto> videoList;// 视频集合

	private PageInfo pageInfo;// 分页

	public SellerVideoListDto() {

	}

	public SellerVideoListDto(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public SellerVideoListDto(List<VideoInfoDto> videoList) {
		this.videoList = videoList;
	}

	public SellerVideoListDto(PageInfo pageInfo, List<VideoInfoDto> videoList) {
		this.pageInfo = pageInfo;
		this.videoList = videoList;
	}

	public SellerVideoListDto(boolean success, String message, List<VideoInfoDto> videoList, PageInfo pageInfo) {
		super(success, message);
		this.videoList = videoList;
		this.pageInfo = pageInfo;
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
