package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryNewVersionRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 新版本检测
   * 
   * @author lcl
   * @date 2018年7月19日 上午11:04:57
 */
public class QueryNewVersionService  extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryNewVersion";
		
		QueryNewVersionRequest queryNewVersionRequest = new QueryNewVersionRequest();
		queryNewVersionRequest.setUserCode(getUserCodeByUserId(userId));
		queryNewVersionRequest.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		queryNewVersionRequest.setMethod(method);
		queryNewVersionRequest.setTimeStamp(new Date().getTime());
		
		exec(queryNewVersionRequest);
		
	}

}
