package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 视频信息
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:54:26
 */
public class VideoListDto extends BaseResultDto {

	private static final long serialVersionUID = -8590856720545944464L;

	private List<VideoInfoDto> videoList;// 视频集合

	private VideoInfoDto videoInfo;// 视频对象

	private PageInfo pageInfo;// 分页

	public VideoListDto() {
	}

	public VideoListDto(boolean success, String message, List<VideoInfoDto> videoList) {
		super(success, message);
		this.videoList = videoList;
	}

	public VideoListDto(boolean success, String message, VideoInfoDto videoInfo, List<VideoInfoDto> videoList,
			PageInfo pageInfo) {
		super(success, message);
		this.videoInfo = videoInfo;
		this.videoList = videoList;
		this.pageInfo = pageInfo;
	}

	public List<VideoInfoDto> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<VideoInfoDto> videoList) {
		this.videoList = videoList;
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
