package com.baoke.user.dao;

import com.baoke.user.domain.UserWechatInfo;

public interface UserWechatInfoDao {

	UserWechatInfo queryUserWechatInfo(UserWechatInfo userWechatInfo);

	int addUserWechatInfo(UserWechatInfo userWechatInfo);

	int modifyUserWechatInfo(UserWechatInfo userWechatInfo);

}