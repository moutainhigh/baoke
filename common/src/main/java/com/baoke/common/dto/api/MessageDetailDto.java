package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.MessageInfoDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 购物车商家、商品信息
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:50:52
 */
public class MessageDetailDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private VideoInfoDto videoInfo;

	private VideoCommentInfoDto commentInfo;

	private List<UserInfoDto> userList;

	private List<MessageInfoDto> messageList;

	private PageInfo pageInfo;

	public MessageDetailDto() {
	}

	public MessageDetailDto(boolean success, String message, PageInfo pageInfo) {
		super(success, message);
		this.pageInfo = pageInfo;
	}

	public MessageDetailDto(boolean success, String message, VideoInfoDto videoInfo, VideoCommentInfoDto commentInfo,
			List<MessageInfoDto> messageList, PageInfo pageInfo) {
		super(success, message);
		this.videoInfo = videoInfo;
		this.commentInfo = commentInfo;
		this.messageList = messageList;
		this.pageInfo = pageInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public VideoInfoDto getVideoInfo() {
		return videoInfo;
	}

	public void setVideoInfo(VideoInfoDto videoInfo) {
		this.videoInfo = videoInfo;
	}

	public VideoCommentInfoDto getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(VideoCommentInfoDto commentInfo) {
		this.commentInfo = commentInfo;
	}

	public List<UserInfoDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoDto> userList) {
		this.userList = userList;
	}

	public List<MessageInfoDto> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageInfoDto> messageList) {
		this.messageList = messageList;
	}

}
