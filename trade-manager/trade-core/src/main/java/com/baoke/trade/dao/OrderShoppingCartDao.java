package com.baoke.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.trade.domain.OrderShoppingCart;

/**
 * 购物车Dao
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:11:43
 */
public interface OrderShoppingCartDao {

	List<OrderShoppingCart> queryShoppingCartByUserId(long userId);

	OrderShoppingCart queryShoppingCartById(long Id);

	OrderShoppingCart queryShoppingCart(OrderShoppingCart shoppingCart);

	int addShoppingCart(OrderShoppingCart shoppingCart);

	int deleteShoppingCart(@Param("id") long id, @Param("userId") long userId);

	int modifyShoppingCart(OrderShoppingCart shoppingCart);

	int deleteShoppingCartByUserIdAndItemId(OrderShoppingCart shoppingCart);

}
