package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询视频评论详情入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午8:33:38
 */
public class QueryVideoCommentDetailRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private Long videoCommentId;// 视频评论ID

	public Long getVideoCommentId() {
		return videoCommentId;
	}

	public void setVideoCommentId(Long videoCommentId) {
		this.videoCommentId = videoCommentId;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoCommentInfoDto videoCommentDto = new VideoCommentInfoDto();
		videoCommentDto.setCommentId(this.videoCommentId);
		return videoCommentDto;
	}

}
