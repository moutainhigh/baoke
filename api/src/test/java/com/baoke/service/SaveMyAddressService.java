package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveMyAddressRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 保存我的地址
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:07:46
 *
 */
public class SaveMyAddressService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "saveMyAddress";
		
		SaveMyAddressRequest saveMyAddressRequest = new SaveMyAddressRequest();
		saveMyAddressRequest.setUserCode(getUserCodeByUserId(userId));
		saveMyAddressRequest.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		saveMyAddressRequest.setMethod(method);
		saveMyAddressRequest.setTimeStamp(new Date().getTime());
		saveMyAddressRequest.setName("收货地址333");
		saveMyAddressRequest.setProvince("湖北省");
		saveMyAddressRequest.setCity("襄阳市");
		saveMyAddressRequest.setArea("保康县");
		saveMyAddressRequest.setAddress("1号街");
		saveMyAddressRequest.setPhone("15266663333");

		exec(saveMyAddressRequest);

		
	}

}
