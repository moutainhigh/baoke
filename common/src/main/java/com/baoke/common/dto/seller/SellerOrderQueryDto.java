package com.baoke.common.dto.seller;

import com.baoke.common.dto.base.BaseDto;

/**
 * 主播订单查询dto
 * 
 * @author: wyj
 * @date: 2018年7月11日 上午11:07:57
 */
public class SellerOrderQueryDto extends BaseDto {

	private static final long serialVersionUID = 1951466014427741117L;

	// 昵称
	private String nickName;

	private Long sellerId;

	// 订单编号
	private String orderNo;

	// 订单状态
	private Integer status;

	// 创建时间
	private Long startTime;

	// 修改时间
	private Long endTime;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

}
