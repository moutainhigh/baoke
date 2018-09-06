package com.baoke.trade.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.trade.domain.Order;

/**
 * 订单Dao
 *
 * @author zjj
 * @date 2018年7月4日 下午12:55:57
 */
public interface OrderDao {

	List<Order> queryMyOrderByStatus(@Param("order") Order order, @Param("statusArray") Integer[] statusArray,
			@Param("pageInfo") PageInfo pageInfo);

	List<Order> queryOrderByStatus(int code);

	List<Order> queryOrderByParentOrderNo(String parentOrderNo);

	Order queryOrderByOrderNo(String orderNo);

	List<Order> querySellerOrderList(@Param("order") Order order, @Param("userIds") String userIds,
			@Param("parentOrderNos") String parentOrderNos, @Param("pageInfo") PageInfo pageInfo,
			@Param("startTime") Date startTime, @Param("endTime") Date endTime);

	int countSellerOrderList(@Param("order") Order order, @Param("userIds") String userIds,
			@Param("parentOrderNos") String parentOrderNos, @Param("startTime") Date startTime,
			@Param("endTime") Date endTime);

	int querySellerOrderTotalPrice(@Param("order") Order order, @Param("userIds") String userIds,
			@Param("parentOrderNos") String parentOrderNos, @Param("startTime") Date startTime,
			@Param("endTime") Date endTime);

	int queryLastdayOrderNum(@Param("sellerId") long sellerId, @Param("status") Integer status);

	int addBatchOrder(List<Order> orderList);

	int modifyOrderStatus(@Param("id") long id, @Param("status") int status);

	int modifyOrderStatusByOrderNo(@Param("orderNo") String orderNo, @Param("status") int status);

	int modifyOrderPriceAndNum(Order order);

	int modifyBatchOrderPostageAndAddress(List<Order> orderList);

}
