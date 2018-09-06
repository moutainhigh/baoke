package com.baoke.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.user.domain.UserAddress;

/**
 * 收货地址dao
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:35:50
 */
public interface UserAddressDao {

	UserAddress queryDefaultAddressByUserId(@Param("userId") Long userId, @Param("status") int status);

	UserAddress queryUserAddressById(@Param("id") long id, @Param("status") int status);

	List<UserAddress> queryUserAddressByUserId(@Param("userId") long userId, @Param("status") int status);

	int addUserAddress(UserAddress userAddress);

	int modifyUserAddressById(UserAddress userAddress);

	int modifyUserDefaultAddressByUserId(@Param("userId") long userId, @Param("defaultFlag") int defaultFlag);

}
