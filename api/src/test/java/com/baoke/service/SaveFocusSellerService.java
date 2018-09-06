package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveFocusSellerRequest;
import com.baoke.service.base.BaseServiceTest;

/**
  * 保存关注
   * 
   * @author lcl
   * @date 2018年7月19日 上午11:31:32
 */
public class SaveFocusSellerService  extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "saveFocusSeller";
		
		SaveFocusSellerRequest saveFocusSellerRequest = new SaveFocusSellerRequest();
		saveFocusSellerRequest.setUserCode(getUserCodeByUserId(userId));
		saveFocusSellerRequest.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		saveFocusSellerRequest.setTimeStamp(new Date().getTime());
		saveFocusSellerRequest.setMethod(method);
		saveFocusSellerRequest.setSellerId(9L);
		saveFocusSellerRequest.setIsFocus(1);
		
		exec(saveFocusSellerRequest);
	}

}
