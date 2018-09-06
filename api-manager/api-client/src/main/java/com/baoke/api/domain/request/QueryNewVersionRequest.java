package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.AppVersionDto;
import com.baoke.common.exception.base.MainException;

/**
 * 新版本检测
 */
public class QueryNewVersionRequest extends BaseRequestParam {

	private static final long serialVersionUID = -6706056216907370198L;

	@Override
	public AppVersionDto convert() throws MainException {
		return new AppVersionDto();
	}

}
