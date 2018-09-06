package com.baoke.push.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.IsReadEnum;
import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.interact.constant.MessageInfoSortEnum;
import com.baoke.interact.domain.MessageInfo;
import com.baoke.interact.domain.MessageNotify;
import com.baoke.interact.manager.MessageInfoManager;
import com.baoke.interact.manager.MessageNotifyManager;
import com.baoke.push.service.SiteMessageSendService;

@Service("siteMessageSendService")
public class SiteMessageSendServiceImpl implements SiteMessageSendService {

	private static final Logger logger = Logger.getLogger(SiteMessageSendServiceImpl.class);

	@Resource
	private MessageNotifyManager messageNotifyManager;

	@Resource
	private MessageInfoManager messageInfoManager;

	@Override
	public void addSiteMessage(SiteMessage siteMessage) {
		if (null == siteMessage) {
			logger.error("addSiteMessage error, siteMessage is null, siteMessage = " + siteMessage);
			return;
		}

		MessageTypeEnum messageTypeEnum = siteMessage.getMessageTypeEnum();
		Long userId = siteMessage.getToUserId();
		Long fromUserId = siteMessage.getFromUserId();
		Long secondParentCommentId = siteMessage.getSecondParentCommentId();
		if (null == messageTypeEnum || null == userId || null == fromUserId || null == secondParentCommentId) {
			logger.error("addSiteMessage error, userId or fromUserId or secondParentCommentId is null, siteMessage = "
					+ siteMessage);
			return;
		}

		MessageNotify messageNotify = new MessageNotify();
		messageNotify.setMessageType(messageTypeEnum.getCode());
		messageNotify.setFromUserId(siteMessage.getFromUserId());
		messageNotify.setUserId(siteMessage.getToUserId());
		messageNotify.setVideoId(siteMessage.getVideoId());
		messageNotify.setCommentId(siteMessage.getCommentId());
		messageNotify.setParentCommentId(siteMessage.getParentCommentId());
		messageNotify.setSecondParentCommentId(secondParentCommentId);
		messageNotify.setTitle(siteMessage.getTitle());
		messageNotify.setContent(siteMessage.getContent());
		messageNotify.setIsRead(IsReadEnum.UN_READ.getCode());
		messageNotify.setStatus(BooleanEnum.TRUE.getCode());
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setMessageType(messageTypeEnum.getCode());
		messageInfo.setUserId(siteMessage.getToUserId());
		messageInfo.setVideoId(siteMessage.getVideoId());
		messageInfo.setCommentId(secondParentCommentId); // 以一级评论分组
		messageInfo.setNum(1);
		messageInfo.setSort(messageTypeEnum.equals(MessageTypeEnum.SYSTEM) ? MessageInfoSortEnum.SYSTEM.getCode()
				: MessageInfoSortEnum.OTHERS.getCode());

		try {
			// 插入数据到messageNotify表
			messageNotifyManager.addMessageNotify(messageNotify);
			// 当发信人和收信人是同一人时，则不向messageInfo表中插入数据或修改数据
			if (!userId.equals(fromUserId)) {
				// (messageType、userId、videoId、commentId)组合查询，没有记录则新增，有记录则num++
				MessageInfo messageInfoTemp = messageInfoManager.queryMessageInfo(messageInfo);
				if (null == messageInfoTemp) { // 插入数据
					messageInfoManager.addMessageInfo(messageInfo);
				} else { // num++
					messageInfoManager.modifyMessageNum(messageInfoTemp.getId(), (messageInfoTemp.getNum() + 1));
				}

			}
		} catch (Exception e) {
			logger.error("addSiteMessage error, save data to database error, messageNotify = " + messageNotify
					+ " , messageInfo = " + messageInfo, e);
		}

	}

}
