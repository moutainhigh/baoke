package com.baoke.trade.service;

import com.baoke.common.dto.api.ShoppingCartDto;
import com.baoke.common.dto.api.ShoppingCartItemListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.trade.domain.OrderPay;

/**
 * 购物车相关
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:29:28
 */
public interface OrderShoppingCartService {

	/**
	 * 查询购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:29:35
	 */
	ShoppingCartItemListDto queryShoppingCart(ShoppingCartDto shoppingCartParamDto) throws ParamInvalidException;

	/**
	 * 加入购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:29:41
	 */
	BaseResultDto addShoppingCart(ShoppingCartDto shoppingCartParamDto) throws ParamInvalidException;

	/**
	 * 购物车删除商品
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:29:48
	 */
	BaseResultDto deleteShoppingCart(ShoppingCartDto shoppingCartParamDto) throws ParamInvalidException;

	/**
	 * 购物车修改商品数量
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:29:55
	 */
	BaseResultDto updateShoppingCartNum(ShoppingCartDto shoppingCartParamDto) throws ParamInvalidException;

	/**
	 * 删除购物车商品，回调后使用
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午7:24:46
	 * @param orderPay
	 */
	boolean deleteShoppingCart(OrderPay orderPay);

}
