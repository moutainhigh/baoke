package com.baoke.user.manager;

import com.baoke.common.domain.message.SmsMessage;

/**
 * 短信发送
 * 
 * @author wyh
 * @date 2018年7月6日 下午7:02:55
 *
 */
public interface SendSmsManager {

	/**
	 * sms消息发送
	 * 
	 * @author wyh
	 * @date 2018年7月6日 下午7:02:31
	 * 
	 * @param smsMessage
	 * @return
	 */
	public boolean sendSms(SmsMessage smsMessage);

}
