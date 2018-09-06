package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveMyOrderAddressRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 修改订单地址和支付方式
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:45:19
 *
 */
public class SaveMyOrderAddressService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "saveMyOrderAddress"; 
		
		SaveMyOrderAddressRequest request = new SaveMyOrderAddressRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setParentOrderNo("PO5575249062701");
		request.setPayType(1);
		request.setAddressId(1L);

		exec(request);
	}

}
