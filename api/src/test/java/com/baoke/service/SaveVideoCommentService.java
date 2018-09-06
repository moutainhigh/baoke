package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveVideoCommentRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 保存评论
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:06:18
 *
 */
public class SaveVideoCommentService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 9L;
		Long deviceId = 1L;
		String method = "saveVideoComment";

		SaveVideoCommentRequest request = new SaveVideoCommentRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setCommentId(1L);
		request.setVideoId(1L);
		request.setContent("你很棒啊啊啊啊啊啊啊啊啊啊啊");
		
		exec(request);
	}

}
