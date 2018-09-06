package com.baoke.user.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.db.DataSource;
import com.baoke.user.constant.UserAddressDefaultEnum;
import com.baoke.user.dao.UserAddressDao;
import com.baoke.user.domain.UserAddress;
import com.baoke.user.manager.UserAddressManager;

/**
 * 收货地址manger实现
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:50:27
 */
@Component
@DataSource("coreDataSource")
public class UserAddressManagerImpl implements UserAddressManager {

	@Resource
	private UserAddressDao userAddressDao;

	@Override
	public UserAddress queryUserAddressById(long id, BooleanEnum booleanEnum) {
		return userAddressDao.queryUserAddressById(id, booleanEnum.getCode());
	}

	@Override
	public UserAddress queryDefaultAddressByUserId(long userId, BooleanEnum booleanEnum) {
		return userAddressDao.queryDefaultAddressByUserId(userId, booleanEnum.getCode());
	}

	@Override
	public List<UserAddress> queryUserAddressByUserId(long userId, BooleanEnum booleanEnum) {
		return userAddressDao.queryUserAddressByUserId(userId, booleanEnum.getCode());
	}

	@Override
	public long addUserAddress(UserAddress userAddress) {
		userAddressDao.addUserAddress(userAddress);
		return userAddress.getId();
	}

	@Override
	public int modifyUserAddressById(UserAddress userAddress) {
		return userAddressDao.modifyUserAddressById(userAddress);
	}

	@Override
	public int modifyUserNewDefaultAddress(long userId, long addressId) {
		// 去掉原来的默认值
		this.modifyUserDefaultAddressByUserId(userId, UserAddressDefaultEnum.NO);

		// 新设置新默认值
		UserAddress userAddress = new UserAddress(addressId, UserAddressDefaultEnum.YES);
		return userAddressDao.modifyUserAddressById(userAddress);
	}

	@Override
	public int modifyUserDefaultAddressByUserId(long userId, UserAddressDefaultEnum userAddressDefaultEnum) {
		return userAddressDao.modifyUserDefaultAddressByUserId(userId, userAddressDefaultEnum.getCode());
	}

}
