package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.SellerAuditInfoDto;

/**
 * admin主播认证数据
 * 
 * @author ljj
 * @date: 2018年7月7日 下午3:51:55
 */
public class SellerAuditListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<SellerAuditInfoDto> SellerAuditInfoList;

	private PageInfo pageInfo;

	public SellerAuditListDto() {
		super();
	}

	public SellerAuditListDto(List<SellerAuditInfoDto> sellerAuditInfoList, PageInfo pageInfo) {
		super();
		SellerAuditInfoList = sellerAuditInfoList;
		this.pageInfo = pageInfo;
	}

	public List<SellerAuditInfoDto> getSellerAuditInfoList() {
		return SellerAuditInfoList;
	}

	public void setSellerAuditInfoList(List<SellerAuditInfoDto> sellerAuditInfoList) {
		SellerAuditInfoList = sellerAuditInfoList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
