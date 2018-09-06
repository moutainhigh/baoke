package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QuerySellerInfoByIdRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 根据主播Id查询主播信息
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:16:16
 *
 */
public class QuerySellerInfoByIdService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 2L;
		Long deviceId = 1L;
		String method = "querySellerInfoById";
		
		QuerySellerInfoByIdRequest request = new QuerySellerInfoByIdRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setSellerId(9L);

		exec(request);
	}

}
