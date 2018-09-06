package com.baoke.common.dto.wechat;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 视频信息列表
 * 
 * @author: wyj
 * @date: 2018年7月13日 上午9:56:53
 */
public class VideoInfoListDto extends BaseResultDto {

	private static final long serialVersionUID = -6091727471485449250L;

	private List<VideoInfoDto> videoList;

	private PageInfo pageInfo;

	public VideoInfoListDto() {
	}

	public VideoInfoListDto(List<VideoInfoDto> videoList, PageInfo pageInfo) {
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
