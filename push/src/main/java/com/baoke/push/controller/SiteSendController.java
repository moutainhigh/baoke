package com.baoke.push.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.domain.result.Result;
import com.baoke.push.service.impl.SiteExecutorTask;

/**
 * siteMessage发送服务
 * 
 */
@Controller
public class SiteSendController {

	private static final Logger logger = Logger.getLogger(SiteSendController.class);

	@ResponseBody
	@RequestMapping(value = "/send/site", method = RequestMethod.POST)
	public Result siteMsg(@RequestBody SiteMessage siteMessage) {

		if (logger.isDebugEnabled()) {
			logger.debug("send site message start. " + siteMessage);
		}

		if (siteMessage != null) {
			try {

				SiteExecutorTask.taskLists.putLast(siteMessage);

				return new Result(true, "success");
			} catch (Exception e) {
				logger.error("send siteMessage error", e);
				return new Result(false, "error");
			}
		}

		return new Result(false, "error");
	}

}
