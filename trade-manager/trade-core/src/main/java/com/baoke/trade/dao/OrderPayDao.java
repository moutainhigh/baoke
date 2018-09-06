package com.baoke.trade.dao;

import com.baoke.trade.domain.OrderPay;

/**
 * 订单Dao
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:10:12
 */
public interface OrderPayDao {

	OrderPay queryOrderPayByUserId(long userId);

	OrderPay queryOrderPayByParentOrderNo(String parentOrderNo);

	int addOrderPay(OrderPay orderPay);

	int modifyOrderPayTypeAndStatus(OrderPay orderPay);

	int modifyOrderPayTypeAndAddressAndPostage(OrderPay orderPay);

	int modifyOrderPayTotalPrice(OrderPay orderPay);

}
