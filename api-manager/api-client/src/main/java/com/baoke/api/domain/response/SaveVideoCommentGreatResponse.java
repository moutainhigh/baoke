package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

public class SaveVideoCommentGreatResponse extends BaseResponseParam {

	private static final long serialVersionUID = -2899785654035349571L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}
}
