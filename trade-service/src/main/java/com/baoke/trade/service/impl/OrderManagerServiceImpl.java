package com.baoke.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.trade.constant.AlipayStatusEnum;
import com.baoke.trade.constant.OrderPayStatusEnum;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.constant.OrderStatusEnum;
import com.baoke.trade.constant.WeChatResultCode;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderItem;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.PayAlipayLog;
import com.baoke.trade.domain.PayWechatLog;
import com.baoke.trade.manager.OrderItemManager;
import com.baoke.trade.manager.OrderManager;
import com.baoke.trade.manager.OrderPayManager;
import com.baoke.trade.service.OrderManagerService;

@Service("orderManagerService")
public class OrderManagerServiceImpl implements OrderManagerService {

	private static final Logger logger = Logger.getLogger(OrderManagerServiceImpl.class);

	@Resource
	OrderManager orderManager;

	@Resource
	private OrderItemManager orderItemManager;

	@Resource
	private OrderPayManager orderPayManager;

	@Resource
	private ItemDetailInfoManager itemDetailInfoManager;

	@Resource
	private PlatformTransactionManager transactionManager;
	@Resource
	private DefaultTransactionDefinition transactionDefinition;

	@Override
	public boolean modifyItemNumAndOrderStatus(Order order) {
		List<OrderItem> orderItemList = orderItemManager.queryOrderItemByOrderNo(order.getOrderNo());
		try {
			boolean success = false;
			// 以order为单位，开启事务
			TransactionStatus status = transactionManager.getTransaction(transactionDefinition);

			for (OrderItem orderItem : orderItemList) {
				ItemDetailInfo itemDetailInfo = itemDetailInfoManager
						.queryItemDetailInfoById(orderItem.getItemDetailId());
				if (itemDetailInfo.getTotalNum() >= orderItem.getItemTotalNum()) { // 库存足够
					itemDetailInfo.setSaleNum(itemDetailInfo.getSaleNum() + orderItem.getItemTotalNum());
					itemDetailInfo.setTotalNum(itemDetailInfo.getTotalNum() - orderItem.getItemTotalNum());

					itemDetailInfoManager.modifyItemTotalAndSaleNum(itemDetailInfo);
					success = true;
					logger.info("alipay notify modify item num, orderNo = " + order.getOrderNo() + ", parentOrderNo = "
							+ order.getParentOrderNo() + ", itemDetailId = " + orderItem.getItemDetailId()
							+ ", itemTotalNum = " + itemDetailInfo.getTotalNum() + ", orderItemNum = "
							+ orderItem.getItemTotalNum());
				} else { // 库存不足
					logger.warn("alipay notify modify item num fail, orderNo = " + order.getOrderNo()
							+ ", parentOrderNo = " + order.getParentOrderNo() + ", itemDetailId = "
							+ orderItem.getItemDetailId() + ", itemTotalNum = " + itemDetailInfo.getTotalNum()
							+ ", orderItemNum = " + orderItem.getItemTotalNum());
					success = false;
					break;
				}
			}
			if (success) {
				orderManager.modifyOrderStatus(order.getId(), OrderStatusEnum.WAIT_DELIVER);
				transactionManager.commit(status); // 以order为单位提交事务
				logger.warn("transaction commit, orderId = " + order.getId() + ", orderNo = " + order.getOrderNo());

				return true;
			} else {
				transactionManager.rollback(status); // 以order为单位回滚事务
				logger.warn("transaction rollback, orderId = " + order.getId() + ", orderNo = " + order.getOrderNo()
						+ ", message = " + OrderStatusEnum.ORDER_FAIL_NUM_LESS);
				orderManager.modifyOrderStatus(order.getId(), OrderStatusEnum.ORDER_FAIL_NUM_LESS);
			}
		} catch (Exception e) {
			orderManager.modifyOrderStatus(order.getId(), OrderStatusEnum.ORDER_FAIL);
		}
		return false;
	}

	@Override
	public boolean modifyOrderPayStatus(OrderPay orderPay, PayAlipayLog payAlipayLog) {
		boolean result = false;
		orderPay.setPayType(OrderPayTypeEnum.ALIPAY.getCode());

		if (AlipayStatusEnum.TRADE_SUCCESS.getCode().equals(payAlipayLog.getTradeStatus())
				|| AlipayStatusEnum.TRADE_FINISHED.getCode().equals(payAlipayLog.getTradeStatus())) {
			orderPay.setStatus(OrderPayStatusEnum.SUCCESS.getCode());
			result = true;
		} else {
			// 只有付款成功或付款关闭才会回调,其他状态是支付宝出错的情况,服务不做处理
			orderPay.setStatus(OrderPayStatusEnum.FAIL.getCode());
		}
		orderPayManager.modifyOrderPayTypeAndStatus(orderPay);
		return result;
	}

	@Override
	public boolean modifyOrderPayStatus(OrderPay orderPay, PayWechatLog payWechatLog) {
		boolean result = false;
		orderPay.setPayType(OrderPayTypeEnum.WECHATPAY.getCode());

		if (WeChatResultCode.SUCCESS.getCode().equals(payWechatLog.getResultCode())) {
			orderPay.setStatus(OrderPayStatusEnum.SUCCESS.getCode());
			result = true;
		} else {
			orderPay.setStatus(OrderPayStatusEnum.FAIL.getCode());
		}

		orderPayManager.modifyOrderPayTypeAndStatus(orderPay);
		return result;
	}

}
