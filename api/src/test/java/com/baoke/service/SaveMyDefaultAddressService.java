package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveMyDefaultAddressRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 保存默认地址
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:10:05
 *
 */
public class SaveMyDefaultAddressService extends  BaseServiceTest {

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "saveMyDefaultAddress";
		
		SaveMyDefaultAddressRequest request = new SaveMyDefaultAddressRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setAddressId(3L);

		exec(request);
	}

}
