package com.baoke.push.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoke.common.domain.message.PushMessage;
import com.baoke.common.domain.result.Result;
import com.baoke.push.service.impl.PushExecutorTask;

/**
 * push发送服务
 * 
 */
@Controller
public class PushSendController {

	private static final Logger logger = LoggerFactory.getLogger(PushSendController.class);

	@ResponseBody
	@RequestMapping(value = "/send/push", method = RequestMethod.POST)
	public Result pushMsg(PushMessage pushMessage) {

		if (logger.isDebugEnabled()) {
			logger.debug("send push message start. " + pushMessage);
		}

		if (pushMessage != null) {
			try {

				PushExecutorTask.taskLists.putLast(pushMessage);

				return new Result(true, "success");
			} catch (InterruptedException e) {
			}
		}

		return new Result(false, "error");
	}

}
