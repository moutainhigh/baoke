package com.baoke.trade.manager;

import java.util.Date;

import com.baoke.trade.domain.OrderRemindSendItem;

/**
 * 提醒发货
 * 
 * @author zjj
 * @date 2018年7月20日 下午2:32:15
 */
public interface OrderRemindSendItemManager {

	/**
	 * 查询当天提醒记录条数
	 * 
	 * @author zjj
	 * @date 2018年7月20日 下午3:39:17
	 * @param orderNo
	 * @param firstTimeOfToday
	 * @return
	 */
	int countTodayRemindRecord(String orderNo, Date firstTimeOfToday);

	/**
	 * 添加提醒记录
	 * 
	 * @author zjj
	 * @date 2018年7月20日 下午4:11:51
	 * @param orderRemindSendItem
	 * @return
	 */
	long addOrderRemindSendItem(OrderRemindSendItem orderRemindSendItem);

}
