package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveMyOrderItemNumRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 修改订单商品数量
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:46:41
 *
 */
public class SaveMyOrderItemNumService extends BaseServiceTest{

	public static void main(String[] args) {


		Long userId = 1L;
		Long deviceId = 1L;
		String method = "saveMyOrderItemNum";
		
		SaveMyOrderItemNumRequest request = new SaveMyOrderItemNumRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setParentOrderNo("PO22674715961802");
		request.setItemId(1L);
		request.setItemDetailId(1L);
		request.setNum(20);

		exec(request);
	}

}
