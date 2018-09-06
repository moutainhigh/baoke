package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 保存关注播主（卖家）回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:33:40
 */
public class SaveFocusSellerResponse extends BaseResponseParam {
	private static final long serialVersionUID = 1L;

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
