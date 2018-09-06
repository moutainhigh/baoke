package com.baoke.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.trade.domain.OrderItem;

/**
 * OrderItemDao
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:10:53
 */
public interface OrderItemDao {

	List<OrderItem> queryOrderItemByOrderNo(String orderNo);

	List<OrderItem> queryOrderItemByParentOrderNo(String parentOrderNo);

	List<OrderItem> queryOrderItemByOrderNos(List<String> orderNoList);

	OrderItem queryOrderItemByParentOrderNoAndItemId(@Param("parentOrderNo") String parentOrderNo,
			@Param("itemId") Long itemId, @Param("itemDetailId") Long itemDetailId);

	int addBatchOrderItem(List<OrderItem> orderItemList);

	int modifyBatchOrderItemPostage(List<OrderItem> orderItemList);

	int modifyOrderItemPriceAndNum(OrderItem orderItem);

}
