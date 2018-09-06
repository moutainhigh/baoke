package com.baoke.sms.service.impl;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baoke.common.domain.message.SmsMessage;
import com.baoke.sms.service.SmsSentProxyService;

/**
 * 短信发送消费
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午11:04:30
 */
@Service("smsExecutorTask")
public class SmsExecutorTask {

	private static final Logger logger = LoggerFactory.getLogger(SmsExecutorTask.class);

	@Resource
	private SmsSentProxyService smsSentProxyService;

	private static int MAX_CAPACITY = 1000;
	/** 任务列表 */
	public static BlockingDeque<SmsMessage> taskList = new LinkedBlockingDeque<SmsMessage>(MAX_CAPACITY);

	@PostConstruct
	public void init() {

		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				while (true) {
					try {

						SmsMessage smsMessage = taskList.takeFirst();
						if (smsMessage != null) {
							// 发送短信
							smsSentProxyService.sendMessage(smsMessage);

							logger.info(String.format("send sms success, phoneNumber={%s}, data={%s}",
									smsMessage.getPhone(), smsMessage));
						}
					} catch (Exception e) {
						logger.error("send sms error:", e);
					}
				}

			}
		});

	}
}
