package com.baoke.api.domain.response;

import java.util.Date;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;

import net.sf.cglib.beans.BeanCopier;

public class QueryVideoCommentDetailResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private Long id;//
	private Long parentId;// 父id，只做一级评论级联
	private Integer status;// 状态 0：不可用；1：可用
	private String content;// 内容
	private Integer greatNum;// 点赞数
	private Date createTime;//

	private Long videoId;// 视频Id
	private String userNick;// 评论用户昵称
	private String headImgUrl;// 评论用户头像
	private Integer userType;// 评论用户是否播主（卖家）0：普通用户；1：播主
	private Integer isGreat;// 是否点赞
	private Integer commentNum;// 回复数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGreatNum() {
		return greatNum;
	}

	public void setGreatNum(Integer greatNum) {
		this.greatNum = greatNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		VideoCommentInfoDto videoCommentDto = (VideoCommentInfoDto) baseResultDto;
		final BeanCopier bc = BeanCopier.create(videoCommentDto.getClass(), this.getClass(), false);
		bc.copy(videoCommentDto, this, null);
		return this;
	}
}
