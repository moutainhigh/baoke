package com.baoke.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.api.domain.AppVersion;
import com.baoke.api.manager.AppVersionManager;
import com.baoke.api.service.VersionService;
import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.api.AppVersionDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.user.domain.DeviceInfo;
import com.baoke.user.manager.DeviceInfoManager;

@ServiceDefinition("versionService")
@Service("versionService")
public class VersionServiceImpl implements VersionService {

	@Resource
	private AppVersionManager appVersionManager;

	@Resource
	private DeviceInfoManager deviceInfoManager;

	@MethodDefinition(value = "queryNewVersion", needLogin = false, needCheckVersion = false)
	@Override
	public AppVersionDto queryNewVersion(AppVersionDto appVersionDto) throws MainException {

		if (appVersionDto == null || appVersionDto.getDeviceId() == null || appVersionDto.getDeviceId() <= 0) {
			throw new ParamInvalidException("参数无效");
		}

		// 查询设备信息
		DeviceInfo deviceInfo = deviceInfoManager.queryDeviceInfoById(appVersionDto.getDeviceId());
		if (null == deviceInfo) {
			throw new ParamInvalidException("设备无效");
		}
		AppVersion appVersion = appVersionManager.queryNewVersionByChannelAndVersionNo(deviceInfo.getChannel(),
				deviceInfo.getAppVersion());

		// 查询强制升级标记
		BooleanEnum isForce = BooleanEnum.FALSE;
		if (null != appVersion) {
			int count = appVersionManager.queryNewVersionIsForceByChannelAndVersionNo(deviceInfo.getChannel(),
					deviceInfo.getAppVersion());
			isForce = (count > 0) ? BooleanEnum.TRUE : BooleanEnum.FALSE;
		}

		return convert(appVersion, isForce);
	}

	private AppVersionDto convert(AppVersion appVersion, BooleanEnum isForce) {
		AppVersionDto appVersionDto = new AppVersionDto(true, "");
		if (null == appVersion) {
			appVersionDto.setHasNew(BooleanEnum.FALSE.getCode());
		} else {
			appVersionDto.setVersionNo(appVersion.getVersionNo());
			appVersionDto.setContent(appVersion.getContent());
			appVersionDto.setHasNew(BooleanEnum.TRUE.getCode());
			appVersionDto.setIsForce(isForce.getCode());
			appVersionDto.setUpdateUrl(appVersion.getUpdateUrl());
		}
		return appVersionDto;
	}

}
