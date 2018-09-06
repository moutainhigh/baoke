package com.baoke.trade.service;

import java.util.List;
import java.util.Map;

import com.baoke.common.dto.OrderListDto;
import com.baoke.common.dto.api.RemindSendItemDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.dto.seller.SellerOrderListDto;
import com.baoke.common.dto.seller.SellerOrderQueryDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderItem;
import com.baoke.trade.domain.OrderPay;

/**
 * 订单相关service
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:29:00
 */
public interface OrderService {

	/**
	 * 我的订单列表
	 * 
	 * @author wyh
	 * @date 2018年6月30日 下午3:13:09
	 * 
	 * @param orderDto
	 * @return
	 * @throws MainException
	 */
	OrderListDto queryMyOrder(OrderInfoDto orderDto) throws MainException;

	/**
	 * 订单详情
	 * 
	 * @author zjj
	 * @date 2018年7月6日 下午8:59:34
	 * @param orderDto
	 * @return
	 * @throws MainException
	 */
	OrderInfoDto queryMyOrderDetail(OrderInfoDto orderDto) throws MainException;

	/**
	 * 通用查询-主播的卖家订单列表
	 * 
	 * @author: wyj
	 * @param pageInfo
	 * @param sellerOrderQueryDto
	 * @date: 2018年7月11日 上午11:03:48
	 */
	SellerOrderListDto querySellerOrderList(SellerOrderQueryDto sellerOrderQueryDto, PageInfo pageInfo)
			throws MainException;

	/**
	 * 将orderItemList转换为OrderInfoList
	 * 
	 * @author zjj
	 * @date 2018年7月4日 下午12:25:27
	 * @param orderItemList
	 * @param orderStatusMap
	 * @return
	 */
	List<OrderInfoDto> buildOrderInfoDtoList(List<OrderItem> orderItemList, Map<String, Integer> orderStatusMap);

	/**
	 * 根据parentOrderNo查找OrderPay
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午8:50:49
	 * @param parentOrderNo
	 * @return
	 */
	OrderPay queryOrderPayByParentOrderNo(String parentOrderNo);

	/**
	 * 通过parentOrderNo查询OrderList
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午7:33:32
	 * @param parentOrderNo
	 * @return
	 */
	List<Order> queryOrderByParentOrderNo(String parentOrderNo);

	/**
	 * 提醒发货
	 * 
	 * @author zjj
	 * @date 2018年7月20日 下午2:23:40
	 * @param remindSendItemDto
	 * @return
	 * @throws MainException
	 */
	BaseResultDto remindSendItem(RemindSendItemDto remindSendItemDto) throws MainException;

	/**
	 * 确认收货
	 * 
	 * @author zdy
	 * @date: 2018年7月10日 下午4:56:36
	 * @param orderInfoDto
	 *            子订单号(orderNo)
	 * @return
	 * @throws MainException
	 */
	BaseResultDto confirmReceiveItem(OrderInfoDto orderInfoDto) throws MainException;

}
