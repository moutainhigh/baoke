package com.baoke.interact.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.IsReadEnum;
import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.domain.result.Result;
import com.baoke.common.dto.MessageListDto;
import com.baoke.common.dto.api.MessageDetailDto;
import com.baoke.common.dto.info.MessageInfoDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.interact.domain.MessageInfo;
import com.baoke.interact.domain.MessageNotify;
import com.baoke.interact.domain.VideoComment;
import com.baoke.interact.domain.VideoCommentGreat;
import com.baoke.interact.manager.MessageInfoManager;
import com.baoke.interact.manager.MessageNotifyManager;
import com.baoke.interact.manager.VideoCommentGreatManager;
import com.baoke.interact.manager.VideoCommentManager;
import com.baoke.interact.service.MessageService;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.UserInfoManager;

@Service("messageService")
@ServiceDefinition(value = "messageService")
public class MessageServiceImpl implements MessageService {

	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

	@Resource
	private MessageNotifyManager messageNotifyManager;

	@Resource
	private MessageInfoManager messageInfoManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private VideoCommentManager videoCommentManager;

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private VideoCommentGreatManager videoCommentGreatManager;

	@Override
	@MethodDefinition(value = "queryMyMessage", needLogin = true)
	public MessageListDto queryMyMessage(MessageListDto messageListDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(messageListDto);

		List<MessageInfo> messageInfoList = messageInfoManager.queryMyMessageInfo(userInfo.getId(),
				messageListDto.getPageInfo());
		if (CollectionUtils.isEmpty(messageInfoList)) {
			if (logger.isDebugEnabled()) {
				logger.debug("query my message is empty, userId = " + userInfo.getId());
			}
			return new MessageListDto(true, "暂时没有任何信息！");
		}

		List<MessageInfoDto> messageDtoList = new ArrayList<MessageInfoDto>();
		for (MessageInfo messageInfo : messageInfoList) {
			if (null != messageInfo.getVideoId() && 0 != messageInfo.getVideoId()) {
				if (null == videoInfoManager.queryVideoInfoByIdAndStatus(messageInfo.getVideoId(),
						VideoStatusEnum.ONLINE)) {
					logger.warn("query videoInfo is empty, " + messageInfo);
					continue;
				}
			}
			if (null != messageInfo.getCommentId() && 0 != messageInfo.getCommentId()) {
				if (null == videoCommentManager.queryVideoCommentById(messageInfo.getCommentId(),
						VideoCommentStatusEnum.NORMAL)) { // 一级评论
					logger.warn("query 1st videoCommentInfo is empty, " + messageInfo);
					continue;
				}
			}

			MessageNotify messageNotify = new MessageNotify(messageInfo.getMessageType(), userInfo.getId(),
					messageInfo.getVideoId(), messageInfo.getCommentId());
			MessageNotify messageNotifyTemp = messageNotifyManager.queryLastMessageNotify(messageNotify,
					BooleanEnum.TRUE);
			if (null == messageNotifyTemp) {
				logger.error("query last message notify is empty, " + messageInfo);
				continue;
			}
			if (null != messageNotifyTemp.getCommentId() && 0 != messageNotifyTemp.getCommentId()) {
				if (null == videoCommentManager.queryVideoCommentById(messageNotifyTemp.getCommentId(),
						VideoCommentStatusEnum.NORMAL)) { // 二级评论
					logger.warn("query 2nd videoCommentInfo is empty, " + messageNotifyTemp);
					continue;
				}
			}

			MessageInfoDto messageInfoDto = new MessageInfoDto();
			if (messageNotifyTemp.getFromUserId() == 0) { // 系统通知
				new UserInfo().convertMessageInfoDto(messageInfoDto, true);
			} else {
				UserInfo fromUser = userInfoManager.queryUserInfoById(messageNotifyTemp.getFromUserId());
				if (null == fromUser) {
					logger.error("query my message, fromuser is empty, userId=" + userInfo.getId() + " fromuserId = "
							+ messageNotifyTemp.getFromUserId());
					continue;
				}
				fromUser.convertMessageInfoDto(messageInfoDto, false);
			}
			messageInfoDto = messageNotifyTemp.convertMessageInfoDto(messageInfoDto);
			messageInfoDto.setCommentId(messageNotifyTemp.getSecondParentCommentId()); // 此处以一级评论ID分组

			int count = messageNotifyManager.countUnReadMessageNotify(messageNotifyTemp, BooleanEnum.TRUE);
			messageInfoDto.setIsRead(count > 0 ? 0 : 1); // 0 表示至少有一条信息未读
			messageInfoDto.setTitle(messageInfo.getMessageTitle());

			messageDtoList.add(messageInfoDto);
		}
		if (CollectionUtils.isEmpty(messageDtoList)) {
			if (logger.isDebugEnabled()) {
				logger.debug("query my message is empty, userId=" + userInfo.getId());
			}
			return new MessageListDto(true, "暂时没有任何信息！");
		}

		return new MessageListDto(true, "", messageDtoList, messageListDto.getPageInfo());
	}

	@MethodDefinition(value = "queryMyMessageByType", needLogin = true)
	@Override
	public MessageDetailDto queryMyMessageByType(MessageInfoDto messageInfoDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(messageInfoDto);
		Integer messageType = messageInfoDto.getMessageType();
		Long videoId = messageInfoDto.getVideoId();
		Long secondParentCommentId = messageInfoDto.getCommentId(); // 指一级评论ID，因为列表是以一级评论ID做的分类

		if (!MessageTypeEnum.isExist(messageType) || messageType == MessageTypeEnum.SELLER_BE_FOCUS.getCode()
				|| messageType == MessageTypeEnum.SELLER_BE_GREAT.getCode()) {
			throw new ParamInvalidException("消息类型不合法");
		}

		Result result = null;
		VideoInfoDto videoInfoDto = new VideoInfoDto();
		VideoCommentInfoDto videoCommentInfoDto = new VideoCommentInfoDto();
		List<MessageNotify> messageNotifyList = new ArrayList<>();
		List<MessageInfoDto> messageInfoDtoList = new ArrayList<>();

		switch (MessageTypeEnum.getByCode(messageType)) {
		case SYSTEM:
			messageNotifyList = getMessageNotifyList(messageInfoDto);
			if (CollectionUtil.isEmpty(messageNotifyList)) {
				if (messageInfoDto.getPageInfo().getCurrent() == 1) {
					return new MessageDetailDto(false, "没有此类消息！", messageInfoDto.getPageInfo());
				} else {
					return new MessageDetailDto(true, "没有更多消息！", messageInfoDto.getPageInfo());
				}
			}

			handleSystemMessage(messageNotifyList, messageInfoDtoList, userInfo.getId());
			break;
		case SELLER_BE_COMMENT:
			result = getVideoInfoDto(videoId);
			if (!result.isSuccess()) {
				return new MessageDetailDto(false, result.getMessage(), messageInfoDto.getPageInfo());
			}
			videoInfoDto = (VideoInfoDto) result.getBody();

			messageNotifyList = getMessageNotifyList(messageInfoDto);
			if (CollectionUtil.isEmpty(messageNotifyList)) {
				if (messageInfoDto.getPageInfo().getCurrent() == 1) {
					return new MessageDetailDto(false, "没有此类消息！", messageInfoDto.getPageInfo());
				} else {
					return new MessageDetailDto(true, "没有更多消息！", messageInfoDto.getPageInfo());
				}
			}

			handleSellerBeCommentMessage(messageNotifyList, messageInfoDtoList, userInfo.getId());
			break;
		case COMMENT_INTERACT:
			result = getVideoCommentInfoDto(secondParentCommentId, userInfo.getId());
			if (!result.isSuccess()) {
				return new MessageDetailDto(false, result.getMessage(), messageInfoDto.getPageInfo());
			}
			videoCommentInfoDto = (VideoCommentInfoDto) result.getBody();

			result = getVideoInfoDto(videoCommentInfoDto.getVideoId());
			if (!result.isSuccess()) {
				return new MessageDetailDto(false, result.getMessage(), messageInfoDto.getPageInfo());
			}
			videoInfoDto = (VideoInfoDto) result.getBody();

			messageNotifyList = getMessageNotifyList(messageInfoDto);
			if (CollectionUtil.isEmpty(messageNotifyList)) {
				if (messageInfoDto.getPageInfo().getCurrent() == 1) {
					return new MessageDetailDto(false, "没有此类消息！", messageInfoDto.getPageInfo());
				} else {
					return new MessageDetailDto(true, "没有更多消息！", messageInfoDto.getPageInfo());
				}
			}

			handleCommentInterActMessage(messageNotifyList, messageInfoDtoList, userInfo.getId());
			break;
		case COMMENT_GREAT:
			result = getVideoCommentInfoDto(secondParentCommentId, userInfo.getId());
			if (!result.isSuccess()) {
				return new MessageDetailDto(false, result.getMessage(), messageInfoDto.getPageInfo());
			}
			videoCommentInfoDto = (VideoCommentInfoDto) result.getBody();

			result = getVideoInfoDto(videoCommentInfoDto.getVideoId());
			if (!result.isSuccess()) {
				return new MessageDetailDto(false, result.getMessage(), messageInfoDto.getPageInfo());
			}
			videoInfoDto = (VideoInfoDto) result.getBody();

			messageNotifyList = getMessageNotifyList(messageInfoDto);
			if (CollectionUtil.isEmpty(messageNotifyList)) {
				if (messageInfoDto.getPageInfo().getCurrent() == 1) {
					return new MessageDetailDto(false, "没有此类消息！", messageInfoDto.getPageInfo());
				} else {
					return new MessageDetailDto(true, "没有更多消息！", messageInfoDto.getPageInfo());
				}
			}

			handleSellerBeCommentMessage(messageNotifyList, messageInfoDtoList, userInfo.getId()); // 同SELLER_BE_COMMENT
			break;
		default:
			throw new ParamInvalidException("消息类型不合法");
		}

		if (CollectionUtils.isEmpty(messageInfoDtoList)) {
			logger.warn("query my message by type, message is empty, userId = " + userInfo.getId() + ", messageType = "
					+ messageType);
			if (messageInfoDto.getPageInfo().getCurrent() == 1) {
				return new MessageDetailDto(false, "没有此类消息或消息已被删除，请刷新重试！", messageInfoDto.getPageInfo());
			}
			return new MessageDetailDto(true, "没有更多消息！", messageInfoDto.getPageInfo());
		}

		// 设置消息已读
		List<Long> messageIdList = new ArrayList<>(); // 消息ID集合(标记为已读使用)
		for (MessageNotify messageNotifyTemp : messageNotifyList) {
			messageIdList.add(messageNotifyTemp.getId());
		}
		if (!CollectionUtils.isEmpty(messageIdList)) {
			messageNotifyManager.modifyMessageReadStatus(messageIdList, IsReadEnum.READ);
		}

		return new MessageDetailDto(true, "", videoInfoDto, videoCommentInfoDto, messageInfoDtoList,
				messageInfoDto.getPageInfo());
	}

	private List<MessageNotify> getMessageNotifyList(MessageInfoDto messageInfoDto) {
		List<MessageNotify> messageNotifyList = new ArrayList<>();
		MessageNotify messageNotify = new MessageNotify(messageInfoDto.getMessageType(), messageInfoDto.getUserId(),
				messageInfoDto.getVideoId(), messageInfoDto.getCommentId()); // 一级评论ID
		messageNotifyList = messageNotifyManager.queryMessageNotifyList(messageNotify, messageInfoDto.getPageInfo(),
				BooleanEnum.TRUE);
		if (CollectionUtil.isEmpty(messageNotifyList)) {
			logger.warn("query message notify list is empty, userId = " + messageInfoDto.getUserId() + ", videoId="
					+ messageInfoDto.getVideoId() + ", messageType = " + messageInfoDto.getMessageType());
		}
		return messageNotifyList;
	}

	private void handleCommentInterActMessage(List<MessageNotify> messageNotifyList,
			List<MessageInfoDto> messageInfoDtoList, long userId) {
		for (MessageNotify messageNotify : messageNotifyList) {
			if (null != messageNotify.getCommentId() && 0 != messageNotify.getCommentId()) {
				if (null == videoCommentManager.queryVideoCommentById(messageNotify.getCommentId(),
						VideoCommentStatusEnum.NORMAL)) { // 二级评论
					logger.warn("query my message by type, query 2nd videoCommentInfo is empty, " + messageNotify);
					continue;
				}
			}

			MessageInfoDto messageInfoDto = new MessageInfoDto();
			if (messageNotify.getFromUserId() == 0) { // 来自系统
				messageInfoDto = new UserInfo().convertMessageInfoDto(messageInfoDto, true);
			} else {
				UserInfo fromUser = userInfoManager.queryUserInfoById(messageNotify.getFromUserId());
				if (null == fromUser) {
					logger.error("query my message by type, fromuser is empty, userId=" + userId + " fromuserId = "
							+ messageNotify.getFromUserId());
					continue;
				}
				messageInfoDto = fromUser.convertMessageInfoDto(messageInfoDto, false);
			}
			messageInfoDto = messageNotify.convertMessageInfoDto(messageInfoDto);

			// 评论点赞数
			messageInfoDto.setNum(videoCommentGreatManager.countVideoCommentGreat(
					new VideoCommentGreat(messageNotify.getCommentId(), GreatStatusEnum.GREAT)));
			boolean isGreat = videoCommentGreatManager.countVideoCommentGreat(
					new VideoCommentGreat(messageNotify.getCommentId(), userId, GreatStatusEnum.GREAT)) > 0;
			messageInfoDto.setIsGreat(isGreat ? GreatStatusEnum.GREAT.getCode() : GreatStatusEnum.UNGREAT.getCode());

			// 二级评论，需要封装当前评论的父评论信息
			if (null != messageNotify.getParentCommentId() && 0 != messageNotify.getParentCommentId()) {
				VideoComment videoComment = videoCommentManager
						.queryVideoCommentById(messageNotify.getParentCommentId(), VideoCommentStatusEnum.NORMAL);
				if (null != videoComment) {
					messageInfoDto.setParentTitle("");
					messageInfoDto.setParentContent(videoComment.getContent());
					UserInfo commentUserInfo = userInfoManager.queryUserInfoById(videoComment.getUserId());
					if (null != commentUserInfo) {
						messageInfoDto.setParentUserId(commentUserInfo.getId());
						messageInfoDto.setParentNickName(commentUserInfo.getNickName());
						messageInfoDto.setParentHeadImgUrl(commentUserInfo.getHeadImgUrl());
					}
				}
			}

			messageInfoDtoList.add(messageInfoDto);
		}
	}

	private void handleSellerBeCommentMessage(List<MessageNotify> messageNotifyList,
			List<MessageInfoDto> messageInfoDtoList, long userId) {
		for (MessageNotify messageNotify : messageNotifyList) {
			if (null != messageNotify.getCommentId() && 0 != messageNotify.getCommentId()) {
				if (null == videoCommentManager.queryVideoCommentById(messageNotify.getCommentId(),
						VideoCommentStatusEnum.NORMAL)) { // 二级评论
					logger.warn("query my message by type, query 2nd videoCommentInfo is empty, " + messageNotify);
					continue;
				}
			}

			MessageInfoDto messageInfoDto = new MessageInfoDto();
			if (messageNotify.getFromUserId() == 0) { // 来自系统
				messageInfoDto = new UserInfo().convertMessageInfoDto(messageInfoDto, true);
			} else {
				UserInfo fromUser = userInfoManager.queryUserInfoById(messageNotify.getFromUserId());
				if (null == fromUser) {
					logger.error("query my message by type, fromuser is empty, userId=" + userId + " fromuserId = "
							+ messageNotify.getFromUserId());
					continue;
				}
				messageInfoDto = fromUser.convertMessageInfoDto(messageInfoDto, false);
			}
			messageInfoDto = messageNotify.convertMessageInfoDto(messageInfoDto);

			// 评论点赞数
			messageInfoDto.setNum(videoCommentGreatManager.countVideoCommentGreat(
					new VideoCommentGreat(messageNotify.getCommentId(), GreatStatusEnum.GREAT)));
			boolean isGreat = videoCommentGreatManager.countVideoCommentGreat(
					new VideoCommentGreat(messageNotify.getCommentId(), userId, GreatStatusEnum.GREAT)) > 0;
			messageInfoDto.setIsGreat(isGreat ? GreatStatusEnum.GREAT.getCode() : GreatStatusEnum.UNGREAT.getCode());

			messageInfoDtoList.add(messageInfoDto);
		}
	}

	private void handleSystemMessage(List<MessageNotify> messageNotifyList, List<MessageInfoDto> messageInfoDtoList,
			long userId) {
		for (MessageNotify messageNotify : messageNotifyList) {
			MessageInfoDto messageInfoDto = new MessageInfoDto();
			if (messageNotify.getFromUserId() == 0) { // 来自系统
				messageInfoDto = new UserInfo().convertMessageInfoDto(messageInfoDto, true);
			} else {
				continue;
			}
			messageInfoDto = messageNotify.convertMessageInfoDto(messageInfoDto);

			messageInfoDto.setNum(0); // 评论点赞数
			messageInfoDto.setIsGreat(GreatStatusEnum.UNGREAT.getCode()); // 是否点赞标志

			messageInfoDtoList.add(messageInfoDto);
		}
	}

	private Result getVideoCommentInfoDto(Long secondParentCommentId, long userId) throws MainException {
		if (null == secondParentCommentId || 0 == secondParentCommentId) {
			throw new ParamInvalidException("评论ID不能为空");
		}
		VideoComment videoComment = videoCommentManager.queryVideoCommentById(secondParentCommentId,
				VideoCommentStatusEnum.NORMAL);
		if (null == videoComment) { // 一级评论
			logger.warn("query my message by type, 1st videoCommentInfo is null, commentId = " + secondParentCommentId);
			return new Result(false, "该评论已经删除！");
		}
		VideoCommentInfoDto videoCommentInfoDto = videoComment.convert();

		videoCommentInfoDto.setGreatNum(videoCommentGreatManager
				.countVideoCommentGreat(new VideoCommentGreat(videoComment.getId(), GreatStatusEnum.GREAT)));
		boolean isGreat = videoCommentGreatManager
				.countVideoCommentGreat(new VideoCommentGreat(videoComment.getId(), userId, GreatStatusEnum.GREAT)) > 0;
		videoCommentInfoDto.setIsGreat(isGreat ? GreatStatusEnum.GREAT.getCode() : GreatStatusEnum.UNGREAT.getCode());
		UserInfo commentUserInfo = userInfoManager.queryUserInfoById(videoComment.getUserId());
		if (null != commentUserInfo) {
			videoCommentInfoDto = commentUserInfo.convertVideoCommentInfoDto(videoCommentInfoDto);
		} else {
			logger.error("query my message by type error, commentUserInfo is empty. userId=" + userId + ", commentId="
					+ secondParentCommentId + ", commentUserId = " + videoComment.getUserId());
		}
		return new Result(true, "", videoCommentInfoDto);
	}

	private Result getVideoInfoDto(Long videoId) throws MainException {
		if (null == videoId || 0 == videoId) {
			throw new ParamInvalidException("视频ID不能为空");
		}
		VideoInfo videoInfo = videoInfoManager.queryVideoInfoByIdAndStatus(videoId, VideoStatusEnum.ONLINE);
		if (null == videoInfo) {
			logger.warn("query videoInfo is empty, videoId = " + videoId);
			return new Result(false, "该视频已经删除！");
		}
		return new Result(true, "", videoInfo.convert());
	}
}
