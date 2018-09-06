package com.baoke.trade.manager;

import java.util.Date;
import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.trade.constant.OrderStatusEnum;
import com.baoke.trade.domain.Order;

/**
 * OrderManager
 *
 * @author zjj
 * @date 2018年7月4日 下午12:55:12
 */
public interface OrderManager {

	/**
	 * 分页获取order
	 * 
	 * @author zjj
	 * @date 2018年7月3日 下午5:22:27
	 * @param order
	 * @param pageInfo
	 * @return
	 */
	List<Order> queryMyOrderByStatus(Order order, Integer[] statusArray, PageInfo pageInfo);

	/**
	 * 根据订单状态查询订单列表
	 * 
	 * @author: wyj
	 * @date: 2018年7月11日 上午11:33:56
	 */
	List<Order> queryOrderByStatus(OrderStatusEnum orderStatusEnum);

	/**
	 * 通过parentOrderNo获取orderList
	 * 
	 * @author zjj
	 * @date 2018年7月4日 下午10:29:41
	 * @param parentOrderNo
	 * @return
	 */
	List<Order> queryOrderByParentOrderNo(String parentOrderNo);

	/**
	 * 通过orderNo获取order
	 * 
	 * @author zjj
	 * @date 2018年7月7日 上午9:23:55
	 * @param orderNo
	 * @return
	 */
	Order queryOrderByOrderNo(String orderNo);

	/**
	 * 查询主播订单列表
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 下午3:18:53
	 */
	List<Order> querySellerOrderList(Order order, String userIds, String parentOrderNos, PageInfo pageInfo,
			Date startTime, Date endTime);

	/**
	 * 统计主播订单总数量
	 * 
	 * @author: wyj
	 * @date: 2018年7月11日 下午4:35:38
	 */
	int countSellerOrderList(Order order, String userIds, String parentOrderNos, Date startTime, Date endTime);

	/**
	 * 查询主播的订单总总额
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 下午2:21:31
	 */
	int querySellerOrderTotalPrice(Order order, String userIds, String parentOrderNos, Date startTime, Date endTime);

	/**
	 * 
	 * 昨日订单数量
	 * 
	 * @author ljj
	 * @date: 2018年7月17日 下午9:26:44
	 * @param status
	 * @return
	 */

	int queryLastdayOrderNum(long sellerId, OrderStatusEnum orderStatusEnum);

	/**
	 * 
	 * /** 批量保存order
	 * 
	 * @author zjj
	 * @date 2018年7月3日 下午9:14:45
	 * @param orderList
	 */
	int addBatchOrder(List<Order> orderList);

	/**
	 * 修改订单状态
	 * 
	 * @author zjj
	 * @date 2018年7月4日 下午11:00:48
	 * @param id
	 * @param orderStatusEnum
	 * @return
	 */
	int modifyOrderStatus(long id, OrderStatusEnum orderStatusEnum);

	/**
	 * 根据子订单编号修改订单状态
	 * 
	 * @author zdy
	 * @date: 2018年7月10日 下午5:14:48
	 * @param orderNo
	 *            子订单编号
	 * @param status
	 *            订单状态
	 * @return
	 */
	int modifyOrderStatusByOrderNo(String orderNo, OrderStatusEnum orderStatusEnum);

	/**
	 * 修改订单商品数量和总价
	 * 
	 * @author zjj
	 * @date 2018年7月11日 下午2:22:03
	 * @param order
	 * @return
	 */
	int modifyOrderPriceAndNum(Order order);

	/**
	 * 批量修改订单地址和运费
	 * 
	 * @author zjj
	 * @date 2018年7月11日 下午7:45:35
	 * @param orderList
	 * @return
	 */
	int modifyBatchOrderPostageAndAddress(List<Order> orderList);

}
