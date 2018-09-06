package com.baoke.user.service;

import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.exception.base.MainException;

/**
 * 用户登录相关service
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午1:57:15
 */
public interface UserLoginSmsService {

	/**
	 * 手机登录并获取用户信息
	 * 
	 * @author zjm
	 * @date 2018年6月6日 下午4:03:29
	 * 
	 * @param smsDto
	 * @return
	 * @throws MainException
	 */
	LoginResultDto loginSms(SmsDto smsDto) throws MainException;

}
