package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QuerySellerItemRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询播主（卖家）商品页
 * 
 * @author wyh
 * @date 2018年7月19日 上午10:33:53
 *
 */
public class QuerySellerItemServiceTest extends BaseServiceTest {

	public static void main(String[] args) {

		Long userId = 1L;
		Long deviceId = 1L;
		String method = "querySellerItem";

		QuerySellerItemRequest request = new QuerySellerItemRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());
		request.setSellerId(2L);

		exec(request);

	}

}
