package com.baoke.trade.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.util.MoneyUtil;
import com.baoke.common.util.api.json.JsonApiUtil;
import com.baoke.trade.constant.AlipayStatusEnum;
import com.baoke.trade.constant.OrderPayStatusEnum;
import com.baoke.trade.constant.WeChatResultCode;
import com.baoke.trade.domain.PayAlipayLog;
import com.baoke.trade.domain.PayWechatLog;
import com.baoke.trade.manager.PayAlipayLogManager;
import com.baoke.trade.manager.PayWechatLogManager;
import com.baoke.trade.service.PayLogService;

@Service("payLogService")
public class PayLogServiceImpl implements PayLogService {

	private static final Logger logger = Logger.getLogger(PayLogServiceImpl.class);

	@Resource
	private PayWechatLogManager payWechatLogManager;
	@Resource
	private PayAlipayLogManager payAlipayLogManager;

	@Override
	public PayAlipayLog addPayAlipayLog(Map<String, String> params, Long userId) {
		PayAlipayLog payAlipayLog = new PayAlipayLog();
		String tradeStatus = params.get("trade_status");
		Integer status = OrderPayStatusEnum.FAIL.getCode();
		if (AlipayStatusEnum.TRADE_SUCCESS.getCode().equals(tradeStatus)
				|| AlipayStatusEnum.TRADE_FINISHED.getCode().equals(tradeStatus)) {
			status = OrderPayStatusEnum.SUCCESS.getCode();
		}

		payAlipayLog.setUserId(userId);
		payAlipayLog.setParentOrderNo(params.get("out_trade_no"));
		payAlipayLog.setSubject(params.get("subject"));
		payAlipayLog.setTradeStatus(tradeStatus);
		payAlipayLog.setStatus(status);
		payAlipayLog.setTotalAmount(MoneyUtil.changeY2F(params.get("total_amount")));
		payAlipayLog.setTradeNo(params.get("trade_no"));
		payAlipayLog.setBuyerId(params.get("buyer_id"));
		payAlipayLog.setBuyerLogonId(params.get("buyer_logon_id"));
		payAlipayLog.setPaySellerId(params.get("seller_id"));
		payAlipayLog.setPaySellerEmail(params.get("seller_email"));
		payAlipayLog.setReceiptAmount(params.get("receipt_amount"));
		payAlipayLog.setBuyerPayAmount(params.get("buyer_pay_amount"));
		payAlipayLog.setPayTotalAmount(params.get("total_amount"));
		payAlipayLog.setAppId(params.get("app_id"));
		payAlipayLog.setCharset(params.get("charset"));
		logger.info("save alipaynotify info, [alinotify-json]:" + JsonApiUtil.convertToJson(params) + ";[payAlipayLog]:"
				+ JsonApiUtil.convertToJson(payAlipayLog));
		payAlipayLog.setId(payAlipayLogManager.addPayAlipayLog(payAlipayLog));

		return payAlipayLog;
	}

	@Override
	public PayWechatLog addPayWechatLog(Map<String, String> params, Long userId) {
		PayWechatLog payWechatLog = new PayWechatLog();

		String resultCode = params.get("result_code");
		Integer status = OrderPayStatusEnum.FAIL.getCode();
		if (WeChatResultCode.SUCCESS.getCode().equals(resultCode)) {
			status = OrderPayStatusEnum.SUCCESS.getCode();
		}

		payWechatLog.setUserId(userId);
		payWechatLog.setParentOrderNo(params.get("out_trade_no"));
		payWechatLog.setOpenId(params.get("openid"));
		payWechatLog.setAppid(params.get("appid"));
		payWechatLog.setStatus(status);
		payWechatLog.setMchId(params.get("mch_id"));
		payWechatLog.setResultCode(resultCode);
		payWechatLog.setNonceStr(params.get("nonce_str"));
		payWechatLog.setSign(params.get("sign"));
		payWechatLog.setTotalFee(Integer.valueOf(params.get("total_fee")));
		payWechatLog.setTradeType(params.get("trade_type"));
		payWechatLog.setTransactionId(params.get("transaction_id"));
		payWechatLog.setTimeEnd(params.get("time_end"));

		logger.info("save wechatpaynotify info, [wechatnotify-json]:" + JsonApiUtil.convertToJson(params)
				+ ";[payWechatLog]:" + JsonApiUtil.convertToJson(payWechatLog));
		payWechatLog.setId(payWechatLogManager.addPayWechatLog(payWechatLog));
		return payWechatLog;
	}

}
