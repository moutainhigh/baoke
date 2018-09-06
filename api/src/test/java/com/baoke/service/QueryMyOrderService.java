package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryMyOrderRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询我的订单列表
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:41:50
 *
 */
public class QueryMyOrderService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryMyOrder";
		
		QueryMyOrderRequest request = new QueryMyOrderRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setOrderStatus(0);
		request.setTimeStamp(new Date().getTime());

		exec(request);
	}

}
