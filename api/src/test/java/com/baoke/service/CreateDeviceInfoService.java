package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.CreateDeviceInfoRequest;
import com.baoke.service.base.BaseServiceTest;

public class CreateDeviceInfoService extends BaseServiceTest{
/**
 * 创建设备信息
  * 
  * @author lcl
  * @date 2018年7月19日 下午5:29:50
  * 
  * @param args
 */
public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "createDeviceInfo";
		
		CreateDeviceInfoRequest request = new CreateDeviceInfoRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setUuid("14");
		request.setAppVersion("1.1");
		request.setOs("ios");
		request.setModel("iponex");
		request.setIfa("IOS");
		request.setTimeStamp(new Date().getTime());

		exec(request);
	}

}
