package com.baoke.user.service;

import com.baoke.common.dto.api.UserSuggestionDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.base.MainException;

/**
 * 用户的投诉与建议
 * 
 * @author zdy
 * @date: 2018年7月10日 上午9:16:02
 */
public interface UserSuggestionService {
	/**
	 * 设置-保存用户的投诉与建议
	 * 
	 * @author zdy
	 * @date: 2018年7月10日 下午2:09:18
	 * @param suggestionDto
	 * @return
	 * @throws MainException
	 */
	BaseResultDto saveSuggestion(UserSuggestionDto suggestionDto) throws MainException;
}
