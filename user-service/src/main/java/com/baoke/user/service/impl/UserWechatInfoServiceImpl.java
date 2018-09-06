package com.baoke.user.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.user.constant.UserWechatStatusEnum;
import com.baoke.user.domain.UserWechatInfo;
import com.baoke.user.manager.UserWechatInfoManager;
import com.baoke.user.service.UserWechatInfoService;

@ServiceDefinition(value = "userWechatInfoService")
@Service("userWechatInfoService")
public class UserWechatInfoServiceImpl implements UserWechatInfoService {

	private static final Logger logger = Logger.getLogger(UserWechatInfoServiceImpl.class);

	@Resource
	private UserWechatInfoManager userWechatInfoManager;

	@Override
	public boolean saveUserWechatInfo(UserWechatInfo userWechatInfo) {

		WechatUserSourceTypeEnum wechatUserSourceTypeEnum = WechatUserSourceTypeEnum
				.getWechatSourceTypeByCode(userWechatInfo.getSourceType());
		if (!userWechatInfoManager.isExistsUserWechatInfo(new UserWechatInfo(userWechatInfo.getOpenId(),
				userWechatInfo.getUnionId(), wechatUserSourceTypeEnum))) {
			logger.info("wechat add user info, add userWechatInfo, " + userWechatInfo);
			userWechatInfo.setStatus(UserWechatStatusEnum.NORMAL.getCode());
			userWechatInfoManager.addUserWechatInfo(userWechatInfo);
		} else {
			logger.info("wechat modify user info, modify userWechatInfo, " + userWechatInfo);
			userWechatInfoManager.modifyUserWechatInfo(userWechatInfo);
		}
		return true;
	}

}
