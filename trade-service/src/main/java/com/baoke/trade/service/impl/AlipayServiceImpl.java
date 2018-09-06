package com.baoke.trade.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baoke.common.constant.config.AliPayConfig;
import com.baoke.trade.service.AlipayService;

@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {

	private AlipayClient alipayClient = null;

	/**
	 * 获得初始化的AlipayClient
	 * 
	 * @author zjj
	 * @date 2018年7月2日 下午3:44:04
	 */
	@PostConstruct
	public void init() {
		alipayClient = new DefaultAlipayClient(AliPayConfig.onLineServerUrl, AliPayConfig.onLineAppId,
				AliPayConfig.onLinePrivateKey, AliPayConfig.format, AliPayConfig.charset, AliPayConfig.onLinePublicKey,
				AliPayConfig.signType);
	}

	@Override
	public String payOrder(String tradeNo, String subject, String amount) throws AlipayApiException {
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setNotifyUrl(AliPayConfig.notifyUrl);
		Map<String, String> map = new HashMap<String, String>();
		map.put("total_amount", amount);
		map.put("product_code", "QUICK_MSECURITY_PAY");
		map.put("timeout_express", "90m");
		map.put("subject", subject);
		map.put("out_trade_no", tradeNo);
		String bizContent = JSONObject.toJSON(map).toString();
		request.setBizContent(bizContent);
		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		if (response.isSuccess()) {
			return response.getBody();
		} else {
			return null;
		}
	}

	@Override
	public String closeOrder(String operatorId, String tradeNo) throws AlipayApiException {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("operator_id", operatorId);
		map.put("out_trade_no", tradeNo);
		String bizContent = JSONObject.toJSON(map).toString();
		request.setBizContent(bizContent);
		AlipayTradeCloseResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			return response.getBody();
		} else {
			return null;
		}

	}

	@Override
	public String queryOrder(String operatorId, String tradeNo) throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("out_trade_no", tradeNo);
		String bizContent = JSONObject.toJSON(map).toString();
		request.setBizContent(bizContent);
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			return response.getBody();
		} else {
			return null;
		}
	}

	@Override
	public String refundOrder(String requestNo, String amount, String tradeNo) throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("out_trade_no", tradeNo);
		map.put("refund_amount", amount);
		map.put("out_request_no", requestNo);
		String bizContent = JSONObject.toJSON(map).toString();
		request.setBizContent(bizContent);
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			return response.getBody();
		} else {
			return null;
		}
	}

	@Override
	public String refundQuery(String requestNo, String tradeNo) throws AlipayApiException {
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("out_trade_no", tradeNo);
		map.put("out_request_no", requestNo);
		String bizContent = JSONObject.toJSON(map).toString();
		request.setBizContent(bizContent);
		AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			return response.getBody();
		} else {
			return null;
		}
	}

	@Override
	public String queryBill(String date) throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("bill_type", "trade");
		map.put("bill_date", date);
		String bizContent = JSONObject.toJSON(map).toString();
		request.setBizContent(bizContent);
		AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			return response.getBody();
		} else {
			return null;
		}
	}

}
