package com.baoke.push.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.push.dao.SendPushDetailDao;
import com.baoke.push.domain.SendPushDetail;
import com.baoke.push.manager.SendPushDetailManager;

@Component
@DataSource("miscDataSource")
public class SendPushDetailManagerImpl implements SendPushDetailManager {

	@Resource
	private SendPushDetailDao sendPushDetailDao;

	@Override
	public long addSendPushDetail(SendPushDetail sendPushDetail) {
		return sendPushDetailDao.addSendPushDetail(sendPushDetail);
	}

}
