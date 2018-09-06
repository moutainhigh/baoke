package com.baoke.trade.manager;

import com.baoke.trade.domain.PayAlipayLog;

/**
 * 支付宝支付记录manager
 * 
 * @author: wyj
 * @date: 2018年6月21日 下午6:33:04
 */
public interface PayAlipayLogManager {

	/**
	 * 新增支付宝支付记录
	 * 
	 * @author: wyj
	 * @date: 2018年6月22日 下午1:17:51
	 */
	long addPayAlipayLog(PayAlipayLog payAlipayLog);

}
