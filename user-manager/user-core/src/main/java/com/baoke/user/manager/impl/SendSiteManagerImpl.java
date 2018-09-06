package com.baoke.user.manager.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.util.HttpClientHelper;
import com.baoke.user.manager.SendSiteManager;

@Component
public class SendSiteManagerImpl implements SendSiteManager {

	private static final Logger logger = Logger.getLogger(SendSiteManagerImpl.class);

	@Value("${user.service.site.send.url}")
	private String sendSiteUrl = "http://127.0.0.1/send/site";// 站内信发送地址

	private final ExecutorService synchronousService = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());

	@Override
	public boolean sendSite(final SiteMessage siteMessage) {

		if (null == siteMessage) {
			return false;
		}

		synchronousService.submit(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					try {
						String content = HttpClientHelper.postDataByRequestBody(sendSiteUrl,
								JSON.toJSONString(siteMessage), true, "utf-8");
						if (logger.isDebugEnabled()) {
							logger.debug("send site message success, content=" + content + ", sendSiteUrl="
									+ sendSiteUrl + ", params=" + siteMessage);
						}
						break;
					} catch (Exception e) {
						logger.error("send site message error, " + siteMessage + ", i=" + (i + 1), e);
					}
				}
			}
		});

		return true;
	}

}
