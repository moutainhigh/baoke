package com.baoke.sms.service;

import com.baoke.common.domain.message.SmsMessage;

/**
 * 短信发送服务
 * 
 * @author wyh
 * @date 2018年7月19日 下午3:09:58
 *
 */
public interface SmsSentProxyService {

	void sendMessage(SmsMessage smsMessage);

}
