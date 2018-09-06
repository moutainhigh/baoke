package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 确认收货
 * 
 * @author zdy
 * @date: 2018年7月10日 下午5:00:22
 */
public class ConfirmReceiveItemResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {
		return this;
	}

}
