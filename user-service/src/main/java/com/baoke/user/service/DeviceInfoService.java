package com.baoke.user.service;

import com.baoke.common.dto.info.DeviceInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;

/**
 * 用户服务service
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午4:55:33
 */
public interface DeviceInfoService {

	/**
	 * 上报设备信息并获取设备CODE
	 * 
	 * @author zjm
	 * @date: 2018年7月3日 上午9:16:31
	 * @param deviceInfoDto
	 * @return
	 * @throws ParamInvalidException
	 * @throws MainException
	 */
	DeviceInfoDto createDeviceInfo(DeviceInfoDto deviceInfoDto) throws ParamInvalidException, MainException;

}
