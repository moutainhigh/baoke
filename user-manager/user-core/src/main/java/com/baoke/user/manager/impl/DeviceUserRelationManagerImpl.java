package com.baoke.user.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.user.dao.DeviceUserRelationDao;
import com.baoke.user.domain.DeviceUserRelation;
import com.baoke.user.manager.DeviceUserRelationManager;

/**
 * 用户设备关联manager实现类
 * 
 * @author zjm
 * @Date: 2018年6月5日 下午8:51:12
 */
@Component
@DataSource("coreDataSource")
public class DeviceUserRelationManagerImpl implements DeviceUserRelationManager {

	@Resource
	private DeviceUserRelationDao deviceUserRelationDao;

	@Override
	public DeviceUserRelation queryDeviceUserRelation(DeviceUserRelation deviceUserRelation) {
		return deviceUserRelationDao.queryDeviceUserRelation(deviceUserRelation);
	}

	@Override
	public long addDeviceUserRelation(DeviceUserRelation deviceUserRelation) {
		deviceUserRelationDao.addDeviceUserRelation(deviceUserRelation);
		return deviceUserRelation.getId();
	}
}
