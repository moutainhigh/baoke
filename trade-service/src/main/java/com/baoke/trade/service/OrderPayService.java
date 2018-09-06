package com.baoke.trade.service;

import com.baoke.common.dto.api.OrderPaySignDto;
import com.baoke.common.exception.base.MainException;

public interface OrderPayService {

	/**
	 * 订单支付
	 * 
	 * @author wyh
	 * @date 2018年6月30日 下午3:13:27
	 * 
	 * @param orderDto
	 * @return
	 * @throws MainException
	 */
	OrderPaySignDto payMyOrder(OrderPaySignDto orderPaySignDto) throws MainException;

}
