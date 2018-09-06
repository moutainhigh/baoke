package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 保存举报（吐槽）响应体
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:53:42
 */
public class SaveComplaintResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
