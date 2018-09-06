package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryOrderPostageRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询物流
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:00:44
 *
 */
public class QueryOrderPostageService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 2L;
		Long deviceId = 1L;
		String method = "queryOrderPostage";
		
		QueryOrderPostageRequest request = new QueryOrderPostageRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setOrderNo("SO46118958004621");

		exec(request);
	}

}
