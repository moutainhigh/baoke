package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 保存用户投诉与建议
 * 
 * @author zdy
 * @date: 2018年7月10日 上午9:29:51
 */
public class SaveSuggestionResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
