package com.baoke.service;

import com.baoke.api.domain.request.QueryMyFocusSellerRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询我关注的主播
   * 
   * @author lcl
   * @date 2018年7月19日 上午11:26:53
 */
public class QueryMyFocusSellerService extends BaseServiceTest {

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "queryMyFocusSeller";
		
		QueryMyFocusSellerRequest queryMyFocusSellerRequest = new QueryMyFocusSellerRequest();
		queryMyFocusSellerRequest.setUserCode(getUserCodeByUserId(userId));
		queryMyFocusSellerRequest.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		queryMyFocusSellerRequest.setMethod(method);
		
		exec(queryMyFocusSellerRequest);
	}

}
