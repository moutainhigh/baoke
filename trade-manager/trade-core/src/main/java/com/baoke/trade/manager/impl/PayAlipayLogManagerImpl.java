package com.baoke.trade.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.PayAlipayLogDao;
import com.baoke.trade.domain.PayAlipayLog;
import com.baoke.trade.manager.PayAlipayLogManager;

/**
 * 支付宝支付记录manager实现
 * 
 * @author: wyj
 * @date: 2018年6月21日 下午6:36:12
 */
@Component
@DataSource("coreDataSource")
public class PayAlipayLogManagerImpl implements PayAlipayLogManager {

	@Resource
	private PayAlipayLogDao payAlipayLogDao;

	@Override
	public long addPayAlipayLog(PayAlipayLog payAlipayLog) {
		payAlipayLogDao.addPayAlipayLog(payAlipayLog);
		return payAlipayLog.getId();
	}

}
