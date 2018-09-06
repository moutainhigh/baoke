package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.VideoCommentListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;

/**
 * 查询回复评论列表回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:45:13
 */
public class QueryReplyVideoCommentResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private VideoCommentInfoDto commentInfo;// 被回复评论的信息
	private Integer commentNum;// 评论数
	private List<VideoCommentInfoDto> commentList;// 视频评论回复集合
	private Integer curPageNo;// 当前页数
	private Integer pageSize;// 每页显示的记录数

	public QueryReplyVideoCommentResponse() {
	}

	public QueryReplyVideoCommentResponse(Integer commentNum, List<VideoCommentInfoDto> commentList,
			VideoCommentInfoDto commentInfo) {
		this.commentInfo = commentInfo;
		this.commentNum = commentNum;
		this.commentList = commentList;
	}

	public VideoCommentInfoDto getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(VideoCommentInfoDto commentInfo) {
		this.commentInfo = commentInfo;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public List<VideoCommentInfoDto> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<VideoCommentInfoDto> commentList) {
		this.commentList = commentList;
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

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		VideoCommentListDto videoCommentListDto = (VideoCommentListDto) baseResultDto;
		this.commentInfo = videoCommentListDto.getCommentInfo();
		this.commentNum = videoCommentListDto.getCommentNum();
		this.commentList = videoCommentListDto.getCommentList();
		if (videoCommentListDto.getPageInfo() != null) {
			this.curPageNo = videoCommentListDto.getPageInfo().getCurrent();
			this.pageSize = videoCommentListDto.getPageInfo().getPageSize();
		}
		return this;
	}

}
