package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 提醒发货响应
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:26:46
 */
public class RemindSendItemResponse extends BaseResponseParam {

	private static final long serialVersionUID = -3605835790013343417L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
