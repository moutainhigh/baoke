package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

public class UserSuggestionDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private String content;// 投诉与建议内容

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
