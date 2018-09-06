package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.BindPhoneRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 微信绑定手机号（并重新下发用户信息）
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:14:31
 *
 */
public class BindPhoneService extends BaseServiceTest {

	public static void main(String[] args) {
		

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "wechatBind";
		
		BindPhoneRequest request = new BindPhoneRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setPhone("18758870781");
		
		exec(request);
	}

}
