package com.baoke.trade.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.baoke.trade.domain.OrderRemindSendItem;

/**
 * 提醒发货
 * 
 * @author zjj
 * @date 2018年7月20日 下午2:30:25
 */
public interface OrderRemindSendItemDao {

	int countTodayRemindRecord(@Param("orderNo") String orderNo, @Param("firstTimeOfToday") Date firstTimeOfToday);

	int addOrderRemindSendItem(OrderRemindSendItem orderRemindSendItem);

}
