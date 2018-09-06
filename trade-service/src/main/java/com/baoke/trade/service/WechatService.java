package com.baoke.trade.service;

import com.baoke.common.domain.result.Result;

/**
 * 微信支付
 * 
 * @author zjj
 * @date 2018年7月14日 上午11:34:51
 */
public interface WechatService {

	/**
	 * 订单支付
	 * 
	 * @author zjj
	 * @date 2018年7月9日 下午3:42:07
	 * @param parentOrderNo
	 *            父订单号，对应out_trade_no
	 * @param body
	 *            描述：APP名称-商品概述
	 * @param totalFee
	 *            费用，单位是分
	 * @param ip
	 *            用户IP
	 */
	Result wechatPay(String parentOrderNo, String body, int totalFee, String ip) throws Exception;

	/**
	 * 订单查询
	 * 
	 * @author zjj
	 * @date 2018年7月25日 下午8:46:01
	 * @param transactionId
	 *            微信订单号
	 * @param parentOrderNo
	 *            父订单号，对应out_trade_no
	 * @return
	 * @throws Exception
	 */
	Result queryOrder(String transactionId, String parentOrderNo) throws Exception;

}
