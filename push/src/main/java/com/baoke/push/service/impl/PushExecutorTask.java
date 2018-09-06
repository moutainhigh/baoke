package com.baoke.push.service.impl;

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

import com.baoke.common.domain.message.PushMessage;
import com.baoke.push.domain.SendPushDetail;
import com.baoke.push.service.GetuiPushSendService;

/**
 * 消息推送
 * 
 * @author: ljj
 * @date: 2018年6月15日 上午11:04:30
 */
@Service("pushExecutorTask")
public class PushExecutorTask {

	private static final Logger logger = LoggerFactory.getLogger(PushExecutorTask.class);

	private static int MAX_CAPACITY = 1000;

	@Resource
	private GetuiPushSendService getuiPushSendService;

	// 任务列表
	public static BlockingDeque<PushMessage> taskLists = new LinkedBlockingDeque<PushMessage>(MAX_CAPACITY);

	@PostConstruct
	public void init() {

		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				while (true) {
					try {
						PushMessage pushMessage = taskLists.takeFirst();
						if (pushMessage != null) {
							SendPushDetail sendPushDetail = new SendPushDetail();
							sendPushDetail.setUserId(pushMessage.getUserId());
							sendPushDetail.setDeviceId(pushMessage.getDeviceId());
							sendPushDetail.setTitle(pushMessage.getTitle());
							sendPushDetail.setContent(pushMessage.getContent());
							sendPushDetail.setCid(pushMessage.getCid());

							getuiPushSendService.sendMessage(sendPushDetail);

							if (logger.isDebugEnabled()) {
								logger.debug("send push message success, cid = " + sendPushDetail.getCid());
							}
						}
					} catch (Exception e) {
						logger.error("send push message error:", e);
					}
				}
			}
		});

	}

}
