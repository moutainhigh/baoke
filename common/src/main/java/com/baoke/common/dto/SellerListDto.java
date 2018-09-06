package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.SellerInfoDto;

/**
 * 播主信息
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:50:07
 */
public class SellerListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<SellerInfoDto> sellerList;// 播主信息集合

	private PageInfo pageInfo;// 分页

	public SellerListDto() {
	}

	public SellerListDto(List<SellerInfoDto> sellerInfoList) {
		this.sellerList = sellerInfoList;
	}

	public SellerListDto(boolean success, String message, List<SellerInfoDto> sellerList) {
		super(success, message);
		this.sellerList = sellerList;
	}

	public List<SellerInfoDto> getSellerList() {
		return sellerList;
	}

	public void setSellerList(List<SellerInfoDto> sellerList) {
		this.sellerList = sellerList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
