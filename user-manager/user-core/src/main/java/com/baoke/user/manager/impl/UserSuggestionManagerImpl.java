package com.baoke.user.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.user.dao.UserSuggestionDao;
import com.baoke.user.domain.UserSuggestion;
import com.baoke.user.manager.UserSuggestionManager;

/**
 * 用戶的投诉与建议
 * 
 * @author zdy
 * @date: 2018年7月10日 上午9:49:25
 */
@Component
@DataSource("miscDataSource")
public class UserSuggestionManagerImpl implements UserSuggestionManager {

	@Resource
	private UserSuggestionDao userSuggestionDao;

	@Override
	public long addUserSuggestion(UserSuggestion userSuggestion) {
		userSuggestionDao.addUserSuggestion(userSuggestion);
		return userSuggestion.getId();
	}

}
