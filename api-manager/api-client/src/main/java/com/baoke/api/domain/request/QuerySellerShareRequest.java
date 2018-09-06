package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询播主首页分享数据
 * 
 * @author zdy
 * @date: 2018年7月26日 下午9:23:05
 */
public class QuerySellerShareRequest extends BaseRequestParam {

	private static final long serialVersionUID = -8266169440814038531L;

	private Long sellerId;// 播主Id

	@Override
	public BaseDto convert() throws MainException {
		SellerInfoDto sellerInfoDto = new SellerInfoDto();
		sellerInfoDto.setSellerId(sellerId);
		return sellerInfoDto;
	}

}
