package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 保存关注播主（卖家）入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:33:18
 */
public class SaveFocusSellerRequest extends BaseRequestParam {
	private static final long serialVersionUID = 1L;

	/** 主播id */
	private Long sellerId;

	/** 状态 0：取消关注；1：关注 */
	private Integer isFocus;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(Integer isFocus) {
		this.isFocus = isFocus;
	}

	@Override
	public BaseDto convert() throws MainException {
		SellerInfoDto sellerInfoDto = new SellerInfoDto();
		sellerInfoDto.setSellerId(sellerId);
		sellerInfoDto.setIsFocus(isFocus);
		return sellerInfoDto;
	}
}
