package com.baoke.user.service;

import com.baoke.common.dto.AddressListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 收货地址service
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:52:36
 */
public interface UserAddressService {

	/**
	 * 通过userId查询该用户的地址列表信息
	 * 
	 * @author: wyj
	 * @throws ParamInvalidException
	 * @date: 2018年6月13日 下午3:52:47
	 */
	AddressListDto queryAddressByUserId(AddressInfoDto addressInfoDto) throws ParamInvalidException;

	/**
	 * 保存我的地址
	 * 
	 * @author: wyj
	 * @throws ParamInvalidException
	 * @date: 2018年6月13日 下午3:52:54
	 */
	BaseResultDto saveMyAddress(AddressInfoDto addressInfoDto) throws ParamInvalidException;

	/**
	 * 根据地址id设置默认地址
	 * 
	 * @author: wyj
	 * @throws ParamInvalidException
	 * @date: 2018年6月13日 下午3:53:01
	 */
	BaseResultDto saveMyDefaultAddress(AddressInfoDto addressInfoDto) throws ParamInvalidException;

	/**
	 * 根据地址id删除地址
	 * 
	 * @author: wyj
	 * @throws ParamInvalidException
	 * @date: 2018年6月13日 下午3:53:11
	 */
	BaseResultDto deleteAddressById(AddressInfoDto addressInfoDto) throws ParamInvalidException;

}
