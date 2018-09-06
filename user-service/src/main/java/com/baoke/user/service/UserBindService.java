package com.baoke.user.service;

import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.WechatDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 绑定服务
 * 
 * @author zdy
 * @date: 2018年7月16日 下午9:17:36
 */
public interface UserBindService {

	/**
	 * 微信登录后绑定手机号（并重新下发用户信息）
	 * 
	 * @date: 2018年6月6日 下午4:46:20
	 * 
	 * @author zjm
	 */
	UserInfoDto bindPhone(SmsDto smsDto) throws MainException;

	/**
	 * 手机登录后绑定微信（并重新下发用户信息）
	 * 
	 * @author wyh
	 * @date 2018年7月4日 下午1:56:56
	 * 
	 * @param userInfoDataDto
	 * @return
	 * @throws MainException
	 */
	UserInfoDto bindWechat(WechatDto wechatDto) throws MainException;
}
