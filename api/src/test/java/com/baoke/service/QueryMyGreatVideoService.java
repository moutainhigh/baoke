package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryMyGreatVideoRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 *  查询我喜欢的视频
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:39:52
 *
 */
public class QueryMyGreatVideoService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryMyGreatVideo";

		QueryMyGreatVideoRequest request = new QueryMyGreatVideoRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		
		exec(request);
	}

}
