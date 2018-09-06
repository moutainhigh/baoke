package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 主播后台视频评论数据
 * 
 * @author ljj
 * @date: 2018年7月4日 下午5:34:45
 */
public class VideoCommentAdminListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<VideoCommentAdminDto> videoCommentAdminList;

	/** 分页数据 */
	private PageInfo pageInfo;

	public VideoCommentAdminListDto() {
		super();
	}

	public VideoCommentAdminListDto(PageInfo pageInfo) {
		super();
		this.pageInfo = pageInfo;
	}

	public VideoCommentAdminListDto(List<VideoCommentAdminDto> videoCommentAdminList, PageInfo pageInfo) {
		super();
		this.videoCommentAdminList = videoCommentAdminList;
		this.pageInfo = pageInfo;
	}

	public List<VideoCommentAdminDto> getVideoCommentAdminList() {
		return videoCommentAdminList;
	}

	public void setVideoCommentAdminList(List<VideoCommentAdminDto> videoCommentAdminList) {
		this.videoCommentAdminList = videoCommentAdminList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
