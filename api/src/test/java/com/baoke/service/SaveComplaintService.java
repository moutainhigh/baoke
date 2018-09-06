package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.SaveComplaintRequest;
import com.baoke.interact.constant.VideoComplaintDictTypeEnum;
import com.baoke.service.base.BaseServiceTest;

/**
 * 保存视频举报（吐槽）
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:21:59
 *
 */
public class SaveComplaintService  extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long userId = 9L;
		Long deviceId = 1L;
		String method = "saveComplaint";

		SaveComplaintRequest request = new SaveComplaintRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setVideoId(2L);
		request.setType(VideoComplaintDictTypeEnum.INPUT.getCode());
		request.setContent("不好不好...........");
		
		exec(request);
	}

}
