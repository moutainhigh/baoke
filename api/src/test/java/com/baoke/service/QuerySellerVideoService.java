package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QuerySellerVideoRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询播主（卖家）首页（视频页）
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:37:01
 *
 */
public class QuerySellerVideoService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "querySellerVideo";
		
		QuerySellerVideoRequest request = new QuerySellerVideoRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setSellerId(9L);

		exec(request);
	}

}
