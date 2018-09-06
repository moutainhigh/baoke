package com.baoke.user.manager;

import java.util.List;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.user.constant.UserAddressDefaultEnum;
import com.baoke.user.domain.UserAddress;

/**
 * 收货地址manager
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:33:22
 */
public interface UserAddressManager {

	/**
	 * 根据ID查询收货地址
	 * 
	 * 注意不考虑可用状态，因为查询历史订单等场景时，就算数据被删除了也是要求能查出来
	 * 
	 * @author wyh
	 * @date 2018年7月21日 下午7:28:54
	 * 
	 * @param id
	 * @return
	 */
	UserAddress queryUserAddressById(long id, BooleanEnum booleanEnum);

	/**
	 * 通过userId查询默认地址
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:33:40
	 */
	UserAddress queryDefaultAddressByUserId(long userId, BooleanEnum booleanEnum);

	/**
	 * 根据用户id查询地址列表
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:33:49
	 */
	List<UserAddress> queryUserAddressByUserId(long userId, BooleanEnum booleanEnum);

	/**
	 * 新增地址
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:34:00
	 */
	long addUserAddress(UserAddress userAddress);

	/**
	 * 修改地址信息
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:34:10
	 */
	int modifyUserAddressById(UserAddress userAddress);

	/**
	 * 修改默认记录为指定的addressId(去掉数据库中其他所有记录的默认值)
	 * 
	 * @author zdy
	 * @date: 2018年6月23日 下午7:31:02
	 * @param userAddress
	 * @return
	 */
	int modifyUserNewDefaultAddress(long userId, long addressId);

	/**
	 * 修改用户默认地址状态
	 * 
	 * @author zdy
	 * @date: 2018年7月17日 上午10:36:45
	 * @param userAddress
	 * @return
	 */
	int modifyUserDefaultAddressByUserId(long userId, UserAddressDefaultEnum userAddressDefaultEnum);

}
