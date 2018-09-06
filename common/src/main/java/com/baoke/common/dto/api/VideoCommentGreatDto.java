package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 视频评论点赞DTO
 * 
 * @author zdy
 * @date: 2018年6月13日 下午8:19:53
 */
public class VideoCommentGreatDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Long commentId;// 评论id

	private Integer isGreat;

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

}
