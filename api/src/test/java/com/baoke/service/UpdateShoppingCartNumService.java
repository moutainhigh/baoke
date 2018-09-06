package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.UpdateShoppingCartNumRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 修改购物车数量
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:58:13
 *
 */
public class UpdateShoppingCartNumService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "updateShoppingCartNum";
		
		UpdateShoppingCartNumRequest request = new UpdateShoppingCartNumRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setContent("20:2");

		exec(request);
	}

}
