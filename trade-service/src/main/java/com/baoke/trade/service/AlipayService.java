package com.baoke.trade.service;

import com.alipay.api.AlipayApiException;

/**
 * 支付宝支付
 * 
 * @author zjj
 * @date 2018年7月14日 上午11:34:35
 */
public interface AlipayService {

	/**
	 * 发起app支付
	 * 
	 * @author: zjj
	 * @date: 2018年7月14日 上午午11:23:47
	 * @param tradeNo
	 *            支付订单号
	 * @param subject
	 *            支付标题
	 * @param amount
	 *            支付金额
	 */
	String payOrder(String tradeNo, String subject, String amount) throws AlipayApiException;

	/**
	 * 关闭支付订单
	 * 
	 * @author: wyj
	 * @date: 2018年6月20日 下午4:36:28
	 * @param operatorId
	 *            操作员id
	 * @param tradeNo
	 *            支付订单id
	 */
	String closeOrder(String operatorId, String tradeNo) throws AlipayApiException;

	/**
	 * 查询支付状态
	 * 
	 * @author: wyj
	 * @date: 2018年6月20日 下午4:39:39
	 */
	String queryOrder(String operatorId, String tradeNo) throws AlipayApiException;

	/**
	 * 订单退款
	 * 
	 * @author: wyj
	 * @date: 2018年6月20日 下午4:46:26
	 */
	String refundOrder(String requestNo, String amount, String tradeNo) throws AlipayApiException;

	/**
	 * 退款查询
	 * 
	 * @author: wyj
	 * @date: 2018年6月20日 下午4:49:35
	 * @param requestNo
	 *            退款申请号
	 * @param tradeNo
	 *            支付订单号
	 */
	String refundQuery(String requestNo, String tradeNo) throws AlipayApiException;

	/**
	 * 对账单下载
	 * 
	 * @author: wyj
	 * @date: 2018年6月20日 下午4:56:08
	 * @param date
	 *            账单日期 yyyy-MM-dd或yyyy-MM
	 */
	String queryBill(String date) throws AlipayApiException;

}
