package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.DeviceInfoDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 上报设备信息并获取设备CODE响应体
 * 
 * @author zjm
 * @Date: 2018年6月5日 下午7:40:57
 */
public class CreateDeviceInfoResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	/** 设备CODE */
	private String deviceCode;

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {

		if (null == baseResultDto) {
			return this;
		}
		DeviceInfoDto deviceInfoDto = (DeviceInfoDto) baseResultDto;

		if (null != deviceInfoDto.getDeviceId()) {
			this.setDeviceCode(this.getCodeFromId(deviceInfoDto.getDeviceId()));
			deviceInfoDto.setDeviceId(null);
		}
		return this;
	}

}
