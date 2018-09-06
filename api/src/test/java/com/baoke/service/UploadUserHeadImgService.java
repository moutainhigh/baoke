package com.baoke.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.springframework.mock.web.MockMultipartFile;

import com.baoke.api.domain.request.UploadUserHeadImgRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 修改用户头像
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:20:26
 *
 */
public class UploadUserHeadImgService extends BaseServiceTest{

	public static void main(String[] args) throws Exception {
		
		Long userId = 1L;
		Long deviceId = 1L;
		String method = "uploadUserHeadImg";
		
		UploadUserHeadImgRequest request = new UploadUserHeadImgRequest();
		File file = new File("E:/123.png");
		FileInputStream inputStream = new FileInputStream(file);
		 new MockMultipartFile(file.getName(), inputStream);

		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setMethod(method);
		request.setTimeStamp(new Date().getTime());

		exec(request);
		
	}

}
