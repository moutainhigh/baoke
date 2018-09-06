package com.baoke.sms.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.sms.dao.SendSmsDetailDao;
import com.baoke.sms.domain.SendSmsDetail;
import com.baoke.sms.manager.SendSmsDetailManager;

/**
 * 保存短息记录
 * 
 * @author ljj
 * @date: 2018年6月22日 下午12:45:57
 */
@Component
@DataSource("miscDataSource")
public class SendSmsDetailManagerImpl implements SendSmsDetailManager {
	@Resource
	private SendSmsDetailDao sendSmsDetailDao;

	@Override
	public int addSendSmsDetail(SendSmsDetail sendSmsDetail) {

		return sendSmsDetailDao.addSendSmsDetail(sendSmsDetail);
	}

}
