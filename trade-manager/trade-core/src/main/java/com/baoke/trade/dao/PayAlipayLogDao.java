package com.baoke.trade.dao;

import com.baoke.trade.domain.PayAlipayLog;

/**
 * 支付宝支付记录dao
 * 
 * @author: wyj
 * @date: 2018年6月21日 下午6:35:09
 */
public interface PayAlipayLogDao {

	int addPayAlipayLog(PayAlipayLog payAlipayLog);

}
