package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.VideoListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 查询首页关注列表回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:19:26
 */
public class QueryFollowVideoResponse extends BaseResponseParam {

	private static final long serialVersionUID = -8590856720545944464L;

	private List<VideoInfoDto> videoList;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

	public QueryFollowVideoResponse() {
	}

	public QueryFollowVideoResponse(List<VideoInfoDto> videoList) {
		this.videoList = videoList;
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
		VideoListDto videoListDto = (VideoListDto) baseResultDto;
		this.videoList = videoListDto.getVideoList();
		if (videoListDto.getPageInfo() != null) {
			this.curPageNo = videoListDto.getPageInfo().getCurrent();
			this.pageSize = videoListDto.getPageInfo().getPageSize();
		}
		return this;
	}

}
