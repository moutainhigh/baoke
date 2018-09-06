package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 商城－查询商城首页
 * 
 * @author zdy
 * @date: 2018年7月4日 下午5:20:52
 */
public class QueryMallHomeRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseDto convert() throws MainException {
		return new BaseDto();
	}

}
