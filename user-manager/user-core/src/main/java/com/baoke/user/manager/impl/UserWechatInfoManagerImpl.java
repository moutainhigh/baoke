package com.baoke.user.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.user.dao.UserWechatInfoDao;
import com.baoke.user.domain.UserWechatInfo;
import com.baoke.user.manager.UserWechatInfoManager;

@Component
@DataSource("coreDataSource")
public class UserWechatInfoManagerImpl implements UserWechatInfoManager {

	@Resource
	private UserWechatInfoDao userWechatInfoDao;

	@Override
	public UserWechatInfo queryUserWechatInfo(UserWechatInfo userWechatInfo) {
		return userWechatInfoDao.queryUserWechatInfo(userWechatInfo);
	}

	@Override
	public boolean isExistsUserWechatInfo(UserWechatInfo userWechatInfo) {
		return null != userWechatInfoDao.queryUserWechatInfo(userWechatInfo);
	}

	@Override
	public long addUserWechatInfo(UserWechatInfo userWechatInfo) {
		userWechatInfoDao.addUserWechatInfo(userWechatInfo);
		return userWechatInfo.getId();
	}

	@Override
	public int modifyUserWechatInfo(UserWechatInfo userWechatInfo) {
		return userWechatInfoDao.modifyUserWechatInfo(userWechatInfo);
	}

}
