package com.baoke.trade.service.impl;

import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.constant.config.WechatPayConfig;
import com.baoke.common.domain.result.Result;
import com.baoke.common.util.XMLUtil;
import com.baoke.common.util.api.json.JsonApiUtil;
import com.baoke.trade.service.WechatService;
import com.baoke.trade.util.HttpXmlUtils;
import com.baoke.trade.util.WechatPayUtil;

@Service("wechatService")
public class WechatServiceImpl implements WechatService {

	private static Logger logger = Logger.getLogger(WechatServiceImpl.class);

	@Override
	public Result wechatPay(String parentOrderNo, String body, int totalFee, String ip) throws Exception {
		// 利用String的自身比较器排序,用于签名
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appid", WechatPayConfig.WX_APPID);
		parameters.put("mch_id", WechatPayConfig.WX_MCHID);
		parameters.put("nonce_str", getRandomString(16));
		parameters.put("body", body);
		parameters.put("out_trade_no", parentOrderNo);
		parameters.put("total_fee", String.valueOf(totalFee));
		parameters.put("notify_url", WechatPayConfig.WX_NOTIFYURL);
		parameters.put("trade_type", WechatPayConfig.WX_TRADETYPE);
		parameters.put("spbill_create_ip", ip);
		// 第一次签名
		String sign = WechatPayUtil.createSign("UTF-8", parameters, WechatPayConfig.PRIVATE_KEY);
		parameters.put("sign", sign);

		// 请求微信服务器，获取prepay_id（预下单）
		String weixinPost = HttpXmlUtils.httpsRequest(WechatPayConfig.WX_UNIFIEDORDER_URL, "POST",
				HttpXmlUtils.xmlInfo(parameters));
		Map<String, String> restmap = XMLUtil.doXMLParse(weixinPost);
		logger.info("wechat pay, get weixin result = " + JsonApiUtil.convertToJson(restmap));

		// 先检查return_code返回SUCCESS或FAIL
		if ("FAIL".equals(restmap.get("return_code"))) {
			logger.error("get wechatPay code error, parentOrderNo = " + parentOrderNo + ", body = " + body
					+ ", totalFee = " + totalFee + ", ip = " + ip + ", return_code = " + restmap.get("return_code")
					+ ", return_msg = " + restmap.get("return_msg"));
			return new Result(false, restmap.get("return_msg"));
		}
		// 再检查result_code返回SUCCESS或FAIL
		if ("FAIL".equals(restmap.get("result_code"))) {
			logger.error("get wechatPay code error, parentOrderNo = " + parentOrderNo + ", body = " + body
					+ ", totalFee = " + totalFee + ", ip = " + ip + ", return_code = " + restmap.get("result_code")
					+ ", err_code = " + restmap.get("err_code") + ", return_msg = " + restmap.get("err_code_des"));
			return new Result(false, restmap.get("err_code_des"));
		}

		SortedMap<String, String> payMap = new TreeMap<String, String>();
		payMap.put("appid", restmap.get("appid"));
		payMap.put("partnerid", restmap.get("mch_id"));
		payMap.put("prepayid", restmap.get("prepay_id")); // 默认有限期为2个小时
		payMap.put("package", "Sign=WXPay");
		payMap.put("noncestr", restmap.get("nonce_str"));
		payMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000)); // 精确到秒
		// 请求到prepay_id后，进行新参数二次签名
		payMap.put("sign", WechatPayUtil.createSign("UTF-8", payMap, WechatPayConfig.PRIVATE_KEY));

		// 签名完成后直接把信息给前端，前端用payjson去调微信
		String payMapStr = JsonApiUtil.convertToJson(payMap);
		logger.info("get wechatPay code success, parentOrderNo = " + parentOrderNo + ", body = " + body
				+ ", totalFee = " + totalFee + ", ip = " + ip + ", payMapStr = " + payMapStr);
		return new Result(true, "", payMapStr);
	}

	/**
	 * transactionId与parentOrderNo二选一，优先选transactionId
	 */
	@Override
	public Result queryOrder(String transactionId, String parentOrderNo) throws Exception {
		// 默认排序
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appid", WechatPayConfig.WX_APPID);
		parameters.put("mch_id", WechatPayConfig.WX_MCHID);
		parameters.put("nonce_str", getRandomString(16));
		// parameters.put("out_trade_no", parentOrderNo);
		parameters.put("transaction_id", transactionId);

		String sign = WechatPayUtil.createSign("UTF-8", parameters, WechatPayConfig.PRIVATE_KEY);
		parameters.put("sign", sign);

		// 请求微信服务器
		String weixinPost = HttpXmlUtils.httpsRequest(WechatPayConfig.WX_ORDERQUERY_URL, "POST",
				HttpXmlUtils.xmlInfo(parameters));
		Map<String, String> restmap = XMLUtil.doXMLParse(weixinPost);
		logger.info("query wechat order, get weixin result = " + JsonApiUtil.convertToJson(restmap));

		// 先检查return_code返回SUCCESS或FAIL
		if ("FAIL".equals(restmap.get("return_code"))) {
			logger.error("query wechat order error, transactionId = " + transactionId + ", parentOrderNo = "
					+ parentOrderNo + ", return_code = " + restmap.get("return_code") + ", return_msg = "
					+ restmap.get("return_msg"));
			return new Result(false, restmap.get("return_msg"));
		}
		// 再检查result_code返回SUCCESS或FAIL
		if ("FAIL".equals(restmap.get("result_code"))) {
			logger.error("get wechatPay code error,transactionId = " + transactionId + ",  parentOrderNo = "
					+ parentOrderNo + ", return_code = " + restmap.get("result_code") + ", err_code = "
					+ restmap.get("err_code") + ", return_msg = " + restmap.get("err_code_des"));
			return new Result(false, restmap.get("err_code_des"));
		}

		return new Result(true, restmap.get("trade_state"), restmap.get("trade_state_desc"));
	}

	// 获取指定长度随机字符串
	private String getRandomString(int length) { // length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		int number = 0;
		for (int i = 0; i < length; i++) {
			number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
