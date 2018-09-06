package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询播主（卖家）首页（视频页）入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:26:39
 */
public class QuerySellerVideoRequest extends BaseRequestParam {

	private static final long serialVersionUID = -8266169440814038531L;

	private Long sellerId;// 播主Id

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 12;// 每页显示的记录数

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public BaseDto convert() throws MainException {
		SellerInfoDto sellerUserInfoDto = new SellerInfoDto(curPageNo, pageSize);
		sellerUserInfoDto.setSellerId(this.sellerId);
		return sellerUserInfoDto;
	}
}
