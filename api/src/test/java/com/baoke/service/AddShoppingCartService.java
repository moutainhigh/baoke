package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.AddShoppingCartRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 添加购物车
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:54:52
 *
 */
public class AddShoppingCartService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 2L;
		Long deviceId = 1L;
		String method = "addShoppingCart";
		
		AddShoppingCartRequest request = new AddShoppingCartRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setItemId(1L);
		request.setItemDetailId(1L);
		request.setNum(100);

		exec(request);
		
	}

}
