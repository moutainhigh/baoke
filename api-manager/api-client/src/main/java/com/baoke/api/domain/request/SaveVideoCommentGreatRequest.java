package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.VideoCommentGreatDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 保存点赞评论入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午8:18:23
 */
public class SaveVideoCommentGreatRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private Long commentId;// 评论id
	private Integer isGreat;// 状态 0：未点赞；1：已点赞

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Integer getIsGreat() {
		return isGreat;
	}

	public void setIsGreat(Integer isGreat) {
		this.isGreat = isGreat;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoCommentGreatDto videoCommentGreatDto = new VideoCommentGreatDto();
		videoCommentGreatDto.setCommentId(this.commentId);
		videoCommentGreatDto.setIsGreat(this.isGreat);
		return videoCommentGreatDto;
	}

}
