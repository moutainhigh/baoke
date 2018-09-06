package com.baoke.sms.service;

import com.baoke.common.domain.message.SmsMessage;

/**
 * 短信发送
 * 
 * @author wyh
 * @date 2018年7月19日 下午3:11:09
 *
 */
public interface SmsSentService {

	void sendMessage(SmsMessage smsMessage);

}
