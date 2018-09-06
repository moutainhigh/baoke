package com.baoke.user.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.user.dao.DeviceInfoDao;
import com.baoke.user.domain.DeviceInfo;
import com.baoke.user.manager.DeviceInfoManager;

/**
 * 设备信息manager实现类
 * 
 * @author zjm
 * @Date: 2018年6月5日 下午8:04:35
 */
@Component
@DataSource("coreDataSource")
public class DeviceInfoManagerImpl implements DeviceInfoManager {

	@Resource
	private DeviceInfoDao deviceInfoDao;

	@Override
	public DeviceInfo queryDeviceInfoById(long id) {
		return deviceInfoDao.queryDeviceInfo(new DeviceInfo(id));
	}

	@Override
	public DeviceInfo queryDeviceInfo(DeviceInfo deviceInfo) {
		return deviceInfoDao.queryDeviceInfo(deviceInfo);
	}

	@Override
	public long addDeviceInfo(DeviceInfo deviceInfo) {

		deviceInfoDao.addDeviceInfo(deviceInfo);

		return deviceInfo.getId();
	}

	@Override
	public int modifyDeviceInfo(DeviceInfo deviceInfo) {
		return deviceInfoDao.modifyDeviceInfo(deviceInfo);
	}

}
