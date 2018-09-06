package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 视频喜欢（不喜欢）响应体
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:03:33
 */
public class SaveVideoDelikeResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
