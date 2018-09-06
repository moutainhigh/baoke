package com.baoke.user.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.OsEnum;
import com.baoke.common.dto.info.DeviceInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.StringUtil;
import com.baoke.user.domain.DeviceInfo;
import com.baoke.user.manager.DeviceInfoManager;
import com.baoke.user.service.DeviceInfoService;
import com.baoke.user.service.DeviceUserRelationService;

/**
 * 用户服务实现类
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午4:58:55
 */
@ServiceDefinition(value = "deviceService")
@Service("deviceService")
public class DeviceInfoServiceImpl implements DeviceInfoService {

	private static final Logger logger = Logger.getLogger(DeviceInfoServiceImpl.class);

	@Resource
	private DeviceInfoManager deviceInfoManager;

	@Resource
	private DeviceUserRelationService deviceUserRelationService;

	@Override
	@MethodDefinition(value = "createDeviceInfo")
	public DeviceInfoDto createDeviceInfo(DeviceInfoDto deviceInfoDto) throws MainException {
		if (null == deviceInfoDto) {
			throw new ParamInvalidException();
		}

		DeviceInfo deviceInfo = new DeviceInfo();
		final BeanCopier deviceInfoBc = BeanCopier.create(deviceInfoDto.getClass(), deviceInfo.getClass(), false);
		deviceInfoBc.copy(deviceInfoDto, deviceInfo, null);

		if (deviceInfoDto.getOs().equals(OsEnum.IOS.getCode())) {
			if (StringUtil.hasLength(deviceInfoDto.getIfa())) {
				deviceInfo.setIfa(deviceInfoDto.getIfa());
			} else if (StringUtil.hasLength(deviceInfoDto.getMac())) {
				deviceInfo.setMac(deviceInfoDto.getMac());
			} else {
				logger.error("save device info error, IOS必须上传ifa或者mac参数中的至少一个, os=" + deviceInfoDto.getOs()
						+ ", userId=" + deviceInfoDto.getUserId());
				throw new ParamInvalidException("必须上传ifa或者mac");
			}
		} else if (deviceInfoDto.getOs().equals(OsEnum.ANDROID.getCode())) {
			if (StringUtil.hasLength(deviceInfoDto.getImei()) && StringUtil.hasLength(deviceInfoDto.getImsi())) {
				deviceInfo.setImei(deviceInfoDto.getImei());
				deviceInfo.setImsi(deviceInfoDto.getImsi());
			} else {
				logger.error("save device info error, ANDROID必须上传Imei和Imsi参数, os=" + deviceInfoDto.getOs() + ", userId="
						+ deviceInfoDto.getUserId());
				throw new ParamInvalidException("必须上传Imei和Imsi");
			}
		} else {
			logger.error("save device info error, os is empty, os=" + deviceInfoDto.getOs() + ", userId="
					+ deviceInfoDto.getUserId());
			throw new ParamInvalidException("必须上传OS");
		}

		long deviceId = 0l;
		DeviceInfo queryDeviceInfo = deviceInfoManager.queryDeviceInfo(deviceInfo);
		if (null != queryDeviceInfo) {
			if (logger.isDebugEnabled()) {
				logger.debug("create device info, modify deviceInfo, " + deviceInfo);
			}
			deviceId = queryDeviceInfo.getId();
			deviceInfo.setId(deviceId);
			deviceInfoManager.modifyDeviceInfo(deviceInfo);
		} else {
			deviceId = deviceInfoManager.addDeviceInfo(deviceInfo);
			logger.info("create device info, add deviceInfo, " + deviceInfo);
		}

		if (deviceId == 0l) {
			logger.error("create device info error, " + deviceInfo);
			return new DeviceInfoDto(false, "上传设备信息失败", deviceId);
		}

		// 插入设备用户关系表
		if (null != deviceInfoDto.getUserId() && deviceInfoDto.getUserId() != 0L) {
			deviceUserRelationService.addDeviceUserRelation(deviceInfoDto.getUserId(), deviceId);
		}
		return new DeviceInfoDto(true, "", deviceId);
	}

}
