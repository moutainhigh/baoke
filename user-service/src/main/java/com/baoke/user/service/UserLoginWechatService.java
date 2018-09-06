package com.baoke.user.service;

import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.WechatDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.user.domain.UserWechatInfo;

/**
 * 用户登录相关service
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午1:57:15
 */
public interface UserLoginWechatService {

	/**
	 * APP微信登录获取用户信息
	 * 
	 * @author zjm
	 * @date 2018年6月12日 下午2:07:35
	 * 
	 * @param wechatDto
	 * @return
	 * @throws MainException
	 */
	LoginResultDto loginApp(WechatDto wechatDto) throws MainException;

	/**
	 * 微信登录方法
	 * 
	 * @author zdy
	 * @date: 2018年7月16日 下午9:38:30
	 * @param wechatDto
	 * @return
	 */
	UserWechatInfo getLoginWechatResponse(WechatDto wechatDto);

}
