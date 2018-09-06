package com.baoke.service.base;

import java.net.URLEncoder;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.util.BkBase64;
import com.baoke.api.util.Md5Encrypt;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.HttpClientHelper;
import com.baoke.common.util.StringUtil;
import com.baoke.common.util.api.json.JsonApiUtil;

public class BaseServiceTest {

	private static final String baseUrl = "http://127.0.0.1/service/app.htm";
	private static final String REQUEST_DATA_KEY = "ksldkwo234232lkk2l32";

	public static String exec(BaseRequestParam baseRequestParam) {

		String requestData = JsonApiUtil.convertToJson(baseRequestParam);
		System.out.println("request:" + baseRequestParam);
		String resultStr = "";

		try {
			String sign = Md5Encrypt.md5(requestData + REQUEST_DATA_KEY, "utf-8");
			String url = baseUrl + "?requestData=" + URLEncoder.encode(requestData, "utf-8") + "&sign=" + sign;
			System.out.println("url:" + url);
			resultStr = HttpClientHelper.getStringByUrl(url, true, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (StringUtil.isBlank(resultStr)) {
			resultStr = "ERROR";
		}

		System.out.println("response:" + resultStr);

		return resultStr;

	}

	public static String getUserCodeByUserId(Long userId) {
		if (null == userId) {
			return "";
		}
		try {
			return BkBase64.long2Base64(userId);
		} catch (ParamInvalidException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getDeviceCodeByDeviceId(Long deviceId) {
		if (null == deviceId) {
			return "";
		}
		try {
			return BkBase64.long2Base64(deviceId);
		} catch (ParamInvalidException e) {
			e.printStackTrace();
		}
		return "";
	}
}
