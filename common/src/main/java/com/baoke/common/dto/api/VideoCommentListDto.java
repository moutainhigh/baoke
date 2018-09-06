package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoCommentInfoDto;

/**
 * 视频评论
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:51:50
 */
public class VideoCommentListDto extends BaseResultDto {
	private static final long serialVersionUID = 1L;

	private VideoCommentInfoDto commentInfo;// 被回复评论的信息

	private List<VideoCommentInfoDto> commentList;// 评论集合

	private Integer commentNum;// 评论数

	private PageInfo pageInfo;// 分页

	public VideoCommentListDto() {
	}

	public VideoCommentListDto(boolean success, String message) {
		super(success, message);
	}

	public VideoCommentListDto(Integer commentNum, List<VideoCommentInfoDto> commentList, PageInfo pageInfo) {
		this.commentNum = commentNum;
		this.commentList = commentList;
		this.pageInfo = pageInfo;
	}

	public VideoCommentListDto(Integer commentNum, List<VideoCommentInfoDto> commentList,
			VideoCommentInfoDto videoCommentInfoDto, PageInfo pageInfo) {
		this.commentInfo = videoCommentInfoDto;
		this.commentNum = commentNum;
		this.commentList = commentList;
		this.pageInfo = pageInfo;
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

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
