package com.baoke.trade.service;

import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.PayAlipayLog;
import com.baoke.trade.domain.PayWechatLog;

/**
 * 订单管理
 * 
 * @author zjj
 * @date 2018年7月13日 下午9:06:01
 */
public interface OrderManagerService {

	/**
	 * 修改库存，修改Order状态，成功返回true
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午8:51:22
	 * @param order
	 * @return
	 */
	boolean modifyItemNumAndOrderStatus(Order order);

	/**
	 * 修改OrderPay状态，返回付款是否成功
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午7:49:29
	 * @param orderPay
	 * @param payAlipayLog
	 * @return
	 */
	boolean modifyOrderPayStatus(OrderPay orderPay, PayAlipayLog payAlipayLog);

	/**
	 * 修改OrderPay状态，返回付款是否成功
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午7:49:29
	 * @param orderPay
	 * @param payAlipayLog
	 * @return
	 */
	boolean modifyOrderPayStatus(OrderPay orderPay, PayWechatLog payWechatLog);

}
