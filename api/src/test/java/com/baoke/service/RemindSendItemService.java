package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.RemindSendItemRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 提醒发货
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:51:29
 *
 */
public class RemindSendItemService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "remindSendItem";
		
		RemindSendItemRequest request = new RemindSendItemRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setOrderNo("SO46118958004621");

		exec(request);
		
	}

}
