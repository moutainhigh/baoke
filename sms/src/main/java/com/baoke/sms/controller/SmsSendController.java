package com.baoke.sms.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoke.common.domain.message.SmsMessage;
import com.baoke.common.domain.result.Result;
import com.baoke.sms.service.impl.SmsExecutorTask;

/**
 * 短信发送
 * 
 * @author wyh
 * @date 2018年7月18日 下午4:37:52
 *
 */
@Controller
public class SmsSendController {

	private static final Logger logger = LoggerFactory.getLogger(SmsSendController.class);

	@RequestMapping(value = "/send/sms", method = RequestMethod.POST)
	@ResponseBody
	public Result sendSms(SmsMessage smsMessage) {

		if (logger.isDebugEnabled()) {
			logger.debug("send sms message start. " + smsMessage);
		}

		if (smsMessage != null) {
			try {

				try {
					smsMessage.setMessage(URLDecoder.decode(smsMessage.getMessage(), "utf-8"));
				} catch (UnsupportedEncodingException e) {
				}
				SmsExecutorTask.taskList.putLast(smsMessage);

				return new Result(true, "success");
			} catch (InterruptedException e) {
			}
		}

		return new Result(false, "error");
	}
}
