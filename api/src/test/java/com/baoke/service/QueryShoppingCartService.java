package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryShoppingCartRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 购物车列表
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:53:26
 *
 */
public class QueryShoppingCartService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryShoppingCart";
		
		QueryShoppingCartRequest request = new QueryShoppingCartRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());

		exec(request);
	}

}
