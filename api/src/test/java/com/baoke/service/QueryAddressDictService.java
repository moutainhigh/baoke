package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryAddressDictRequest;
import com.baoke.service.base.BaseServiceTest;
/**
 * 地址详情
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:04:07
 *
 */
public class QueryAddressDictService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryAddressDict";
		
		QueryAddressDictRequest request = new QueryAddressDictRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setParentCode("86");

		exec(request);
	}

}
