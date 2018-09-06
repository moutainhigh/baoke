package com.baoke.trade.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.trade.constant.OrderStatusEnum;
import com.baoke.trade.dao.OrderDao;
import com.baoke.trade.domain.Order;
import com.baoke.trade.manager.OrderManager;

/**
 * OrderManager实现
 *
 * @author zjj
 * @date 2018年7月4日 下午12:54:33
 */
@Component
@DataSource("coreDataSource")
public class OrderManagerImpl implements OrderManager {

	@Resource
	private OrderDao orderDao;

	@Override
	public List<Order> queryMyOrderByStatus(Order order, Integer[] statusArray, PageInfo pageInfo) {
		return orderDao.queryMyOrderByStatus(order, statusArray, pageInfo);
	}

	@Override
	public List<Order> queryOrderByStatus(OrderStatusEnum orderStatusEnum) {
		return orderDao.queryOrderByStatus(orderStatusEnum.getCode());
	}

	@Override
	public List<Order> queryOrderByParentOrderNo(String parentOrderNo) {
		return orderDao.queryOrderByParentOrderNo(parentOrderNo);
	}

	@Override
	public Order queryOrderByOrderNo(String orderNo) {
		return orderDao.queryOrderByOrderNo(orderNo);
	}

	@Override
	public List<Order> querySellerOrderList(Order order, String userIds, String parentOrderNos, PageInfo pageInfo,
			Date startTime, Date endTime) {
		return orderDao.querySellerOrderList(order, userIds, parentOrderNos, pageInfo, startTime, endTime);
	}

	@Override
	public int countSellerOrderList(Order order, String userIds, String parentOrderNos, Date startTime, Date endTime) {
		return orderDao.countSellerOrderList(order, userIds, parentOrderNos, startTime, endTime);
	}

	@Override
	public int querySellerOrderTotalPrice(Order order, String userIds, String parentOrderNos, Date startTime,
			Date endTime) {
		return orderDao.querySellerOrderTotalPrice(order, userIds, parentOrderNos, startTime, endTime);
	}

	@Override
	public int addBatchOrder(List<Order> orderList) {
		return orderDao.addBatchOrder(orderList);
	}

	@Override
	public int modifyOrderStatus(long id, OrderStatusEnum orderStatusEnum) {
		return orderDao.modifyOrderStatus(id, orderStatusEnum.getCode());
	}

	@Override
	public int modifyOrderStatusByOrderNo(String orderNo, OrderStatusEnum orderStatusEnum) {
		return orderDao.modifyOrderStatusByOrderNo(orderNo, orderStatusEnum.getCode());
	}

	@Override
	public int modifyOrderPriceAndNum(Order order) {
		return orderDao.modifyOrderPriceAndNum(order);
	}

	@Override
	public int modifyBatchOrderPostageAndAddress(List<Order> orderList) {
		return orderDao.modifyBatchOrderPostageAndAddress(orderList);
	}

	@Override
	public int queryLastdayOrderNum(long sellerId, OrderStatusEnum orderStatusEnum) {
		return orderDao.queryLastdayOrderNum(sellerId, orderStatusEnum.getCode());
	}

}
