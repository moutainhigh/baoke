package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.SellerListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.SellerInfoDto;

public class QueryMyFocusSellerResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private List<SellerInfoDto> sellerInfoList;// 关注的播主信息集合

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

	public List<SellerInfoDto> getSellerInfoList() {
		return sellerInfoList;
	}

	public void setSellerInfoList(List<SellerInfoDto> sellerInfoList) {
		this.sellerInfoList = sellerInfoList;
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
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		SellerListDto sellerListDto = (SellerListDto) baseResultDto;
		this.sellerInfoList = sellerListDto.getSellerList();
		if (sellerListDto.getPageInfo() != null) {
			this.curPageNo = sellerListDto.getPageInfo().getCurrent();
			this.pageSize = sellerListDto.getPageInfo().getPageSize();
		}
		return this;
	}

}
