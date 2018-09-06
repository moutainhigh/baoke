package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveUserNickNameRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 设置－保存用户昵称
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:19:06
 *
 */
public class SaveUserNickNameService extends BaseServiceTest{

	public static void main(String[] args) {


		Long userId = 9L;
		Long deviceId = 1L;
		String method = "saveUserNickName";
		
		SaveUserNickNameRequest request = new SaveUserNickNameRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setNickName("热不热");
		
		exec(request);
	}

}
