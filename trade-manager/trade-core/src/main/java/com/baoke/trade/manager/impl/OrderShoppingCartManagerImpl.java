package com.baoke.trade.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.util.StringUtil;
import com.baoke.trade.dao.OrderShoppingCartDao;
import com.baoke.trade.domain.OrderShoppingCart;
import com.baoke.trade.manager.OrderShoppingCartManager;

/**
 * 购物车manager实现
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:24:51
 */
@Component
@DataSource("coreDataSource")
public class OrderShoppingCartManagerImpl implements OrderShoppingCartManager {

	@Resource
	private OrderShoppingCartDao shoppingCartDao;

	@Override
	public OrderShoppingCart queryShoppingCartById(long id) {
		return shoppingCartDao.queryShoppingCartById(id);
	}

	@Override
	public List<OrderShoppingCart> queryShoppingCartByUserId(long userId) {
		return shoppingCartDao.queryShoppingCartByUserId(userId);
	}

	@Override
	public OrderShoppingCart queryShoppingCart(OrderShoppingCart shoppingCart) {
		return shoppingCartDao.queryShoppingCart(shoppingCart);
	}

	@Override
	public long addShoppingCart(OrderShoppingCart shoppingCart) {
		shoppingCartDao.addShoppingCart(shoppingCart);
		return shoppingCart.getId();
	}

	@Override
	public int deleteShoppingCart(String[] cartIdArray, Long userId) {
		int successNum = 0;
		for (int i = 0; i < cartIdArray.length; i++) {
			if (StringUtil.isNotBlank(cartIdArray[i])) {
				Long cartId = Long.parseLong(cartIdArray[i]);
				int result = shoppingCartDao.deleteShoppingCart(cartId, userId);
				successNum += result;
			}
		}
		return successNum;
	}

	@Override
	public int modifyShoppingCart(OrderShoppingCart shoppingCart) {
		return shoppingCartDao.modifyShoppingCart(shoppingCart);
	}

	@Override
	public int deleteShoppingCartByUserIdAndItemId(OrderShoppingCart shoppingCart) {
		return shoppingCartDao.deleteShoppingCartByUserIdAndItemId(shoppingCart);
	}

}
