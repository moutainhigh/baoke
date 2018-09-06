package com.baoke.trade.manager;

import com.baoke.trade.domain.OrderPay;

/**
 * OrderPayManager
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:06:50
 */
public interface OrderPayManager {

	/**
	 * 根据parentOrderNo查询订单
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:07:19
	 */
	OrderPay queryOrderPayByParentOrderNo(String parentOrderNo);

	/**
	 * 根据parentOrderNo查询订单
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:07:28
	 */
	long addOrderPay(OrderPay orderPay);

	/**
	 * 修改订单状态和支付方式
	 * 
	 * @author: wyj
	 * @date: 2018年6月23日 上午10:49:42
	 */
	int modifyOrderPayTypeAndStatus(OrderPay orderPay);

	/**
	 * 修改订单收货地址、运费和支付方式
	 * 
	 * @author zjj
	 * @date 2018年7月7日 下午4:46:08
	 * @param orderPay
	 * @return
	 */
	int modifyOrderPayTypeAndAddressAndPostage(OrderPay orderPay);

	/**
	 * 修改订单总价格
	 * 
	 * @author zjj
	 * @date 2018年7月11日 下午2:16:53
	 * @param orderPay
	 * @return
	 */
	int modifyOrderPayTotalPrice(OrderPay orderPay);

}
