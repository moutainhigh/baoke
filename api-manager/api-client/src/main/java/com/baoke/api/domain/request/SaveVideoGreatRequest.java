package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.VideoGreatDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 视频点赞入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午8:51:33
 */
public class SaveVideoGreatRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 视频id */
	private Long videoId;

	/** 0:取消 1：点赞 */
	private Integer isGreat;


	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getIsGreat() {
		return isGreat;
	}

	public void setIsGreat(Integer isGreat) {
		this.isGreat = isGreat;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoGreatDto videoGreatDto = new VideoGreatDto();
		videoGreatDto.setVideoId(this.videoId);
		videoGreatDto.setIsGreat(this.isGreat);
		return videoGreatDto;
	}

}
