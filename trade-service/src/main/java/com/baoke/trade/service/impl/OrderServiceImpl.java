package com.baoke.trade.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.SellerIncomeStatus;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.OrderListDto;
import com.baoke.common.dto.api.RemindSendItemDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.dto.info.OrderItemInfoDto;
import com.baoke.common.dto.seller.SellerOrderDto;
import com.baoke.common.dto.seller.SellerOrderListDto;
import com.baoke.common.dto.seller.SellerOrderQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.DateUtil;
import com.baoke.common.util.DecimalUtil;
import com.baoke.common.util.MoneyUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.trade.constant.IsRemindSendItemEnum;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.constant.OrderStatusEnum;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderItem;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.OrderRemindSendItem;
import com.baoke.trade.domain.SellerIncomeAmountDetail;
import com.baoke.trade.manager.OrderItemManager;
import com.baoke.trade.manager.OrderManager;
import com.baoke.trade.manager.OrderPayManager;
import com.baoke.trade.manager.OrderRemindSendItemManager;
import com.baoke.trade.manager.SellerIncomeAmountDetailManager;
import com.baoke.trade.service.OrderService;
import com.baoke.user.domain.UserAddress;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserAddressManager;
import com.baoke.user.manager.UserInfoManager;

/**
 * 订单srvice实现
 *
 * @author wyj
 * @date 2018年7月4日 下午12:29:18
 */
@ServiceDefinition(value = "orderService")
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Resource
	UserInfoManager userInfoManager;

	@Resource
	OrderItemManager orderItemManager;

	@Resource
	OrderManager orderManager;
	@Resource
	OrderPayManager orderPayManager;

	@Resource
	UserAddressManager userAddressManager;

	@Resource
	private SendSiteManager sendSiteManager;

	@Resource
	private OrderRemindSendItemManager orderRemindSendItemManager;

	@Resource
	private SellerIncomeAmountDetailManager sellerIncomeAmountDetailManager;

	@Resource
	PlatformTransactionManager transactionManager;

	@Resource
	DefaultTransactionDefinition transactionDefinition;

	@Override
	@MethodDefinition(value = "queryMyOrder", needLogin = true)
	public OrderListDto queryMyOrder(OrderInfoDto orderDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(orderDto);

		Order order = new Order(userInfo.getId(), orderDto.getOrderStatus());
		Integer[] statusArray = new Integer[] { OrderStatusEnum.WAIT_DELIVER.getCode(),
				OrderStatusEnum.WAIT_RECEIVE.getCode(), OrderStatusEnum.RECEIVED.getCode() };

		List<Order> orderList = orderManager.queryMyOrderByStatus(order, statusArray, orderDto.getPageInfo());

		// 订单状态集合,key为子订单orderNo,value为子订单状态值
		Map<String, Integer> orderStatusMap = new HashMap<>();
		List<String> orderNoList = new ArrayList<>();
		for (Order orderTemp : orderList) {
			orderNoList.add(orderTemp.getOrderNo());
			orderStatusMap.put(orderTemp.getOrderNo(), orderTemp.getStatus());
		}

		List<OrderItem> orderItemList = orderItemManager.queryOrderItemByOrderNos(orderNoList);

		List<OrderInfoDto> orderInfoList = buildOrderInfoDtoList(orderItemList, orderStatusMap);

		return new OrderListDto(true, "", orderInfoList, orderDto.getPageInfo());
	}

	@Override
	public List<OrderInfoDto> buildOrderInfoDtoList(List<OrderItem> orderItemList,
			Map<String, Integer> orderStatusMap) {

		// orderInfoDtoMap集合,key为orderNo,value为OrderInfoDto
		Map<String, OrderInfoDto> orderInfoDtoMap = new HashMap<>();

		for (OrderItem orderItem : orderItemList) {
			OrderInfoDto orderInfoDto = orderInfoDtoMap.get(orderItem.getOrderNo());
			if (null == orderInfoDto) {
				UserInfo sellerInfo = userInfoManager.queryUserInfoById(orderItem.getSellerId());
				if (null == sellerInfo) {
					continue;
				}
				orderInfoDto = new OrderInfoDto(orderItem.getOrderNo(), orderItem.getSellerId(), "0",
						MoneyUtil.changeF2Y(orderItem.getPostage()), 0);
				orderInfoDto.setPayType(OrderPayTypeEnum.UNKNOW.getCode());
				orderInfoDto.setSellerNickName(sellerInfo.getNickName());
				Integer status = orderStatusMap.get(orderItem.getOrderNo());
				orderInfoDto.setOrderStatus(status);
				orderInfoDto.setOrderStatusDesc(OrderStatusEnum.getNameByStatus(status));
				orderInfoDto.setCreateTime(orderItem.getCreateTime().getTime());
				orderInfoDto.setIsRemindSendItem(
						getIsRemindSendItem(orderItem.getOrderNo(), orderItem.getCreateTime(), status));
				orderInfoDto.setOrderItemList(new ArrayList<OrderItemInfoDto>());
			}
			orderInfoDto.setTotalNum(orderInfoDto.getTotalNum() + orderItem.getItemTotalNum());
			orderInfoDto.setTotalPrice(MoneyUtil.add(orderInfoDto.getTotalPrice(), orderItem.getItemTotalPrice()));
			orderInfoDto.setPostage(MoneyUtil
					.changeF2Y(Math.min(MoneyUtil.changeY2F(orderInfoDto.getPostage()), orderItem.getPostage())));
			orderInfoDto.getOrderItemList().add(orderItem.convert());

			if (!orderInfoDtoMap.containsKey(orderItem.getOrderNo())) {
				orderInfoDtoMap.put(orderItem.getOrderNo(), orderInfoDto);
			}
		}

		List<OrderInfoDto> orderInfoDtoList = new ArrayList<>(orderInfoDtoMap.values());
		Collections.sort(orderInfoDtoList, new Comparator<OrderInfoDto>() {
			@Override
			public int compare(OrderInfoDto o1, OrderInfoDto o2) {
				return o1.getCreateTime() - o2.getCreateTime() > 0 ? -1 : 1;
			}
		});
		return orderInfoDtoList;
	}

	private int getIsRemindSendItem(String orderNo, Date createTime, Integer status) {

		if (OrderStatusEnum.WAIT_DELIVER.getCode() != status) {
			return IsRemindSendItemEnum.NO.getCode();
		}

		Date now = new Date();
		long pastMs = now.getTime() - createTime.getTime();
		if (pastMs < 1000 * 60 * 60 * 12) { // 支付成功12小时后可提醒发货（此处用订单创建时间代替）
			return IsRemindSendItemEnum.NO.getCode();
		}

		int count = orderRemindSendItemManager.countTodayRemindRecord(orderNo, DateUtil.getFirstTimeOfDate(now));
		if (count > 0) { // 每个自然日只能提醒一次
			return IsRemindSendItemEnum.NO.getCode();
		}

		return IsRemindSendItemEnum.YES.getCode();
	}

	@Override
	@MethodDefinition(value = "queryMyOrderDetail", needLogin = true)
	public OrderInfoDto queryMyOrderDetail(OrderInfoDto orderDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(orderDto);

		if (StringUtil.isBlank(orderDto.getOrderNo())) {
			throw new ParamInvalidException("订单编号不可为空");
		}

		String orderNo = orderDto.getOrderNo().trim();
		Order order = orderManager.queryOrderByOrderNo(orderNo);
		if (null == order) {
			logger.error("queryMyOrderDetail error, order is null, orderNo = " + orderNo);
			return new OrderInfoDto(false, "未查询到订单信息，请刷新重试");
		}
		if (!userInfo.getId().equals(order.getUserId())) {
			logger.error("queryMyOrderDetail error, orderInfo is not match to userInfo, orderNo = " + orderNo
					+ ", userId = " + userInfo.getId());
			return new OrderInfoDto(false, "订单信息与用户信息不匹配");
		}

		// 订单状态集合,key为子订单orderNo,value为子订单状态值
		Map<String, Integer> orderStatusMap = new HashMap<>();
		orderStatusMap.put(orderNo, order.getStatus());
		List<OrderItem> orderItemList = orderItemManager.queryOrderItemByOrderNo(orderNo);

		List<OrderInfoDto> orderInfoList = buildOrderInfoDtoList(orderItemList, orderStatusMap);

		if (CollectionUtils.isEmpty(orderInfoList)) {
			return new OrderInfoDto(false, "未查询到订单信息，请刷新重试");
		}
		OrderInfoDto orderInfo = orderInfoList.get(0);

		UserAddress userAddress = userAddressManager.queryUserAddressById(order.getAddressId(), BooleanEnum.TRUE);
		if (null == userAddress) {
			return new OrderInfoDto(false, "未查到地址信息，请设置收货地址");
		}
		orderInfo.setAddressInfo(userAddress.convert());
		orderInfo.setSuccess(true);

		return orderInfo;
	}

	@Override
	@MethodDefinition(value = "remindSendItem", needLogin = true)
	public BaseResultDto remindSendItem(RemindSendItemDto remindSendItemDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(remindSendItemDto);

		if (StringUtils.isBlank(remindSendItemDto.getOrderNo())) {
			throw new ParamInvalidException("订单编号不能为空");
		}

		String orderNo = remindSendItemDto.getOrderNo().trim();
		Order order = orderManager.queryOrderByOrderNo(orderNo);
		if (null == order) {
			logger.warn("remindSendItem error, order is null, userId=" + userInfo.getId() + ", orderNo=" + orderNo);
			return new BaseResultDto(false, "订单不存在，请刷新重试");
		}
		if (!userInfo.getId().equals(order.getUserId())) {
			logger.error("remindSendItem error, orderInfo is noy match to userInfo, userId=" + userInfo.getId()
					+ ", orderNo=" + orderNo);
			throw new ParamInvalidException("订单编号与用户编号不匹配");
		}

		int isRemindSendItem = getIsRemindSendItem(orderNo, order.getCreateTime(), order.getStatus());
		if (isRemindSendItem != IsRemindSendItemEnum.YES.getCode()) {
			return new BaseResultDto(false, "尚未到达催单时间，请稍后再使用该功能");
		}

		orderRemindSendItemManager.addOrderRemindSendItem(new OrderRemindSendItem(order));

		SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID, order.getSellerId(),
				null, "提醒发货", "订单" + orderNo + "未发货，收到用户催单，请尽快安排发货。");
		if (sendSiteManager.sendSite(siteMessage)) {
			return new BaseResultDto(true, "催单成功");
		} else {
			return new BaseResultDto(false, "网络异常，请稍后重试");
		}
	}

	@MethodDefinition(value = "confirmReceiveItem", needLogin = true)
	@Override
	public BaseResultDto confirmReceiveItem(OrderInfoDto orderInfoDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(orderInfoDto);

		if (StringUtils.isBlank(orderInfoDto.getOrderNo())) {
			throw new ParamInvalidException("订单编号不能为空");
		}

		String orderNo = orderInfoDto.getOrderNo().trim();
		Order order = orderManager.queryOrderByOrderNo(orderNo);
		if (null == order) {
			logger.warn("confirmReceiveItem error, order is null, userId=" + userInfo.getId() + ", orderNo=" + orderNo);
			return new BaseResultDto(false, "订单不存在，请刷新重试");
		}
		if (!userInfo.getId().equals(order.getUserId())) {
			logger.error("confirmReceiveItem error, orderInfo is noy match to userInfo, userId=" + userInfo.getId()
					+ ", orderNo=" + orderNo);
			throw new ParamInvalidException("订单编号与用户编号不匹配");
		}

		SellerIncomeAmountDetail sellerIncomeAmountDetail = sellerIncomeAmountDetailManager
				.querySellerIncomeAmountDetailByOrderNo(orderInfoDto.getOrderNo());
		if (sellerIncomeAmountDetail == null) {
			logger.error("confirmReceiveItem is error, sellerIncomeAmountDetail is null, userId=" + userInfo.getId()
					+ ", orderNo=" + orderNo);
			return new BaseResultDto(false, "数据异常，请稍后重试！");
		}

		if (sellerIncomeAmountDetail.getStatus() != SellerIncomeStatus.FREEZEN.getCode()) {
			logger.error("confirmReceiveItem is error, sellerIncomeAmountDetail status error, userId="
					+ userInfo.getId() + ", orderNo=" + orderNo + ", status=" + sellerIncomeAmountDetail.getStatus());
			return new BaseResultDto(false, "数据异常，请稍后重试");
		}

		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		try {
			orderManager.modifyOrderStatusByOrderNo(orderInfoDto.getOrderNo(), OrderStatusEnum.RECEIVED);
			sellerIncomeAmountDetailManager.modifySellerIncomeAmountDetailPayType(orderNo, SellerIncomeStatus.NORMAL);
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			logger.error("confirmReceiveItem is error, save data to database error", e);
		}

		// 站内信
		SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID, order.getSellerId(),
				null, "确认收货", "订单编号为：" + orderInfoDto.getOrderNo() + "的订单已确认收货");
		sendSiteManager.sendSite(siteMessage);

		return new BaseResultDto(true, "");
	}

	@Override
	public OrderPay queryOrderPayByParentOrderNo(String parentOrderNo) {
		return orderPayManager.queryOrderPayByParentOrderNo(parentOrderNo);
	}

	@Override
	public List<Order> queryOrderByParentOrderNo(String parentOrderNo) {
		return orderManager.queryOrderByParentOrderNo(parentOrderNo);
	}

	@Override
	public SellerOrderListDto querySellerOrderList(SellerOrderQueryDto sellerOrderQueryDto, PageInfo pageInfo)
			throws MainException {

		if (sellerOrderQueryDto.getSellerId() == null) {
			throw new ParamInvalidException("卖家id不存在");
		}
		Order order = new Order();
		order.setSellerId(sellerOrderQueryDto.getSellerId());
		if (StringUtil.isNotBlank(sellerOrderQueryDto.getOrderNo())) {
			order.setOrderNo(sellerOrderQueryDto.getOrderNo());
		}
		if (null != sellerOrderQueryDto.getStatus()) {
			order.setStatus(sellerOrderQueryDto.getStatus());
		}

		String userIds = null;
		if (StringUtil.isNotBlank(sellerOrderQueryDto.getNickName())) {
			List<UserInfo> userInfoList = userInfoManager.queryUserInfoByNickName(sellerOrderQueryDto.getNickName());
			if (CollectionUtil.isNotEmpty(userInfoList)) {
				userIds = getUserIds(userInfoList);
			} else {
				return new SellerOrderListDto(new PageInfo());
			}
		}

		String parentOrderNos = null;
		if (sellerOrderQueryDto.getStatus() != null) {
			List<Order> orderList = orderManager
					.queryOrderByStatus(OrderStatusEnum.getEnumByStatus(sellerOrderQueryDto.getStatus()));
			if (CollectionUtil.isNotEmpty(orderList)) {
				parentOrderNos = getParentOrderNos(orderList);
			} else {
				return new SellerOrderListDto(new PageInfo());
			}
		}
		Date startTime = null;
		Date endTime = null;
		if (sellerOrderQueryDto.getStartTime() != null) {
			startTime = new Date(sellerOrderQueryDto.getStartTime());
		}
		if (sellerOrderQueryDto.getEndTime() != null) {
			endTime = new Date(sellerOrderQueryDto.getEndTime());
		}
		int total = orderManager.countSellerOrderList(order, userIds, parentOrderNos, startTime, endTime);
		pageInfo.setTotal(total);
		List<Order> orderList = orderManager.querySellerOrderList(order, userIds, parentOrderNos, pageInfo, startTime,
				endTime);
		for (Order orderTemp : orderList) {
			orderTemp.setTotalItemPrice(orderTemp.getTotalItemPrice() + orderTemp.getTotalPostage());
		}
		List<SellerOrderDto> sellerOrderDtoList = convertOrderList2OrderInfoDtoList(orderList,
				sellerOrderQueryDto.getSellerId());
		int totalPrice = orderManager.querySellerOrderTotalPrice(order, userIds, parentOrderNos, startTime, endTime);
		SellerOrderListDto sellerOrderListDto = new SellerOrderListDto(sellerOrderDtoList,
				DecimalUtil.divide(totalPrice, 100).toString());
		sellerOrderListDto.setPageInfo(pageInfo);
		return sellerOrderListDto;
	}

	private List<SellerOrderDto> convertOrderList2OrderInfoDtoList(List<Order> orderList, Long sellerId) {
		// 商品列表
		List<SellerOrderDto> sellerOrderDtoList = new ArrayList<>();
		for (Order order : orderList) {
			OrderInfoDto orderInfoDto = new OrderInfoDto();
			orderInfoDto.setOrderNo(order.getOrderNo());
			orderInfoDto.setSellerId(sellerId);
			orderInfoDto.setTotalPrice(DecimalUtil.divide(order.getTotalItemPrice(), 100).toString());
			orderInfoDto.setParentOrderNo(order.getParentOrderNo());
			orderInfoDto.setTotalNum(order.getTotalItemNum());
			orderInfoDto.setPostage(DecimalUtil.divide(order.getTotalPostage(), 100).toString());
			orderInfoDto.setCreateTime(order.getCreateTime().getTime());
			orderInfoDto.setOrderStatus(order.getStatus());
			orderInfoDto.setOrderStatusDesc(OrderStatusEnum.getNameByStatus(order.getStatus()));

			UserInfo sellerInfo = userInfoManager.queryUserInfoById(sellerId);
			orderInfoDto.setSellerNickName(sellerInfo.getNickName());
			orderInfoDto.setSellerContactPhone(sellerInfo.getPhone());

			OrderPay orderPay = orderPayManager.queryOrderPayByParentOrderNo(order.getParentOrderNo());
			orderInfoDto.setPayType(orderPay.getPayType());

			orderInfoDto.setAddressInfo(new AddressInfoDto(order.getAddressId(), order.getAddressName(),
					order.getAddressPhone(), order.getAddressDetail()));

			List<OrderItem> orderItemList = orderItemManager.queryOrderItemByOrderNo(order.getOrderNo());
			List<OrderItemInfoDto> orderItemInfoDtoList = convertOrderItemList2OrderItemInfoDtoList(orderItemList);
			orderInfoDto.setOrderItemList(orderItemInfoDtoList);

			UserInfo userInfo = userInfoManager.queryUserInfoById(order.getUserId());
			sellerOrderDtoList.add(new SellerOrderDto(orderInfoDto, userInfo.getNickName()));
		}
		return sellerOrderDtoList;
	}

	private List<OrderItemInfoDto> convertOrderItemList2OrderItemInfoDtoList(List<OrderItem> orderItemList) {
		List<OrderItemInfoDto> orderItemInfoDtos = new ArrayList<>();
		for (OrderItem orderItem : orderItemList) {
			OrderItemInfoDto orderItemInfoDto = orderItem.convert();
			orderItemInfoDtos.add(orderItemInfoDto);
		}
		return orderItemInfoDtos;
	}

	private String getParentOrderNos(List<Order> orderList) {
		StringBuilder sb = new StringBuilder();
		for (Order order : orderList) {
			sb.append("'");
			sb.append(order.getParentOrderNo());
			sb.append("',");
		}
		return sb.substring(0, sb.length() - 1);
	}

	private String getUserIds(List<UserInfo> userInfoList) {
		StringBuilder sb = new StringBuilder();
		for (UserInfo userInfo : userInfoList) {
			sb.append("'");
			sb.append(userInfo.getId());
			sb.append("',");
		}
		return sb.substring(0, sb.length() - 1);
	}

}
