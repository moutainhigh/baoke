package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryVideoComplaintRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 获取举报字典
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:16:30
 *
 */
public class QueryVideoComplaintService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 9L;
		Long deviceId = 1L;
		String method = "queryVideoComplaint";
		
		QueryVideoComplaintRequest request = new QueryVideoComplaintRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setVideoId(1L);

		exec(request);
	}

}
