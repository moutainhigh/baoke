package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.OrderItemNumDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 修改订单商品数量
 * 
 * @author zjj
 * @date 2018年7月7日 下午10:12:08
 */
public class SaveMyOrderItemNumRequest extends BaseRequestParam {

	private static final long serialVersionUID = -3265800086286469053L;

	private String parentOrderNo;

	private Long itemId;

	private Long itemDetailId;

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

	@Override
	public BaseDto convert() throws MainException {
		OrderItemNumDto orderItemNumDto = new OrderItemNumDto();
		orderItemNumDto.setParentOrderNo(parentOrderNo);
		orderItemNumDto.setItemDetailId(itemDetailId);
		orderItemNumDto.setItemId(itemId);
		orderItemNumDto.setNum(num);
		return orderItemNumDto;
	}

}
