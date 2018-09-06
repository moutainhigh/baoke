package com.baoke.user.manager;

import com.baoke.user.domain.UserSuggestion;

/**
 * 设置－用戶投诉与建议
 * 
 * @author zdy
 * @date: 2018年7月10日 上午9:48:16
 */
public interface UserSuggestionManager {

	/**
	 * 设置－ 保存投诉与建议
	 * 
	 * @author zdy
	 * @date: 2018年7月10日 上午9:48:35
	 * @param userSuggestion
	 * @return
	 */
	long addUserSuggestion(UserSuggestion userSuggestion);
}
