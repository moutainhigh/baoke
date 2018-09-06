package com.baoke.interact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.MessageNotify;

/**
 * 消息通知dao
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午4:46:40
 */
public interface MessageNotifyDao {

	MessageNotify queryMessageNotifyById(long id);

	MessageNotify queryLastMessageNotify(@Param("messageType") int messageType, @Param("userId") long userId,
			@Param("videoId") long videoId, @Param("secondParentCommentId") long secondParentCommentId,
			@Param("status") int status);

	/**
	 * 查询系统消息
	 */
	List<MessageNotify> querySystemMessageNotifyList(@Param("messageType") int messageType,
			@Param("userId") long userId, @Param("pageInfo") PageInfo pageInfo, @Param("status") int status);

	/**
	 * 查询主播关注消息
	 */
	List<MessageNotify> querySellerBeFocusMessageNotifyList(@Param("messageType") int messageType,
			@Param("userId") long userId, @Param("pageInfo") PageInfo pageInfo, @Param("status") int status);

	/**
	 * 查询视频点赞信息
	 */
	List<MessageNotify> querySellerBeGreatMessageNotifyList(@Param("messageType") int messageType,
			@Param("userId") long userId, @Param("videoId") long videoId, @Param("pageInfo") PageInfo pageInfo,
			@Param("status") int status);

	/**
	 * 查询视频被评价消息，一级评论
	 */
	List<MessageNotify> querySellerBeCommentMessageNotifyList(@Param("messageType") int messageType,
			@Param("userId") long userId, @Param("videoId") long videoId, @Param("pageInfo") PageInfo pageInfo,
			@Param("status") int status);

	/**
	 * 查询评价回复消息，二级评论
	 */
	List<MessageNotify> queryCommentInteractMessageNotifyList(@Param("messageType") int messageType,
			@Param("userId") long userId, @Param("secondParentCommentId") long secondParentCommentId,
			@Param("pageInfo") PageInfo pageInfo, @Param("status") int status);

	/**
	 * 查询评论点赞消息
	 */
	List<MessageNotify> queryCommentGreatMessageNotifyList(@Param("messageType") int messageType,
			@Param("userId") long userId, @Param("secondParentCommentId") long secondParentCommentId,
			@Param("pageInfo") PageInfo pageInfo, @Param("status") int status);

	int countUnreadMessageNotify(@Param("messageType") int messageType, @Param("userId") long userId,
			@Param("videoId") long videoId, @Param("secondParentCommentId") long secondParentCommentId,
			@Param("status") int status);

	int countMessageNotifyByUserIdAndIsRead(@Param("userId") long userId, @Param("isRead") int isRead);

	int addMessageNotify(MessageNotify messageNotify);

	int modifyMessageReadStatus(@Param("idList") List<Long> idList, @Param("isRead") int isRead);

}
