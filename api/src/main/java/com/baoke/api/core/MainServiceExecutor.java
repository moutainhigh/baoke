package com.baoke.api.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.baoke.api.core.MainServiceScanner.ObjectAndMethod;
import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.util.BkBase64;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamMethodInvalidException;
import com.baoke.common.exception.UserEmptyException;
import com.baoke.common.util.IpUtil;
import com.baoke.common.util.StringUtil;

public class MainServiceExecutor {

	@Autowired
	private MainServiceScanner mainServiceScanner;

	/**
	 * 根据method调用相应的service中的方法执行
	 * 
	 * @param method
	 * @param baseRequestParam
	 * @return
	 * @throws RequestProcessInterruptException
	 * @throws Exception
	 */
	public BaseResultDto execute(BaseRequestParam baseRequestParam, HttpServletRequest request) throws Exception {

		ObjectAndMethod oam = mainServiceScanner.getServiceByMethodName(baseRequestParam.getMethod());

		if (null == oam) {
			throw new ParamMethodInvalidException();
		}

		if (oam.isNeedIp()) {
			baseRequestParam.setIp(IpUtil.getIpAddress(request));
		}

		BaseDto baseDto = baseRequestParam.convert();

		if (StringUtil.hasLength(baseRequestParam.getUserCode())) {
			baseDto.setUserId(BkBase64.base642long(baseRequestParam.getUserCode()));
		}
		if (StringUtil.hasLength(baseRequestParam.getDeviceCode())) {
			baseDto.setDeviceId(BkBase64.base642long(baseRequestParam.getDeviceCode()));
		}

		if (oam.isNeedLogin() && (null == baseDto.getUserId() || 0 >= baseDto.getUserId())) {
			throw new UserEmptyException();
		}

		BaseResultDto baseResultDto = (BaseResultDto) oam.getTargetMethod().invoke(oam.getBeanObject(), baseDto);

		// if (null != baseDtoResponse.getUserId()) {
		// baseResultDto.setUserCode(BkBase64.long2Base64(baseDtoResponse.getUserId()));
		// baseResultDto.setUserId(null);
		// }
		// if (null != baseDtoResponse.getDeviceId()) {
		// baseResultDto.setDeviceCode(BkBase64.long2Base64(baseDtoResponse.getDeviceId()));
		// baseResultDto.setDeviceId(null);
		// }

		return baseResultDto;
	}

}
