package com.baoke.common.dto.info;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 物流信息
 * 
 * @author wyh
 * @date 2018年6月30日 下午5:03:10
 *
 */
public class PostageInfoDto extends BaseResultDto {

	private static final long serialVersionUID = -3589797763424615665L;
	// 订单号
	private String orderNo;

	// 物流公司
	private String companyName;

	// 物流公司logo
	private String logoUrl;

	// 物流单号
	private String postageNo;

	// 状态
	private Integer status;

	// 状态描述
	private String statusDesc;

	// 物流节点信息
	private List<PostageDetailInfoDto> postageDetailList;

	public PostageInfoDto() {
		super();
	}

	public PostageInfoDto(boolean success, String message) {
		super(success, message);
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getPostageNo() {
		return postageNo;
	}

	public void setPostageNo(String postageNo) {
		this.postageNo = postageNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<PostageDetailInfoDto> getPostageDetailList() {
		return postageDetailList;
	}

	public void setPostageDetailList(List<PostageDetailInfoDto> postageDetailList) {
		this.postageDetailList = postageDetailList;
	}

}
