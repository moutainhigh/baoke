package com.baoke.trade.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.OrderItemDao;
import com.baoke.trade.domain.OrderItem;
import com.baoke.trade.manager.OrderItemManager;

/**
 * OrderItemManager实现类
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:22:36
 */
@Component
@DataSource("coreDataSource")
public class OrderItemManagerImpl implements OrderItemManager {

	@Resource
	private OrderItemDao orderItemDao;

	@Override
	public List<OrderItem> queryOrderItemByOrderNo(String orderNo) {
		return orderItemDao.queryOrderItemByOrderNo(orderNo);
	}

	@Override
	public List<OrderItem> queryOrderItemByParentOrderNo(String parentOrderNo) {
		return orderItemDao.queryOrderItemByParentOrderNo(parentOrderNo);
	}

	@Override
	public List<OrderItem> queryOrderItemByOrderNos(List<String> orderNoList) {
		return orderItemDao.queryOrderItemByOrderNos(orderNoList);
	}

	@Override
	public OrderItem queryOrderItemByParentOrderNoAndItemId(String parentOrderNo, Long itemId, Long itemDetailId) {
		return orderItemDao.queryOrderItemByParentOrderNoAndItemId(parentOrderNo, itemId, itemDetailId);
	}

	@Override
	public int addBatchOrderItem(List<OrderItem> orderItemList) {
		return orderItemDao.addBatchOrderItem(orderItemList);
	}

	@Override
	public int modifyOrderItemPriceAndNum(OrderItem orderItem) {
		return orderItemDao.modifyOrderItemPriceAndNum(orderItem);
	}

	@Override
	public int modifyBatchOrderItemPostage(List<OrderItem> orderItemList) {
		return orderItemDao.modifyBatchOrderItemPostage(orderItemList);
	}

}
