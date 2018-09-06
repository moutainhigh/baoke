package com.baoke.user.manager.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.baoke.common.domain.message.SmsMessage;
import com.baoke.common.util.HttpClientHelper;
import com.baoke.user.manager.SendSmsManager;

@Component
public class SendSmsManagerImpl implements SendSmsManager {

	private static final Logger logger = Logger.getLogger(SendSmsManagerImpl.class);

	@Value("${user.service.sms.send.url}")
	private String sendSmsUrl = "http://127.0.0.1/send/sms";// 短信发送地址

	@Override
	public boolean sendSms(SmsMessage smsMessage) {

		if (null == smsMessage) {
			return false;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("phone", smsMessage.getPhone());
		try {
			params.put("message", URLEncoder.encode(smsMessage.getMessage(), "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			params.put("message", smsMessage.getMessage());
		}
		params.put("sendTime", String.valueOf(smsMessage.getSendTime()));
		params.put("validMillisecond", String.valueOf(smsMessage.getValidMillisecond()));

		for (int i = 0; i < 3; i++) {
			try {
				String content = HttpClientHelper.postDataByUrl(sendSmsUrl, true, "utf-8", params);
				if (logger.isDebugEnabled()) {
					logger.debug("send sms message success, content=" + content + ", sendSmsUrl=" + sendSmsUrl
							+ ", params=" + params);
				}
				return true;
			} catch (Exception e) {
				logger.error("send sms message error, " + smsMessage + ", i=" + (i + 1), e);
			}
		}
		return false;
	}

}
