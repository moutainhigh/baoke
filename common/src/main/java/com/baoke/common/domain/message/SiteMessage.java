package com.baoke.common.domain.message;

import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.domain.base.BaseDomain;

/**
 * 发送站内信
 * 
 * @author wyh
 * @date 2018年7月14日 下午4:13:38
 *
 */
public class SiteMessage extends BaseDomain {

	private static final long serialVersionUID = 5154755490994540949L;

	private Long fromUserId = 0L;

	private Long toUserId = 0L;

	private MessageTypeEnum messageTypeEnum;

	private Long videoId = 0L;

	private Long commentId = 0L;

	private Long parentCommentId = 0L;

	private Long secondParentCommentId = 0L;

	private String title = "";

	private String content = "";

	public SiteMessage() {
	}

	private SiteMessage(Long fromUserId, Long toUserId, MessageTypeEnum messageTypeEnum) {
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.messageTypeEnum = messageTypeEnum;
	}

	/**
	 * 系统消息
	 */
	public static SiteMessage createSystemSiteMessage(Long fromUserId, Long toUserId, Long videoId, String title,
			String content) {
		SiteMessage siteMessage = new SiteMessage(fromUserId, toUserId, MessageTypeEnum.SYSTEM);
		siteMessage.setVideoId(videoId);
		siteMessage.setTitle(title);
		siteMessage.setContent(content);
		return siteMessage;
	}

	/**
	 * 主播被关注
	 */
	public static SiteMessage createSellerBeFocusSiteMessage(Long fromUserId, Long toUserId) {
		SiteMessage siteMessage = new SiteMessage(fromUserId, toUserId, MessageTypeEnum.SELLER_BE_FOCUS);
		return siteMessage;
	}

	/**
	 * 主播被点赞
	 */
	public static SiteMessage createSellerBeGreatSiteMessage(Long fromUserId, Long toUserId, Long videoId) {
		SiteMessage siteMessage = new SiteMessage(fromUserId, toUserId, MessageTypeEnum.SELLER_BE_GREAT);
		siteMessage.setVideoId(videoId);
		return siteMessage;
	}

	/**
	 * 评论消息－主播收到的评论（一级）
	 */
	public static SiteMessage createSellerBeCommentMessage(Long fromUserId, Long toUserId, Long videoId, Long commentId,
			Long parentCommentId, Long secondParentCommentId, String content) {
		SiteMessage siteMessage = new SiteMessage(fromUserId, toUserId, MessageTypeEnum.SELLER_BE_COMMENT);
		siteMessage.setVideoId(videoId);
		siteMessage.setCommentId(commentId);
		siteMessage.setParentCommentId(parentCommentId);
		siteMessage.setSecondParentCommentId(secondParentCommentId);
		siteMessage.setContent(content);
		return siteMessage;
	}

	/**
	 * 评论消息－评论的回复（二级）
	 */
	public static SiteMessage createCommentInteractMessage(Long fromUserId, Long toUserId, Long videoId, Long commentId,
			Long parentCommentId, Long secondParentCommentId, String content) {
		SiteMessage siteMessage = new SiteMessage(fromUserId, toUserId, MessageTypeEnum.COMMENT_INTERACT);
		siteMessage.setVideoId(videoId);
		siteMessage.setCommentId(commentId);
		siteMessage.setParentCommentId(parentCommentId);
		siteMessage.setSecondParentCommentId(secondParentCommentId);
		siteMessage.setContent(content);
		return siteMessage;
	}

	/**
	 * 评论被点赞消息
	 */
	public static SiteMessage createCommentGreatSiteMessage(Long fromUserId, Long toUserId, Long videoId,
			Long commentId, Long parentCommentId, Long secondParentCommentId, String content) {
		SiteMessage siteMessage = new SiteMessage(fromUserId, toUserId, MessageTypeEnum.COMMENT_GREAT);
		siteMessage.setVideoId(videoId);
		siteMessage.setCommentId(commentId);
		siteMessage.setParentCommentId(parentCommentId);
		siteMessage.setSecondParentCommentId(secondParentCommentId);
		siteMessage.setContent(content);
		return siteMessage;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public MessageTypeEnum getMessageTypeEnum() {
		return messageTypeEnum;
	}

	public void setMessageTypeEnum(MessageTypeEnum messageTypeEnum) {
		this.messageTypeEnum = messageTypeEnum;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId == null ? 0L : videoId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId == null ? 0L : commentId;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId == null ? 0L : parentCommentId;
	}

	public Long getSecondParentCommentId() {
		return secondParentCommentId;
	}

	public void setSecondParentCommentId(Long secondParentCommentId) {
		this.secondParentCommentId = secondParentCommentId == null ? 0L : secondParentCommentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? "" : title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? "" : content;
	}

}
