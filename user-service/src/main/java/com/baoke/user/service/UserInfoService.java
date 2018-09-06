package com.baoke.user.service;

import com.baoke.common.dto.UserDto;
import com.baoke.common.dto.UserListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;

/**
 * 用户服务service
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午4:55:33
 */
public interface UserInfoService {

	/**
	 * 根据ID查询用户信息
	 * 
	 * @author wyh
	 * @date 2018年7月4日 下午12:16:56
	 * 
	 * @param baseDto
	 *            userId不为空
	 * @return
	 */
	UserInfoDto queryUserInfoById(BaseDto baseDto) throws MainException;

	/**
	 * 根据ID查询播主（卖家）信息
	 * 
	 * @author zdy
	 * @date: 2018年7月6日 下午8:44:36
	 * @param sellerInfoDto
	 *            sellerId不为空
	 * @return
	 */
	SellerInfoDto querySellerInfoById(SellerInfoDto sellerInfoDto) throws MainException;

	/**
	 * 获取用户信息
	 * 
	 * @date: 2018年6月4日 下午4:56:49
	 * 
	 * @author zjm
	 * @throws ParamInvalidException
	 */
	UserInfoDto queryUserInfo(UserInfoDto userInfoDto) throws MainException;

	/**
	 * 查询用户列表
	 * 
	 * @author zjm
	 * @date: 2018年7月4日 上午11:25:15
	 * @param userInfoDto
	 * @return
	 */
	UserListDto queryUserInfoListByPage(UserDto userDto) throws MainException;

}
