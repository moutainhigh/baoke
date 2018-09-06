package com.baoke.trade.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.OrderPayDao;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.manager.OrderPayManager;

/**
 * OrderPayManager实现
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:13:10
 */
@Component
@DataSource("coreDataSource")
public class OrderPayManagerImpl implements OrderPayManager {

	@Resource
	private OrderPayDao orderPayDao;

	@Override
	public OrderPay queryOrderPayByParentOrderNo(String parentOrderNo) {
		return orderPayDao.queryOrderPayByParentOrderNo(parentOrderNo);
	}

	@Override
	public long addOrderPay(OrderPay orderPay) {
		orderPayDao.addOrderPay(orderPay);
		return orderPay.getId();
	}

	@Override
	public int modifyOrderPayTypeAndStatus(OrderPay orderPay) {
		return orderPayDao.modifyOrderPayTypeAndStatus(orderPay);
	}

	@Override
	public int modifyOrderPayTypeAndAddressAndPostage(OrderPay orderPay) {
		return orderPayDao.modifyOrderPayTypeAndAddressAndPostage(orderPay);
	}

	@Override
	public int modifyOrderPayTotalPrice(OrderPay orderPay) {
		return orderPayDao.modifyOrderPayTotalPrice(orderPay);
	}

}
