package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoComplaintInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 获取举报字典请求体
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:28:53
 */
public class QueryVideoComplaintRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 视频id */
	private Long videoId;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoComplaintInfoDto videoComplaintInfoDto = new VideoComplaintInfoDto();
		videoComplaintInfoDto.setVideoId(this.videoId);
		return videoComplaintInfoDto;
	}

}
