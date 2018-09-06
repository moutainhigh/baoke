package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveMyOrderRequest;
import com.baoke.service.base.BaseServiceTest;
import com.baoke.trade.constant.IsShoppingCartEnum;

/**
 * 保存订单
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:43:44
 *
 */
public class SaveMyOrderService extends BaseServiceTest{

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "saveMyOrder";

		SaveMyOrderRequest request = new SaveMyOrderRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setItemId(1L);
		request.setNum(2);
		request.setIsShoppingCart(IsShoppingCartEnum.YES.getCode());
		request.setShoppingCartIds("5,7");

		exec(request);

	}

}
