package com.baoke.user.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.dto.api.UserSuggestionDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.StringUtil;
import com.baoke.user.domain.UserSuggestion;
import com.baoke.user.manager.UserSuggestionManager;
import com.baoke.user.service.UserSuggestionService;

/**
 * 设置－保存投诉与建议
 * 
 * @author zdy
 * @date: 2018年7月10日 上午9:35:37
 */
@Service("userSuggestionService")
@ServiceDefinition(value = "userSuggestionService")
public class UserSuggestionServiceImpl implements UserSuggestionService {
	private static final Logger logger = Logger.getLogger(UserSuggestionServiceImpl.class);

	@Resource
	private UserSuggestionManager userSuggestionManager;

	@MethodDefinition(value = "saveSuggestion", needLogin = true)
	@Override
	public BaseResultDto saveSuggestion(UserSuggestionDto suggestionDto) throws MainException {
		if (suggestionDto == null || suggestionDto.getUserId() == null || suggestionDto.getUserId() == 0L) {
			throw new ParamInvalidException("请先登陆!");
		}

		if (StringUtil.isEmpty(suggestionDto.getContent())) {
			throw new ParamInvalidException("请填写内容!");
		}

		UserSuggestion userSuggestion = new UserSuggestion(suggestionDto.getUserId(), suggestionDto.getContent());
		long reuslt = userSuggestionManager.addUserSuggestion(userSuggestion);
		if (reuslt > 0) {
			return new BaseResultDto(true, null);
		}

		logger.error("save user suggestion error, " + userSuggestion);
		return new BaseResultDto(false, "保存失败！");

	}

}
