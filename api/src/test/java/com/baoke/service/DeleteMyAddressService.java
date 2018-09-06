package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.DeleteMyAddressRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 删除我的地址
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:11:41
 *
 */
public class DeleteMyAddressService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "deleteMyAddress";
		
		DeleteMyAddressRequest request = new DeleteMyAddressRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setAddressId(3L);

		exec(request);
	}

}
