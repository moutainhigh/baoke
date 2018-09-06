package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveVideoDelikeRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 视频点赞
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:24:35
 *
 */
public class SaveVideoDelikeService extends BaseServiceTest {

	public static void main(String[] args) {

		Long userId = 9L;
		Long deviceId = 1L;
		String method = "saveVideoGreat";
		
		SaveVideoDelikeRequest request = new SaveVideoDelikeRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setVideoId(1L);
		request.setIsDelike(10);

		exec(request);
	}

}
