package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 视频评论DTO
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:56:17
 */
public class VideoCommentInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Long commentId;//

	private Long secondParentId;// 检索使用，2级评论的父id，一级评论为0

	private Long parentId;// 父id，只做一级评论级联

	private Long videoId;// 视频Id

	private String userNick;// 评论用户昵称

	private String headImgUrl;// 评论用户头像

	private Integer userType;// 评论用户是否播主（卖家）0：普通用户；1：播主

	private String content;// 内容

	private Long time;// 评论时间

	private Integer status;// 状态

	private Integer greatNum;// 点赞数

	private Integer isGreat;// 是否点赞

	private Integer commentNum;// 回复数

	private VideoCommentInfoDto parentCommentInfo;

	private PageInfo pageInfo;

	public VideoCommentInfoDto() {
	}

	public VideoCommentInfoDto(boolean success, String message) {
		super(success, message);
	}

	public VideoCommentInfoDto(Integer curPageNo, Integer pageSize) {
		this.pageInfo = new PageInfo(curPageNo, pageSize);
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getSecondParentId() {
		return secondParentId;
	}

	public void setSecondParentId(Long secondParentId) {
		this.secondParentId = secondParentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGreatNum() {
		return greatNum;
	}

	public void setGreatNum(Integer greatNum) {
		this.greatNum = greatNum;
	}

	public Integer getIsGreat() {
		return isGreat;
	}

	public void setIsGreat(Integer isGreat) {
		this.isGreat = isGreat;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public VideoCommentInfoDto getParentCommentInfo() {
		return parentCommentInfo;
	}

	public void setParentCommentInfo(VideoCommentInfoDto parentCommentInfo) {
		this.parentCommentInfo = parentCommentInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
