package com.baoke.extra.pay.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoke.common.constant.config.WechatPayConfig;
import com.baoke.common.util.StringUtil;
import com.baoke.common.util.XMLUtil;
import com.baoke.common.util.api.json.JsonApiUtil;
import com.baoke.trade.constant.OrderPayStatusEnum;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.PayWechatLog;
import com.baoke.trade.service.OrderManagerService;
import com.baoke.trade.service.OrderService;
import com.baoke.trade.service.OrderShoppingCartService;
import com.baoke.trade.service.PayLogService;
import com.baoke.trade.service.SellerIncomeRecordService;
import com.baoke.trade.util.WechatPayUtil;

/**
 * 微信支付回调
 * 
 * @author: zjj
 * @date: 2018年6月25日 下午4:19:39
 */
@Controller
public class WechatNotifyController {

	private static Logger logger = Logger.getLogger(WechatNotifyController.class);

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
	@RequestMapping(value = "wechat/notify", method = RequestMethod.POST)
	public String wechatNotify(HttpServletRequest request) {
		// 读取xml字符串
		InputStream inputStream;
		String notifyXml = "";
		StringBuffer sb = new StringBuffer("");
		try {
			inputStream = request.getInputStream();
			byte[] b = new byte[4096];
			for (int n; (n = inputStream.read(b)) != -1;) {
				sb.append(new String(b, 0, n, "UTF-8"));
			}
			notifyXml = sb.toString();
			logger.info("wechat notify start, params=" + notifyXml);
		} catch (Exception e) {
			logger.error("wechat notify get params error", e);
			return buildResXml("FAIL", "读取数据流失败");
		}

		// 解析xml字符串为map
		Map<String, String> paramMap;
		try {
			paramMap = XMLUtil.doXMLParse(notifyXml);
			logger.info("wechat notify start, paramMap=" + JsonApiUtil.convertToJson(paramMap));
		} catch (Exception e) {
			logger.error("wechat notify get paramMap error", e);
			return buildResXml("FAIL", "解析xml数据失败");
		}

		if (!"SUCCESS".equals(paramMap.get("return_code"))) {
			return buildResXml("FAIL", "未获得数据");
		}

		OrderPay orderPay = orderService.queryOrderPayByParentOrderNo(paramMap.get("out_trade_no"));
		logger.info("wechat notify, " + orderPay);
		if (null == orderPay) {
			logger.error("wechat notify error, orderPay is empty. parentOrderNo = " + paramMap.get("out_trade_no")
					+ ", paramMap=" + paramMap);
			return buildResXml("FAIL", "out_trade_no错误");
		}

		PayWechatLog payWechatLog = null;

		synchronized (objectLock) {
			// 避免重复处理
			if (OrderPayStatusEnum.SUCCESS.getCode() == orderPay.getStatus()) {
				return buildResXml("SUCCESS", "OK");
			}

			payWechatLog = payLogService.addPayWechatLog(paramMap, (null == orderPay) ? null : orderPay.getUserId());

			// 验签
			if (!checkSign(paramMap)) {
				logger.error("wechat notify sign check error, " + payWechatLog);
				return buildResXml("FAIL", "签名错误");
			}

			if (!checkWechatpayNotify(payWechatLog, orderPay)) {
				logger.error("wechat notify check error, " + payWechatLog);
				return buildResXml("FAIL", "订单数据不正确");
			}

			if (!orderManagerService.modifyOrderPayStatus(orderPay, payWechatLog)) {
				logger.error("wechat notify error, modify order pay error, " + payWechatLog);
				return buildResXml("SUCCESS", "OK");
			}

			// 如果支付成功，修改商品库存，修改order订单状态，增加卖家收入明细
			if (OrderPayStatusEnum.SUCCESS.getCode() == payWechatLog.getStatus()) {
				List<Order> orderList = orderService.queryOrderByParentOrderNo(payWechatLog.getParentOrderNo());
				if (null == orderList) {
					logger.error("wechat notify error, order list is empty" + payWechatLog);
				}
				for (Order order : orderList) {
					try {
						if (orderManagerService.modifyItemNumAndOrderStatus(order)) {
							sellerIncomeRecordService.addSellerIncomeRecord(order, payWechatLog.getId(),
									OrderPayTypeEnum.WECHATPAY);
						}
					} catch (Exception e) {
						logger.error("wechat notify error, modify order item error, " + payWechatLog, e);
					}
				}
			}
		}

		// 如果是购物车付款的，则删除购物车对应记录
		orderShoppingCartService.deleteShoppingCart(orderPay);

		logger.info("wechat notify end, " + payWechatLog);
		return buildResXml("SUCCESS", "OK");
	}

	private boolean checkWechatpayNotify(PayWechatLog payWechatLog, OrderPay orderPay) {
		int totalPay = orderPay.getTotalPrice() + orderPay.getTotalPostage();
		if (payWechatLog.getTotalFee() != totalPay) {
			return false;
		}
		// 检查appid
		if (!WechatPayConfig.WX_APPID.equals(payWechatLog.getAppid())) {
			return false;
		}
		return true;
	}

	private static boolean checkSign(Map<String, String> paramMap) {
		String signFromResponse = paramMap.get("sign");
		if (StringUtil.isBlank(signFromResponse)) {
			return false;
		}

		SortedMap<String, String> sortedMap = new TreeMap<>();
		Iterator<String> it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = paramMap.get(key);
			if (!StringUtil.isBlank(value) && !"sign".equals(key)) {
				sortedMap.put(key, value.trim());
			}
		}

		String signForResponse = WechatPayUtil.createSign("UTF-8", sortedMap, WechatPayConfig.PRIVATE_KEY);
		return signFromResponse.equals(signForResponse);
	}

	private String buildResXml(String returnCode, String returnMsg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<return_code><![CDATA[").append(returnCode).append("]]></return_code>");
		sb.append("<return_msg><![CDATA[").append(returnMsg).append("]]></return_msg>");
		sb.append("</xml>");
		return sb.toString();
	}

	public static void main(String[] args) {
		String xml = "<xml><appid><![CDATA[wx083af9f00fa6f4c0]]></appid>\r\n"
				+ "<bank_type><![CDATA[GDB_CREDIT]]></bank_type>\r\n" + "<cash_fee><![CDATA[1]]></cash_fee>\r\n"
				+ "<fee_type><![CDATA[CNY]]></fee_type>\r\n" + "<is_subscribe><![CDATA[N]]></is_subscribe>\r\n"
				+ "<mch_id><![CDATA[1507178591]]></mch_id>\r\n"
				+ "<nonce_str><![CDATA[qe9ByEbC9JSg1XkU]]></nonce_str>\r\n"
				+ "<openid><![CDATA[oGCRTxEYIWh_05Qb_x61dKITJAcs]]></openid>\r\n"
				+ "<out_trade_no><![CDATA[PO1231696494816148]]></out_trade_no>\r\n"
				+ "<result_code><![CDATA[SUCCESS]]></result_code>\r\n"
				+ "<return_code><![CDATA[SUCCESS]]></return_code>\r\n"
				+ "<sign><![CDATA[FAF8CA9C431F50C6D9F33C7009508AE6]]></sign>\r\n"
				+ "<time_end><![CDATA[20180723164448]]></time_end>\r\n" + "<total_fee>1</total_fee>\r\n"
				+ "<trade_type><![CDATA[APP]]></trade_type>\r\n"
				+ "<transaction_id><![CDATA[4200000129201807231404118430]]></transaction_id>\r\n" + "</xml>";
		try {
			Map<String, String> paramMap = XMLUtil.doXMLParse(xml);
			boolean checkSign = checkSign(paramMap);
			System.out.println(checkSign);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
}
