package com.baoke.interact.domain;

import java.util.Date;
import java.util.List;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.user.constant.UserTypeEnum;

/**
 * 视频评论domain
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:05:28
 */
public class VideoComment extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;//
	private Long secondParentId;// 检索使用，2级评论的父id，一级评论为0
	private Long parentId;// 评论的父id，一级评论为0
	private Long videoId;// 视频id
	private Long sellerId;// 播主id
	private Long userId;// 评论人id
	private Integer status;// 状态 0：不可用；1：可用
	private String content;// 内容
	private Date deletedTime;// 删除日期
	private Integer greatNum;// 点赞数
	private Date createTime;//
	private Date updateTime;//

	public VideoComment() {
	}

	public VideoComment(Long secondParentId, BooleanEnum booleanEnum) {
		this.secondParentId = secondParentId;
		this.status = booleanEnum.getCode();
	}

	public VideoComment(Long videoId, Long sellerId, VideoCommentStatusEnum videoCommentStatusEnum) {
		this.videoId = videoId;
		this.sellerId = sellerId;
		if (null != videoCommentStatusEnum) {
			this.status = videoCommentStatusEnum.getCode();
		}
	}

	public VideoComment(Long videoId, Long sellerId, Long secondParentId,
			VideoCommentStatusEnum videoCommentStatusEnum) {
		this.videoId = videoId;
		this.sellerId = sellerId;
		this.secondParentId = secondParentId;
		if (null != videoCommentStatusEnum) {
			this.status = videoCommentStatusEnum.getCode();
		}
	}

	public VideoComment(VideoCommentInfoDto videoCommentDto, long sellerId, long parentId, long secondParentId) {
		this.userId = videoCommentDto.getUserId();
		this.videoId = videoCommentDto.getVideoId();
		this.parentId = parentId;
		this.secondParentId = secondParentId;
		this.content = videoCommentDto.getContent();
		this.greatNum = (null == videoCommentDto.getGreatNum()) ? 0 : videoCommentDto.getGreatNum();
		this.sellerId = sellerId;
		this.status = BooleanEnum.TRUE.getCode();
	}

	public VideoCommentInfoDto convert() {
		VideoCommentInfoDto videoCommentInfoDto = new VideoCommentInfoDto();
		videoCommentInfoDto.setUserId(this.userId);
		videoCommentInfoDto.setTime(this.createTime.getTime());
		videoCommentInfoDto.setGreatNum(this.getGreatNum());
		videoCommentInfoDto.setCommentId(this.getId());
		videoCommentInfoDto.setParentId(this.getParentId());
		videoCommentInfoDto.setSecondParentId(this.getSecondParentId());
		videoCommentInfoDto.setStatus(this.getStatus());
		videoCommentInfoDto.setVideoId(this.getVideoId());
		videoCommentInfoDto.setContent(this.content);
		return videoCommentInfoDto;
	}

	/**
	 * 评论敏感词替换
	 * 
	 * @author zdy
	 * @date: 2018年7月26日 下午7:19:51
	 * @param blackKeyWordList
	 *            敏感词集合
	 * @return
	 */
	public VideoCommentInfoDto convert(List<BlackKeyWord> blackKeyWordList) {
		VideoCommentInfoDto videoCommentInfoDto = this.convert();
		if (CollectionUtil.isNotEmpty(blackKeyWordList)) {
			for (BlackKeyWord blackKeyWord : blackKeyWordList) {
				if (StringUtil.isNotEmpty(this.content)) {
					content = this.content.replaceAll(blackKeyWord.getName(), CommonConfig.BLACK_KEY_WORD_REPLACE);
				}
			}
		}
		videoCommentInfoDto.setContent(this.content);
		return videoCommentInfoDto;
	}

	/**
	 * 判断是否是播主本人及评论敏感词替换
	 * 
	 * @author zdy
	 * @date: 2018年7月26日 下午8:00:59
	 * @param isSeller
	 *            是否是播主本人
	 * @param blackKeyWordList
	 *            敏感词集合
	 * @return
	 */
	public VideoCommentInfoDto convert(boolean isSeller, List<BlackKeyWord> blackKeyWordList) {
		VideoCommentInfoDto videoCommentInfoDto = this.convert(blackKeyWordList);
		if (isSeller) {
			videoCommentInfoDto.setUserType(UserTypeEnum.SELLER.getCode());
		} else {
			videoCommentInfoDto.setUserType(UserTypeEnum.USER.getCode());
		}
		return videoCommentInfoDto;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getVideoId() {
		return this.videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGreatNum() {
		return this.greatNum;
	}

	public void setGreatNum(Integer greatNum) {
		this.greatNum = greatNum;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getSecondParentId() {
		return secondParentId;
	}

	public void setSecondParentId(Long secondParentId) {
		this.secondParentId = secondParentId;
	}

	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

}
