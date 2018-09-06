package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryMyMessageRequest;
import com.baoke.service.base.BaseServiceTest;
/**
 * 我的消息
   * 
   * @author lcl
   * @date 2018年7月19日 上午11:15:35
 */
public class QueryMyMessageService extends BaseServiceTest {

	public static void main(String[] args) {
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryMyMessage";
		
		QueryMyMessageRequest request = new QueryMyMessageRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		
		exec(request);
	}

}
