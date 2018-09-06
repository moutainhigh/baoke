package com.baoke.trade.manager.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.OrderRemindSendItemDao;
import com.baoke.trade.domain.OrderRemindSendItem;
import com.baoke.trade.manager.OrderRemindSendItemManager;

/**
 * 提醒发货
 * 
 * @author zjj
 * @date 2018年7月20日 下午2:36:57
 */
@Component
@DataSource("coreDataSource")
public class OrderRemindSendItemManagerImpl implements OrderRemindSendItemManager {

	@Resource
	private OrderRemindSendItemDao orderRemindSendItemDao;

	@Override
	public int countTodayRemindRecord(String orderNo, Date firstTimeOfToday) {
		return orderRemindSendItemDao.countTodayRemindRecord(orderNo, firstTimeOfToday);
	}

	@Override
	public long addOrderRemindSendItem(OrderRemindSendItem orderRemindSendItem) {
		orderRemindSendItemDao.addOrderRemindSendItem(orderRemindSendItem);
		return orderRemindSendItem.getId();
	}

}
