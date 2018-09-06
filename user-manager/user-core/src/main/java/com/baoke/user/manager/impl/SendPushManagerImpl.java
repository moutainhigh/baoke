package com.baoke.user.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.baoke.common.domain.message.PushMessage;
import com.baoke.common.util.HttpClientHelper;
import com.baoke.user.manager.SendPushManager;

@Component
public class SendPushManagerImpl implements SendPushManager {

	private static final Logger logger = Logger.getLogger(SendPushManagerImpl.class);

	@Value("${user.service.push.send.url}")
	private String sendPushUrl = "http://127.0.0.1/send/push";

	@Override
	public boolean sendPush(PushMessage pushMessage) {

		if (null == pushMessage) {
			return false;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", pushMessage.getUserId().toString());
		params.put("deviceId", pushMessage.getDeviceId().toString());
		params.put("cid", pushMessage.getCid());
		params.put("title", pushMessage.getTitle());
		params.put("content", pushMessage.getContent());

		for (int i = 0; i < 3; i++) {
			try {
				String content = HttpClientHelper.postDataByUrl(sendPushUrl, true, "utf-8", params);
				if (logger.isDebugEnabled()) {
					logger.debug("send push message success, content=" + content + ", sendPushUrl=" + sendPushUrl
							+ ", params=" + params);
				}
				return true;
			} catch (Exception e) {
				logger.error("send push message error, " + pushMessage + ", i=" + (i + 1), e);
			}
		}
		return false;
	}

}
