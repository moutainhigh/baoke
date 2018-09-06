package com.baoke.user.service;

import com.baoke.user.domain.UserWechatInfo;

/**
 * 微信用户
 * 
 * @author zdy
 * @date: 2018年7月16日 下午9:44:56
 */
public interface UserWechatInfoService {

	/**
	 * 保存微信用户，判断是否存在该用户，存在则更新，否则新增
	 * 
	 * @author zdy
	 * @date: 2018年7月16日 下午9:45:32
	 * @param userWechatInfo
	 * @return
	 */
	public boolean saveUserWechatInfo(UserWechatInfo userWechatInfo);

}
