package com.baoke.trade.manager;

import java.util.List;

import com.baoke.trade.domain.OrderShoppingCart;

/**
 * 购物车manager
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:09:02
 */
public interface OrderShoppingCartManager {

	/**
	 * 查询购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:09:15
	 */
	List<OrderShoppingCart> queryShoppingCartByUserId(long userId);

	/**
	 * 按id查询购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:09:24
	 */
	OrderShoppingCart queryShoppingCartById(long id);

	/**
	 * 查询购物车
	 * 
	 * @author zjj
	 * @date 2018年6月27日 上午11:08:59
	 * @param shoppingCart
	 */
	OrderShoppingCart queryShoppingCart(OrderShoppingCart shoppingCart);

	/**
	 * 加入购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:09:32
	 */
	long addShoppingCart(OrderShoppingCart shoppingCart);

	/**
	 * 购物车删除商品
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:09:42
	 */
	int deleteShoppingCart(String[] cartIdArray, Long userId);

	/**
	 * 修改购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:09:51
	 */
	int modifyShoppingCart(OrderShoppingCart shoppingCart);

	/**
	 * 根据userId,itemId,itemDetailId删除购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月23日 下午1:39:23
	 */
	int deleteShoppingCartByUserIdAndItemId(OrderShoppingCart shoppingCart);

}
