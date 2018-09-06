package com.baoke.trade.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.PayWechatLogDao;
import com.baoke.trade.domain.PayWechatLog;
import com.baoke.trade.manager.PayWechatLogManager;

/**
 * 微信支付记录manager实现
 * 
 * @author: wyj
 * @date: 2018年6月25日 下午1:48:44
 */
@Component
@DataSource("coreDataSource")
public class PayWechatLogManagerImpl implements PayWechatLogManager {

	@Resource
	private PayWechatLogDao payWechatLogDao;

	@Override
	public long addPayWechatLog(PayWechatLog payWechatLog) {
		payWechatLogDao.addPayWechatLog(payWechatLog);
		return payWechatLog.getId();
	}

}
