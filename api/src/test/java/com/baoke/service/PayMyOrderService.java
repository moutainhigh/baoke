package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.PayMyOrderRequest;
import com.baoke.service.base.BaseServiceTest;
import com.baoke.trade.constant.OrderPayTypeEnum;

/**
 * 支付订单
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:48:00
 *
 */
public class PayMyOrderService extends BaseServiceTest {

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "payMyOrder";

		PayMyOrderRequest request = new PayMyOrderRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setParentOrderNo("PO46713211717735");
		request.setPayType(OrderPayTypeEnum.WECHATPAY.getCode());
		request.setIp("192.168.1.1");

		exec(request);

	}

}
