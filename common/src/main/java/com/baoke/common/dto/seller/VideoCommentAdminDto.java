package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoCommentInfoDto;

/**
 * 评论分页数据
 * 
 * @author zjm
 * @date: 2018年6月27日 下午7:23:22
 */
public class VideoCommentAdminDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	/** 评论信息 */
	private VideoCommentInfoDto commentInfo;

	/** 评论IDs */
	private List<Long> ids;

	/** 禁言状态 0：未禁言 1：已禁言 */
	private Integer bannedStatus;

	/** 视频的标题 */
	private String title;

	/** 评论内容 */
	private String content;

	/** 被回复用户的id */
	private Long toUserId;

	/** 被回复用户的名称 */
	private String toUserNick;

	/** 用户名称 */
	private String nickName;

	private Long sellerId;

	/** 分页数据 */
	private PageInfo pageInfo;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public VideoCommentInfoDto getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(VideoCommentInfoDto commentInfo) {
		this.commentInfo = commentInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Integer getBannedStatus() {
		return bannedStatus;
	}

	public void setBannedStatus(Integer bannedStatus) {
		this.bannedStatus = bannedStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUserNick() {
		return toUserNick;
	}

	public void setToUserNick(String toUserNick) {
		this.toUserNick = toUserNick;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

}
