package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 快递实体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:05:59
 */
public class PostageInfo extends BaseDomain {

	private static final long serialVersionUID = 5889202443655341824L;

	// 快递主键id
	private Long id;

	// 快递公司
	private String companyCode;

	// 快递单号
	private String postageNo;

	// 订单号
	private String orderNo;

	// 同一订单分多次发货，为空表示一次性发货
	private String orderItemIds;

	// 运费
	private Integer postage;

	// 快递状态
	private Integer status;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getPostageNo() {
		return postageNo;
	}

	public void setPostageNo(String postageNo) {
		this.postageNo = postageNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderItemIds() {
		return orderItemIds;
	}

	public void setOrderItemIds(String orderItemIds) {
		this.orderItemIds = orderItemIds;
	}

	public Integer getPostage() {
		return postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
