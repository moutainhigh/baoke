package com.baoke.interact.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.IsReadEnum;
import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.dao.MessageNotifyDao;
import com.baoke.interact.domain.MessageNotify;
import com.baoke.interact.manager.MessageNotifyManager;

/**
 * 消息通知manager实现
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午4:45:26
 */
@Component
@DataSource("interactDataSource")
public class MessageNotifyManagerImpl implements MessageNotifyManager {

	@Resource
	private MessageNotifyDao messageNotifyDao;

	@Override
	public List<MessageNotify> queryMessageNotifyList(MessageNotify messageNotify, PageInfo pageInfo,
			BooleanEnum booleanEnum) {
		List<MessageNotify> messageNotifieList = new ArrayList<>();
		switch (MessageTypeEnum.getByCode(messageNotify.getMessageType())) {
		case SYSTEM:
			messageNotifieList = messageNotifyDao.querySystemMessageNotifyList(messageNotify.getMessageType(),
					messageNotify.getUserId(), pageInfo, booleanEnum.getCode());
			break;
		case SELLER_BE_FOCUS:
			messageNotifieList = messageNotifyDao.querySellerBeFocusMessageNotifyList(messageNotify.getMessageType(),
					messageNotify.getUserId(), pageInfo, booleanEnum.getCode());
			break;
		case SELLER_BE_GREAT:
			messageNotifieList = messageNotifyDao.querySellerBeGreatMessageNotifyList(messageNotify.getMessageType(),
					messageNotify.getUserId(), messageNotify.getVideoId(), pageInfo, booleanEnum.getCode());
			break;
		case SELLER_BE_COMMENT:
			messageNotifieList = messageNotifyDao.querySellerBeCommentMessageNotifyList(messageNotify.getMessageType(),
					messageNotify.getUserId(), messageNotify.getVideoId(), pageInfo, booleanEnum.getCode());
			break;
		case COMMENT_INTERACT:
			messageNotifieList = messageNotifyDao.queryCommentInteractMessageNotifyList(messageNotify.getMessageType(),
					messageNotify.getUserId(), messageNotify.getSecondParentCommentId(), pageInfo,
					booleanEnum.getCode());
			break;
		case COMMENT_GREAT:
			messageNotifieList = messageNotifyDao.queryCommentGreatMessageNotifyList(messageNotify.getMessageType(),
					messageNotify.getUserId(), messageNotify.getSecondParentCommentId(), pageInfo,
					booleanEnum.getCode());
			break;
		default:
			break;
		}
		return messageNotifieList;
	}

	@Override
	public MessageNotify queryLastMessageNotify(MessageNotify messageNotify, BooleanEnum booleanEnum) {
		return messageNotifyDao.queryLastMessageNotify(messageNotify.getMessageType(), messageNotify.getUserId(),
				messageNotify.getVideoId(), messageNotify.getSecondParentCommentId(), booleanEnum.getCode());
	}

	@Override
	public int countUnReadMessageNotify(MessageNotify messageNotify, BooleanEnum booleanEnum) {
		return messageNotifyDao.countUnreadMessageNotify(messageNotify.getMessageType(), messageNotify.getUserId(),
				messageNotify.getVideoId(), messageNotify.getSecondParentCommentId(), booleanEnum.getCode());
	}

	@Override
	public int countMessageNotifyByUserIdAndIsRead(long userId, int isRead) {
		return messageNotifyDao.countMessageNotifyByUserIdAndIsRead(userId, isRead);
	}

	@Override
	public MessageNotify queryMessageNotifyById(long id) {
		return messageNotifyDao.queryMessageNotifyById(id);
	}

	@Override
	public long addMessageNotify(MessageNotify messageNotify) {
		messageNotifyDao.addMessageNotify(messageNotify);
		return messageNotify.getId();
	}

	@Override
	public int modifyMessageReadStatus(List<Long> idList, IsReadEnum ReadStatus) {
		return messageNotifyDao.modifyMessageReadStatus(idList, ReadStatus.getCode());
	}

}
