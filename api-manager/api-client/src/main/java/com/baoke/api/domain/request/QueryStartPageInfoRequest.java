package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询启动页配置信息
 * 
 * @author zdy
 * @date: 2018年7月10日 下午3:38:07
 */
public class QueryStartPageInfoRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseDto convert() throws MainException {
		return new BaseDto();
	}

}
