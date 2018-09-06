package com.baoke.trade.service;

import com.baoke.common.dto.api.OrderAddressDto;
import com.baoke.common.dto.api.OrderItemNumDto;
import com.baoke.common.dto.api.OrderPayDto;
import com.baoke.common.dto.api.OrderSubmitDto;
import com.baoke.common.exception.base.MainException;

public interface OrderSaveService {
	/**
	 * 提交并保存订单,去支付
	 * 
	 * @author wyh
	 * @date 2018年6月30日 下午3:13:21
	 * 
	 * @param orderSubmitDto
	 * @return
	 * @throws MainException
	 */
	OrderPayDto saveMyOrder(OrderSubmitDto orderSubmitDto) throws MainException;

	/**
	 * 修改订单，修改收货地址和支付类型
	 * 
	 * @author zjj
	 * @date 2018年7月7日 下午3:39:25
	 * @param saveOrderAddressDto
	 * @return
	 * @throws MainException
	 */
	OrderPayDto saveMyOrderAddress(OrderAddressDto orderAddressDto) throws MainException;

	/**
	 * 修改订单，修改商品数量
	 * 
	 * @author zjj
	 * @date 2018年7月11日 上午11:01:06
	 * @param saveMyOrderItemNumDto
	 * @return
	 * @throws MainException
	 */
	OrderPayDto saveMyOrderItemNum(OrderItemNumDto orderItemNumDto) throws MainException;

}
