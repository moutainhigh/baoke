package com.baoke.user.service;

import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.user.domain.UserWechatInfo;

/**
 * 小程序接口类
 * 
 * @author wyh
 * @date 2018年7月16日 下午3:31:54
 *
 */
public interface UserLoginSmallAppService {

	/**
	 * 小程序登录
	 * 
	 * @author wyh
	 * @date 2018年7月16日 下午3:32:35
	 * 
	 * @param wechatDto
	 * @return
	 * @throws MainException
	 */
	LoginResultDto loginSmallApp(UserWechatInfo userWechatInfo) throws MainException;

}
