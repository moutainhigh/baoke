package com.baoke.sms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.domain.message.SmsMessage;
import com.baoke.sms.service.SmsSentProxyService;
import com.baoke.sms.service.SmsSentService;

@Service("smsSentProxyService")
public class SmsSentProxyServiceImpl implements SmsSentProxyService {

	@Resource
	private SmsSentService yuntongxunSmsSentService;

	@Override
	public void sendMessage(SmsMessage smsMessage) {
		if (smsMessage == null) {
			return;
		}

		yuntongxunSmsSentService.sendMessage(smsMessage);

	}

}
