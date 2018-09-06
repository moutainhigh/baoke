package com.baoke.user.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.user.domain.DeviceUserRelation;
import com.baoke.user.manager.DeviceUserRelationManager;
import com.baoke.user.service.DeviceUserRelationService;

@Service("deviceUserRelationService")
public class DeviceUserRelationServiceImpl implements DeviceUserRelationService {

	private static final Logger logger = Logger.getLogger(DeviceUserRelationServiceImpl.class);

	@Resource
	private DeviceUserRelationManager deviceUserRelationManager;

	@Override
	public void addDeviceUserRelation(Long userId, Long deviceId) throws MainException {
		if (null == userId || userId == 0L || null == deviceId || deviceId == 0L) {
			throw new ParamInvalidException("参数错误");
		}

		DeviceUserRelation deviceUserRelation = deviceUserRelationManager
				.queryDeviceUserRelation(new DeviceUserRelation(userId));
		if (null == deviceUserRelation) {
			deviceUserRelation = new DeviceUserRelation(deviceId, userId);
			deviceUserRelationManager.addDeviceUserRelation(deviceUserRelation);
		} else {
			if (deviceUserRelation.getDeviceId() != deviceId) {
				deviceUserRelation = new DeviceUserRelation(deviceId, userId);
				deviceUserRelationManager.addDeviceUserRelation(deviceUserRelation);
			}
		}
		logger.info("add device user relation, userId=" + userId + ", deviceId=" + deviceId);

	}

}
