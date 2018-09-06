package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryItemDetailRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询商品详情
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:27:00
 *
 */
public class QueryItemDetailService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryItemDetail";
		
		QueryItemDetailRequest request = new QueryItemDetailRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setItemId(100L);

		exec(request);
	}

}
