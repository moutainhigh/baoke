package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.VideoGreatDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 保存感兴趣情况（用户对视频）入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午8:51:33
 */
public class SaveVideoDelikeRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 视频id */
	private Long videoId;

	/** 不感兴趣 0：否；1：是 */
	private Integer isDelike;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getIsDelike() {
		return isDelike;
	}

	public void setIsDelike(Integer isDelike) {
		this.isDelike = isDelike;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoGreatDto videoGreatDto = new VideoGreatDto();
		videoGreatDto.setVideoId(this.videoId);
		videoGreatDto.setIsDelike(this.isDelike);
		return videoGreatDto;
	}

}
