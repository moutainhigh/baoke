package com.baoke.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.baoke.common.constant.UploadFileEnum;
import com.baoke.common.exception.ParamInvalidException;

public class UploadFileUtilTest {

	public static void main(String[] args) throws IOException, ParamInvalidException {
		System.out.println(UploadFileUtil.getImageView(
				"http://user-1254128350.picsh.myqcloud.com/idcard/18071298e8684f054242e8b39d4f68f1d542b4.jpg", 100,
				100));

		File file = new File("D://a.png");
		@SuppressWarnings("resource")
		InputStream input = new FileInputStream(file);
		byte[] data = new byte[input.available()];
		input.read(data);

		String url[] = UploadFileUtil.uploadTencentCloud(data, "jpg", 0l, UploadFileEnum.USER_NICK);

		System.out.println(url[0]);
	}

}
