package com.baoke.api.domain.response;

import java.util.ArrayList;
import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.VideoListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.util.CollectionUtil;

/**
 * 查询视频详情回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:20:32
 */
public class QueryVideoDetailResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	// 视频集合
	private List<VideoInfoDto> videoList;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

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
		VideoListDto videoInfoListDto = (VideoListDto) baseResultDto;
		videoList = new ArrayList<>();
		if (videoInfoListDto.getPageInfo() != null) {
			this.curPageNo = videoInfoListDto.getPageInfo().getCurrent();
			this.pageSize = videoInfoListDto.getPageInfo().getPageSize();
			if (curPageNo == 1) {
				videoList.add(videoInfoListDto.getVideoInfo());
			}
		}
		if (CollectionUtil.isNotEmpty(videoInfoListDto.getVideoList())) {
			videoList.addAll(videoInfoListDto.getVideoList());
		}
		return this;
	}
}
