package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 站内信dto
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午5:32:49
 */
public class MessageInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 6187207033328830792L;

	private Long messageId;

	// 消息类型
	private Integer messageType;

	// 发送人头像
	private String headImgUrl;

	// 发送人昵称
	private String nickName;

	// 当前消息的父消息发送人Id
	private Long parentUserId;

	// 当前消息的父消息发送人头像
	private String parentHeadImgUrl;

	// 当前消息的父消息发送人昵称
	private String parentNickName;

	// 是否是播主
	private Integer userType;

	// 标题
	private String title;

	// 内容
	private String content;

	// 当前消息的父消息标题
	private String parentTitle;

	// 当前消息的父消息内容
	private String parentContent;

	// 视频ID
	private Long videoId;

	// 评论id
	private Long commentId;

	// 时间
	private Long time;

	// 已读标记
	private Integer isRead;

	// 评论点赞数
	private Integer num;

	// 是否点赞
	private Integer isGreat;

	private PageInfo pageInfo;// 分页

	public MessageInfoDto() {
	}

	public MessageInfoDto(Integer curPageNo, Integer pageSize) {
		this.pageInfo = new PageInfo(curPageNo, pageSize);
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}

	public String getParentHeadImgUrl() {
		return parentHeadImgUrl;
	}

	public void setParentHeadImgUrl(String parentHeadImgUrl) {
		this.parentHeadImgUrl = parentHeadImgUrl;
	}

	public String getParentNickName() {
		return parentNickName;
	}

	public void setParentNickName(String parentNickName) {
		this.parentNickName = parentNickName;
	}

	public String getParentTitle() {
		return parentTitle;
	}

	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}

	public String getParentContent() {
		return parentContent;
	}

	public void setParentContent(String parentContent) {
		this.parentContent = parentContent;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getIsGreat() {
		return isGreat;
	}

	public void setIsGreat(Integer isGreat) {
		this.isGreat = isGreat;
	}

}
