package com.baoke.user.manager;

import org.springframework.stereotype.Component;

import com.baoke.user.domain.DeviceUserRelation;

/**
 * 用户设备关联manager
 * 
 * @author zjm
 * @Date: 2018年6月5日 下午8:50:43
 */
@Component
public interface DeviceUserRelationManager {

	/**
	 * 创建关联
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:26:13
	 * @param deviceUserRelation
	 * @return
	 */
	long addDeviceUserRelation(DeviceUserRelation deviceUserRelation);

	/**
	 * 条件查询最新关联
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:26:23
	 * @param deviceUserRelation
	 * @return
	 */
	DeviceUserRelation queryDeviceUserRelation(DeviceUserRelation deviceUserRelation);
}
