package com.baoke.user.service;

import com.baoke.common.exception.base.MainException;

/**
 * 设备用户关系服务
 * 
 * @author zjj
 * @date 2018年7月19日 下午7:18:11
 */
public interface DeviceUserRelationService {

	/**
	 * 添加用户关系
	 * 
	 * @author zjj
	 * @date 2018年7月19日 下午7:19:34
	 * @param userId
	 * @param deviceId
	 * @return
	 */
	void addDeviceUserRelation(Long userId, Long deviceId) throws MainException;

}
