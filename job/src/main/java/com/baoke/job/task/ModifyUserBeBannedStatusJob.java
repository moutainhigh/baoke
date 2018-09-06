package com.baoke.job.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.baoke.user.service.UserInfoManagerService;

/**
 * 解除禁言定时
 * 
 * @author zjm
 * @date: 2018年7月25日 下午2:53:24
 */
public class ModifyUserBeBannedStatusJob {

	private static final Logger logger = Logger.getLogger(ModifyUserBeBannedStatusJob.class);

	@Resource
	private UserInfoManagerService userInfoManagerService;

	public void modifyUserBeBannedStatus() {
		logger.info("解除禁言定时启动");
		try {
			int count = userInfoManagerService.modifyUserBeBannedStatusByJob();
			logger.info("解除禁言定时任务执行成功，解禁" + count + "名用户");
		} catch (Exception e) {
			logger.error("解除禁言定时异常：", e);
		}
		logger.info("解除禁言定时结束");
	}
}
