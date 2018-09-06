package com.baoke.common.dto;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoInfoDto;

public class VideoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private VideoInfoDto videoInfo;

	private PageInfo pageInfo;// 分页

	public VideoDto() {
	}

	public VideoDto(VideoInfoDto videoInfo, PageInfo pageInfo) {
		super();
		this.videoInfo = videoInfo;
		this.pageInfo = pageInfo;
	}

	public VideoDto(Integer curPageNo, Integer pageSize) {
		PageInfo pageInfo = new PageInfo(curPageNo, pageSize);
		this.pageInfo = pageInfo;
	}

	public VideoInfoDto getVideoInfo() {
		return videoInfo;
	}

	public void setVideoInfo(VideoInfoDto videoInfo) {
		this.videoInfo = videoInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
