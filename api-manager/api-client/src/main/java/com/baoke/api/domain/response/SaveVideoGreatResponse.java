package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 保存视频点赞回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:46:24
 */
public class SaveVideoGreatResponse extends BaseResponseParam {
	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
