package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.dao.MessageInfoDao;
import com.baoke.interact.domain.MessageInfo;
import com.baoke.interact.manager.MessageInfoManager;

/**
 * MessageInfoManager实现类
 * 
 * @author zjj
 * @date 2018年7月12日 下午5:29:54
 */
@Component
@DataSource("interactDataSource")
public class MessageInfoManagerImpl implements MessageInfoManager {

	@Resource
	private MessageInfoDao messageInfoDao;

	@Override
	public List<MessageInfo> queryMyMessageInfo(Long userId, PageInfo pageInfo) {
		return messageInfoDao.queryMyMessageInfo(userId, pageInfo);
	}

	@Override
	public MessageInfo queryMessageInfo(MessageInfo messageInfo) {
		return messageInfoDao.queryMessageInfo(messageInfo);
	}

	@Override
	public long addMessageInfo(MessageInfo messageInfo) {
		messageInfoDao.addMessageInfo(messageInfo);
		return messageInfo.getId();
	}

	@Override
	public int modifyMessageNum(Long id, int num) {
		return messageInfoDao.modifyMessageNum(id, num);
	}

}
