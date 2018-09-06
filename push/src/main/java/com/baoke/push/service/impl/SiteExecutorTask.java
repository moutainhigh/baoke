package com.baoke.push.service.impl;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.domain.message.SiteMessage;
import com.baoke.push.service.SiteMessageSendService;

/**
 * 站内消息
 * 
 * @author zjj
 * @date 2018年7月16日 下午8:13:11
 */
@Service("siteExecutorTask")
public class SiteExecutorTask {

	private static final Logger logger = Logger.getLogger(SiteExecutorTask.class);

	private static int MAX_CAPACITY = 1000;

	@Resource
	private SiteMessageSendService siteMessageSendService;

	// 任务列表
	public static BlockingDeque<SiteMessage> taskLists = new LinkedBlockingDeque<SiteMessage>(MAX_CAPACITY);

	@PostConstruct
	public void init() {

		ExecutorService service = Executors.newCachedThreadPool();
		service.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				while (true) {
					try {
						SiteMessage siteMessage = taskLists.takeFirst();
						if (siteMessage != null) {

							siteMessageSendService.addSiteMessage(siteMessage);

							if (logger.isDebugEnabled()) {
								logger.debug("send site message success, siteMessage:" + siteMessage);
							}
						}
					} catch (Exception e) {
						logger.error("send site message error:", e);
					}
				}
			}
		});

	}

}
