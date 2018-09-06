package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryMyOrderDetailRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 
 * 订单详情
 * @author lcl
 * @date 2018年7月19日 下午1:50:05
 *
 */
public class QueryMyOrderDetailService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryMyOrderDetail";
		
		QueryMyOrderDetailRequest request = new QueryMyOrderDetailRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setOrderNo("SO5582960603007");
		
		exec(request);

	}

}
