package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 保存已读响应
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:40:04
 */
public class SaveMyMessageReadFlagResponse extends BaseResponseParam {

	private static final long serialVersionUID = -523988938008109650L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
