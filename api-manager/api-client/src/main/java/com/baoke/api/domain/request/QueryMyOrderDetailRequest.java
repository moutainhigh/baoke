package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询我的订单详情
 * 
 * @author zjj
 * @date 2018年7月7日 下午9:50:57
 */
public class QueryMyOrderDetailRequest extends BaseRequestParam {
	private static final long serialVersionUID = -2455517648055390074L;

	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public BaseDto convert() throws MainException {
		OrderInfoDto orderDto = new OrderInfoDto();
		orderDto.setOrderNo(this.orderNo);
		return orderDto;
	}

}
