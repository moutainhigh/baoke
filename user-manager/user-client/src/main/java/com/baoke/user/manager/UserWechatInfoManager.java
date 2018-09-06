package com.baoke.user.manager;

import com.baoke.user.domain.UserWechatInfo;

/**
 * 微信用户Manager
 * 
 * @author zjm
 * @Date: 2018年6月12日 下午3:44:00
 */
public interface UserWechatInfoManager {

	/**
	 * 查询用户信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:34:47
	 * @param userWechatInfo
	 * @return
	 */
	UserWechatInfo queryUserWechatInfo(UserWechatInfo userWechatInfo);

	/**
	 * 判断用户是否存在
	 * 
	 * @author wyh
	 * @date 2018年7月4日 下午12:40:22
	 * 
	 * @param userWechatInfo
	 * @return
	 */
	boolean isExistsUserWechatInfo(UserWechatInfo userWechatInfo);

	/**
	 * 创建微信用户信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:34:40
	 * @param userWechatInfo
	 * @return
	 */
	long addUserWechatInfo(UserWechatInfo userWechatInfo);

	/**
	 * 更新用户信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:34:53
	 * @param userWechatInfo
	 * @return
	 */
	int modifyUserWechatInfo(UserWechatInfo userWechatInfo);

}
