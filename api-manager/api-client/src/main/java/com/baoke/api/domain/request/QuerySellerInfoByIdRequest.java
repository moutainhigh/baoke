package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;

public class QuerySellerInfoByIdRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 卖家id */
	private Long sellerId;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public BaseDto convert() throws MainException {
		SellerInfoDto sellerInfoDto = new SellerInfoDto();
		sellerInfoDto.setSellerId(sellerId);
		return sellerInfoDto;
	}

}
