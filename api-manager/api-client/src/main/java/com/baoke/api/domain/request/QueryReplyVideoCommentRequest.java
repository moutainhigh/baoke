package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询回复评论列表入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:44:50
 */
public class QueryReplyVideoCommentRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private Long videoId;// 视频ID

	private Long commentId;// 评论Id

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 20;// 每页显示的记录数

	public QueryReplyVideoCommentRequest() {
	}

	public QueryReplyVideoCommentRequest(Long videoId, Long commentId) {
		this.videoId = videoId;
		this.commentId = commentId;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoCommentInfoDto videoCommentDto = new VideoCommentInfoDto(curPageNo, pageSize);
		videoCommentDto.setVideoId(this.videoId);
		videoCommentDto.setSecondParentId(this.commentId);
		return videoCommentDto;
	}

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
}
