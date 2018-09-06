package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询评论列表回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:43:30
 */
public class QueryVideoCommentRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private Long videoId;// 视频Id

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 20;// 每页显示的记录数

	public QueryVideoCommentRequest() {
	}

	public QueryVideoCommentRequest(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoInfoDto videoInfoDto = new VideoInfoDto(curPageNo, pageSize);
		videoInfoDto.setVideoId(this.videoId);
		return videoInfoDto;
	}

}
