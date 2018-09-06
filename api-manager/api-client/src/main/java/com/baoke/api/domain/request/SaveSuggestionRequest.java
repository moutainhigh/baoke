package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.UserSuggestionDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 设置－保存投诉与建议
 * 
 * @author zdy
 * @date: 2018年7月10日 上午9:20:48
 */
public class SaveSuggestionRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private String content;// 投诉内容

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public BaseDto convert() throws MainException {
		UserSuggestionDto userSuggestionDto = new UserSuggestionDto();
		userSuggestionDto.setContent(content);
		return userSuggestionDto;
	}

}
