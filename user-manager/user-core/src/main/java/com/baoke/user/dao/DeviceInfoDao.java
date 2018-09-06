package com.baoke.user.dao;

import com.baoke.user.domain.DeviceInfo;

public interface DeviceInfoDao {

	DeviceInfo queryDeviceInfo(DeviceInfo deviceInfo);

	int addDeviceInfo(DeviceInfo deviceInfo);

	int modifyDeviceInfo(DeviceInfo deviceInfo);

}