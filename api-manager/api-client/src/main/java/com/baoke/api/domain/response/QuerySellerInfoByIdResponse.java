package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.ParamInvalidException;

public class QuerySellerInfoByIdResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private SellerInfoDto sellerInfo;// 播主（卖家）

	public SellerInfoDto getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfoDto sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {
		if (null == baseResultDto) {
			return this;
		}
		this.sellerInfo = (SellerInfoDto) baseResultDto;
		return this;
	}

}
