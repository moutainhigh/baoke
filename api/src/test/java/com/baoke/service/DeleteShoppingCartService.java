package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.DeleteShoppingCartRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 删除购物车
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:56:25
 *
 */
public class DeleteShoppingCartService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 9L;
		Long deviceId = 1L;
		String method = "deleteShoppingCart";
		
		DeleteShoppingCartRequest request = new DeleteShoppingCartRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setShoppingCartIds("3,4");

		exec(request);
	}

}
