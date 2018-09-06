package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询我关注的主播入参
 * 
 * @author zdy
 * @date: 2018年6月15日 下午7:05:13
 */
public class QueryMyFocusSellerRequest extends BaseRequestParam {

	private static final long serialVersionUID = -6110536313628955255L;

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 12;// 每页显示的记录数

	public QueryMyFocusSellerRequest() {
		super();
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
		SellerInfoDto sellerInfoDto = new SellerInfoDto(this.curPageNo, this.pageSize);
		return sellerInfoDto;
	}

}
