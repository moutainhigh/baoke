package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.OrderInfoDto;

/**
 * 订单详情响应
 * 
 * @author zjj
 * @date 2018年7月7日 下午10:02:51
 */
public class QueryMyOrderDetailResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1655795840839022067L;

	// 订单详情
	private OrderInfoDto orderInfo;

	public OrderInfoDto getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfoDto orderInfo) {
		this.orderInfo = orderInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		OrderInfoDto orderInfoDto = (OrderInfoDto) baseResultDto;
		this.orderInfo = orderInfoDto;
		return this;
	}

}
