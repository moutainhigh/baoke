package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 修改订单商品数量
 * 
 * @author zjj
 * @date 2018年7月7日 下午3:36:44
 */
public class OrderItemNumDto extends BaseResultDto {

	private static final long serialVersionUID = -3265800086286469053L;

	// 父订单编号
	private String parentOrderNo;

	// 商品id
	private Long itemId;

	// 商品详情id
	private Long itemDetailId;

	// 支付方式
	private Integer num;

	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemDetailId() {
		return itemDetailId;
	}

	public void setItemDetailId(Long itemDetailId) {
		this.itemDetailId = itemDetailId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
