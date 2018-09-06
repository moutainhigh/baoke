package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 确认收货
 * 
 * @author zdy
 * @date: 2018年7月10日 下午4:58:41
 */
public class ConfirmReceiveItemRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	// 子订单编号
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public BaseDto convert() throws MainException {
		OrderInfoDto orderInfoDto = new OrderInfoDto();
		orderInfoDto.setOrderNo(orderNo);
		return orderInfoDto;
	}

}
