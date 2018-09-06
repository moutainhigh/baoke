package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 发布、回复评论请求
 * 
 * @author zjm
 * @date: 2018年6月13日 下午2:57:52
 */
public class SaveVideoCommentRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 视频id */
	private Long videoId;

	/** 评论id */
	private Long commentId;

	/** 内容 */
	private String content;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoCommentInfoDto VideoCommentDto = new VideoCommentInfoDto();
		VideoCommentDto.setParentId(this.commentId);
		VideoCommentDto.setVideoId(this.videoId);
		VideoCommentDto.setContent(this.content);
		return VideoCommentDto;
	}

}
