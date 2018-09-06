package com.baoke.extra.pay.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.internal.util.AlipaySignature;
import com.baoke.common.constant.config.AliPayConfig;
import com.baoke.common.util.api.json.JsonApiUtil;
import com.baoke.trade.constant.OrderPayStatusEnum;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.PayAlipayLog;
import com.baoke.trade.service.OrderManagerService;
import com.baoke.trade.service.OrderService;
import com.baoke.trade.service.OrderShoppingCartService;
import com.baoke.trade.service.PayLogService;
import com.baoke.trade.service.SellerIncomeRecordService;

/**
 * 支付宝支付回调
 * 
 * @author: zjj
 * @date: 2018年6月20日 下午5:44:14
 */
@Controller
public class AlipayNotifyController {

	private static Logger logger = Logger.getLogger(AlipayNotifyController.class);

	@Resource
	private OrderShoppingCartService orderShoppingCartService;
	@Resource
	private SellerIncomeRecordService sellerIncomeRecordService;
	@Resource
	private PayLogService payLogService;
	@Resource
	private OrderService orderService;
	@Resource
	private OrderManagerService orderManagerService;

	private Object objectLock = new Object();

	@ResponseBody
	@RequestMapping(value = "alipay/notify", method = RequestMethod.POST)
	public String alipayNotify(HttpServletRequest request) {
		String result = "failure";
		logger.warn("alipay notify start, params=" + request.getParameterMap());

		Map<String, String> paramMap = null;
		try {
			paramMap = getParamMap(request.getParameterMap());
			logger.warn("alipay notify start, paramMap=" + JsonApiUtil.convertToJson(paramMap));
		} catch (Exception e) {
			logger.error("alipay notify error", e);
		}

		if (null == paramMap) {
			return result;
		}

		OrderPay orderPay = orderService.queryOrderPayByParentOrderNo(paramMap.get("out_trade_no"));
		logger.info("alipay notify, " + orderPay);
		if (null == orderPay) {
			logger.error("alipay notify error, orderPay is empty. parentOrderNo = " + paramMap.get("out_trade_no")
					+ ", paramMap=" + paramMap);
			return result;
		}

		PayAlipayLog payAlipayLog = null;

		synchronized (objectLock) {
			// 避免重复处理
			if (OrderPayStatusEnum.SUCCESS.getCode() == orderPay.getStatus()) {
				result = "success";
				return result;
			}

			payAlipayLog = payLogService.addPayAlipayLog(paramMap, (null == orderPay) ? null : orderPay.getUserId());

			boolean signVerified = false;
			try {
				signVerified = AlipaySignature.rsaCheckV1(paramMap, AliPayConfig.onLinePublicKey, "UTF-8",
						AliPayConfig.signType);
			} catch (Exception e) {
				logger.error("alipay notify signature check error, " + payAlipayLog, e);
			}

			if (!signVerified) {
				logger.error("alipay notify signature check error, " + payAlipayLog);
				return result;
			}

			if (!checkAlipaynotify(payAlipayLog, orderPay)) {
				logger.error("alipay notify check error, " + payAlipayLog);
				return result;
			}

			// 数据验证无异常，即可向支付宝返回success
			result = "success";

			if (!orderManagerService.modifyOrderPayStatus(orderPay, payAlipayLog)) {
				logger.error("alipay notify error, modify order pay error, " + payAlipayLog);
				return result;
			}

			// 如果支付成功，修改商品库存，修改order订单状态，增加卖家收入明细
			if (OrderPayStatusEnum.SUCCESS.getCode() == payAlipayLog.getStatus()) {
				List<Order> orderList = orderService.queryOrderByParentOrderNo(payAlipayLog.getParentOrderNo());
				if (null == orderList) {
					logger.error("alipay notify error, order list is empty" + payAlipayLog);
				}
				for (Order order : orderList) {
					try {
						if (orderManagerService.modifyItemNumAndOrderStatus(order)) {
							sellerIncomeRecordService.addSellerIncomeRecord(order, payAlipayLog.getId(),
									OrderPayTypeEnum.ALIPAY);
						}
					} catch (Exception e) {
						logger.error("alipay notify error, modify order item error, " + payAlipayLog, e);
					}
				}
			}

		}

		// 如果是购物车付款的，则删除购物车对应记录
		orderShoppingCartService.deleteShoppingCart(orderPay);

		logger.info("alipay notify end, result = " + result + payAlipayLog);
		return result;
	}

	private boolean checkAlipaynotify(PayAlipayLog payAlipayLog, OrderPay orderPay) {
		int totalPay = orderPay.getTotalPrice() + orderPay.getTotalPostage();
		if (payAlipayLog.getTotalAmount() != totalPay) {
			return false;
		}
		// 校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		if (!AliPayConfig.paySellerId.equals(payAlipayLog.getPaySellerId())) {
			return false;
		}
		// 验证app_id是否为该商户本身
		if (!AliPayConfig.onLineAppId.equals(payAlipayLog.getAppId())) {
			return false;
		}
		return true;
	}

	/**
	 * 获取支付宝回调传入的参数，包含订单的所有信息
	 * 
	 * @author: wyj
	 * @date: 2018年6月20日 下午5:52:59
	 */
	private Map<String, String> getParamMap(Map<String, String[]> requestParams) {
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		return params;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("subject", "星球-测试商品");
		map.put("sign_type", "RSA2");
		map.put("buyer_logon_id", "15384034921");
		map.put("auth_app_id", "2018061460405359");
		map.put("notify_type", "trade_status_sync");
		map.put("out_trade_no", "PO543852046134221");
		map.put("point_amount", "0.00");
		map.put("version", "fund_bill_list");
		map.put("fund_bill_list", "[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]");
		map.put("buyer_id", "2088412283139326");
		map.put("total_amount", "0.01");
		map.put("trade_no", "2018072521001004320562625818");
		map.put("notify_time", "2018-07-25 20:56:37");
		map.put("charset", "utf-8");
		map.put("invoice_amount", "0.01");
		map.put("trade_status", "TRADE_SUCCESS");
		map.put("gmt_payment", "2018-07-25 20:42:55");
		String sign = "j02P7HLfHzVJwMUYKJ0dn6nU2kUTGa4sX+2Ljp9CLG9MbmEeTz3Hxxs9X0Z/jrOyc4t3et5cQwkW5eOrPDNxzJi1cvm5EbWaJaFMSmNAiNrn5G9vKu5EJOlzWfEvGgUQ0HudzoAk8W91rvzEnL+d2DXzLyzNj2hv0F2US9LmlODNOxIK4z3vmeZXMszF1MCV4McPPJgGxWgEo07pak47c8uLLOI8Jr1FrL+EQ2hi5+MGM9F87z0LqeMInclEcJheqBTzTcHdll9iTd9H4GbPOy7V670vxuUqmFJ2pv7DY4hq2BuuU47MUNPj7BjEUosQvkdItSppzKqfTbVULkJs4Q==";
		map.put("sign", sign);
		map.put("gmt_create", "2018-07-25 20:42:54");
		map.put("buyer_pay_amount", "0.01");
		map.put("receipt_amount", "0.01");
		map.put("app_id", "2018061460405359");
		map.put("seller_id", "2088131570742772");
		map.put("notify_id", "2b9873250090eda4ce87e1eca132d8bih1");
		map.put("seller_email", "baoke522@163.com");

		try {
			boolean signVerified = AlipaySignature.rsaCheckV1(map, AliPayConfig.onLinePublicKey, "UTF-8",
					AliPayConfig.signType);

			System.out.println(signVerified);
		} catch (Exception e) {
			logger.error("alipay notify signature check error", e);
		}

	}

}
