package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveUserSexRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 设置－保存用户性别
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:17:45
 *
 */
public class SaveUserSexService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "saveUserSex";
		
		SaveUserSexRequest request = new SaveUserSexRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setSex(1);

		exec(request);
	}

}
