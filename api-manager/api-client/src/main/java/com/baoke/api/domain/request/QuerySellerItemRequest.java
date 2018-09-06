package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询播主（卖家）商品页入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:30:26
 */
public class QuerySellerItemRequest extends BaseRequestParam {
	private static final long serialVersionUID = 1L;

	private Long sellerId;// 播主Id
	
	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 20;// 每页显示的记录数

	public QuerySellerItemRequest() {
		super();
	}
	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}
	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
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
