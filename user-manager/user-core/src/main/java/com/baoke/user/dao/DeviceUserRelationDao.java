package com.baoke.user.dao;

import com.baoke.user.domain.DeviceUserRelation;

public interface DeviceUserRelationDao {

	DeviceUserRelation queryDeviceUserRelation(DeviceUserRelation deviceUserRelation);

	int addDeviceUserRelation(DeviceUserRelation deviceUserRelation);

}