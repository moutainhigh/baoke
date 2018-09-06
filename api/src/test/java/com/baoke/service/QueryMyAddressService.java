package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryMyAddressRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询我的地址
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:06:11
 *
 */
public class QueryMyAddressService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryMyAddress";
		
		QueryMyAddressRequest request = new QueryMyAddressRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());

		exec(request);
	}

}
