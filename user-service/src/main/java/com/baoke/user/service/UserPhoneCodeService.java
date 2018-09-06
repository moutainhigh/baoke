package com.baoke.user.service;

import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.base.MainException;

/**
 * 验证码发送service
 * 
 * @author zjm
 * @date: 2018年7月4日 下午7:54:41
 */
public interface UserPhoneCodeService {

	/**
	 * 获取手机登录验证码
	 * 
	 * @author zjm
	 * @date 2018年6月6日 下午2:44:36
	 * 
	 * @param userInfoDto
	 * @return
	 * @throws MainException
	 */
	BaseResultDto createLoginPhoneCode(SmsDto smsDto) throws MainException;

	/**
	 * 微信绑定手机号—获取短信验证码
	 * 
	 * @author zjm
	 * @date: 2018年7月4日 下午5:32:18
	 * @param smsDto
	 * @return
	 * @throws MainException
	 */
	BaseResultDto createBindPhoneCode(SmsDto smsDto) throws MainException;
}
