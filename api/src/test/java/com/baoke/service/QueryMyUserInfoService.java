package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryMyUserInfoRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 获取用户信息
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:13:03
 *
 */
public class QueryMyUserInfoService extends BaseServiceTest {

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryMyUserInfo";
		
		QueryMyUserInfoRequest request = new QueryMyUserInfoRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());

		exec(request);
	}

}
