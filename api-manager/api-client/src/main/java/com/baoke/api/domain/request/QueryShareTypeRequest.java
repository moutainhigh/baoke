package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询分享渠道
 * 
 * @author zdy
 * @date: 2018年7月9日 下午9:27:02
 */
public class QueryShareTypeRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseDto convert() throws MainException {
		return new BaseDto();
	}
}
