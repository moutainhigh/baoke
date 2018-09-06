package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;

/**
 * 发布、回复评论响应体
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:03:33
 */
public class SaveVideoCommentResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private VideoCommentInfoDto commentInfo;// 被回复评论的信息

	public VideoCommentInfoDto getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(VideoCommentInfoDto commentInfo) {
		this.commentInfo = commentInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		this.commentInfo = (VideoCommentInfoDto) baseResultDto;
		return this;
	}

}
