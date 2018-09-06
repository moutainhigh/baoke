package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.IsReadEnum;
import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.MessageInfoDto;

/**
 * 消息通知实体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午4:35:25
 */
public class MessageNotify extends BaseDomain {

	private static final long serialVersionUID = -4708674822231995473L;

	private Long id;

	// 短信类型
	private Integer messageType;

	// 接收人用户ID，全网消息用户ID为0
	private Long userId;

	// 视频ID
	private Long videoId;

	// 当前评论ID
	private Long commentId;

	// 父评论ID，一级评论为空
	private Long parentCommentId;

	// 二级父评论ID
	private Long secondParentCommentId;

	// 发送人用户ID
	private Long fromUserId;

	// 标题
	private String title;

	// 内容
	private String content;

	// 已读标记
	private Integer isRead;

	// 状态 0：无效（删除）,1:有效
	private Integer status;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public MessageNotify() {
	}

	public MessageNotify(Integer messageType, Long userId, Long videoId, Long secondParentCommentId) {
		this.messageType = messageType;
		this.userId = userId;
		this.videoId = videoId;
		this.secondParentCommentId = secondParentCommentId;
	}

	/**
	 * 创建系统消息
	 * 
	 * @author wyh
	 * @date 2018年7月14日 上午9:11:50
	 * 
	 * @param userId
	 * @param fromUserId
	 * @param title
	 * @param content
	 * @return
	 */
	public static MessageNotify createSystemMessage(long userId, long fromUserId, String title, String content) {
		MessageNotify messageNotify = new MessageNotify();
		messageNotify.setMessageType(MessageTypeEnum.SYSTEM.getCode());
		messageNotify.setUserId(userId);
		messageNotify.setFromUserId(fromUserId);
		messageNotify.setIsRead(IsReadEnum.UN_READ.getCode());
		messageNotify.setStatus(BooleanEnum.TRUE.getCode());
		messageNotify.setContent(content);
		messageNotify.setTitle(title);
		return messageNotify;
	}

	/**
	 * 创建播主关注消息
	 * 
	 * @author wyh
	 * @date 2018年7月14日 上午9:15:22
	 * 
	 * @param userId
	 * @param fromUserId
	 * @param title
	 * @param content
	 * @return
	 */
	public static MessageNotify createSellerBeFocusMessage(long userId, long fromUserId, String title, String content) {
		MessageNotify messageNotify = new MessageNotify();
		messageNotify.setMessageType(MessageTypeEnum.SELLER_BE_FOCUS.getCode());
		messageNotify.setUserId(userId);
		messageNotify.setFromUserId(fromUserId);
		messageNotify.setIsRead(IsReadEnum.UN_READ.getCode());
		messageNotify.setStatus(BooleanEnum.TRUE.getCode());
		messageNotify.setContent(content);
		messageNotify.setTitle(title);
		return messageNotify;
	}

	/**
	 * 创建播主被点赞消息
	 * 
	 * @author wyh
	 * @date 2018年7月14日 上午9:17:37
	 * 
	 * @param userId
	 * @param fromUserId
	 * @param title
	 * @param content
	 * @return
	 */
	public static MessageNotify createSellerBeGreatMessage(long userId, long fromUserId, String title, String content) {
		MessageNotify messageNotify = new MessageNotify();
		messageNotify.setMessageType(MessageTypeEnum.SELLER_BE_GREAT.getCode());
		messageNotify.setUserId(userId);
		messageNotify.setFromUserId(fromUserId);
		messageNotify.setIsRead(IsReadEnum.UN_READ.getCode());
		messageNotify.setStatus(BooleanEnum.TRUE.getCode());
		messageNotify.setContent(content);
		messageNotify.setTitle(title);
		return messageNotify;
	}

	/**
	 * 创建视频一级评论消息
	 * 
	 * @author wyh
	 * @date 2018年7月14日 上午9:19:34
	 * 
	 * @param userId
	 * @param fromUserId
	 * @param title
	 * @param content
	 * @return
	 */
	public static MessageNotify createSellerBeCommentMessage(long userId, long fromUserId, long videoId, long commentId,
			String title, String content) {
		MessageNotify messageNotify = new MessageNotify();
		messageNotify.setMessageType(MessageTypeEnum.SELLER_BE_COMMENT.getCode());
		messageNotify.setUserId(userId);
		messageNotify.setFromUserId(fromUserId);
		messageNotify.setVideoId(videoId);
		messageNotify.setCommentId(commentId);
		messageNotify.setIsRead(IsReadEnum.UN_READ.getCode());
		messageNotify.setStatus(BooleanEnum.TRUE.getCode());
		messageNotify.setContent(content);
		messageNotify.setTitle(title);
		return messageNotify;
	}

	/**
	 * 创建视频二级评论消息
	 * 
	 * @author wyh
	 * @date 2018年7月14日 上午9:20:53
	 * 
	 * @param userId
	 * @param fromUserId
	 * @param title
	 * @param content
	 * @return
	 */
	public static MessageNotify createCommentInteractMessage(long userId, long fromUserId, long videoId, long commentId,
			long parentCommentId, String title, String content) {
		MessageNotify messageNotify = new MessageNotify();
		messageNotify.setMessageType(MessageTypeEnum.COMMENT_INTERACT.getCode());
		messageNotify.setUserId(userId);
		messageNotify.setFromUserId(fromUserId);
		messageNotify.setVideoId(videoId);
		messageNotify.setCommentId(commentId);
		messageNotify.setParentCommentId(parentCommentId);
		messageNotify.setIsRead(IsReadEnum.UN_READ.getCode());
		messageNotify.setStatus(BooleanEnum.TRUE.getCode());
		messageNotify.setContent(content);
		messageNotify.setTitle(title);
		return messageNotify;
	}

	/**
	 * 创建视频点赞消息
	 * 
	 * @author wyh
	 * @date 2018年7月14日 上午9:21:48
	 * 
	 * @param userId
	 * @param fromUserId
	 * @param title
	 * @param content
	 * @return
	 */
	public static MessageNotify createCommentGreatMessage(long userId, long fromUserId, long videoId, long commentId,
			String title, String content) {
		MessageNotify messageNotify = new MessageNotify();
		messageNotify.setMessageType(MessageTypeEnum.COMMENT_GREAT.getCode());
		messageNotify.setUserId(userId);
		messageNotify.setFromUserId(fromUserId);
		messageNotify.setVideoId(videoId);
		messageNotify.setCommentId(commentId);
		messageNotify.setIsRead(IsReadEnum.UN_READ.getCode());
		messageNotify.setStatus(BooleanEnum.TRUE.getCode());
		messageNotify.setContent(content);
		messageNotify.setTitle(title);
		return messageNotify;
	}

	public MessageInfoDto convertMessageInfoDto(MessageInfoDto messageInfoDto) {
		if (null == messageInfoDto) {
			messageInfoDto = new MessageInfoDto();
		}
		messageInfoDto.setMessageId(this.id);
		messageInfoDto.setMessageType(this.messageType);
		messageInfoDto.setTitle(this.title);
		messageInfoDto.setContent(this.content);
		messageInfoDto.setVideoId(this.videoId);
		messageInfoDto.setCommentId(this.commentId);
		messageInfoDto.setTime(this.createTime.getTime());
		messageInfoDto.setIsRead(this.isRead);
		return messageInfoDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
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

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSecondParentCommentId() {
		return secondParentCommentId;
	}

	public void setSecondParentCommentId(Long secondParentCommentId) {
		this.secondParentCommentId = secondParentCommentId;
	}

}
