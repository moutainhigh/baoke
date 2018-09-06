package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryVideoDetailRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询视频详情
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:34:33
 *
 */
public class QueryVideoDetailService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryVideoDetail";
		
		QueryVideoDetailRequest request = new QueryVideoDetailRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setVideoId(1L);
		request.setCurPageNo(1);

		exec(request);
		
	}

}
