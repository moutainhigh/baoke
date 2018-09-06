package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryFollowVideoRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询首页关注列表
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:32:13
 *
 */
public class QueryFollowVideoService extends BaseServiceTest {

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryFollowVideo";
		
		QueryFollowVideoRequest request = new QueryFollowVideoRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setCurPageNo(1);

		exec(request);
		
	}

}
