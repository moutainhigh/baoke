package com.baoke.api.service.impl;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.baoke.api.constant.ApiProcessStatus;
import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.api.service.ResponseParamService;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.StringUtil;
import com.baoke.common.util.api.GZip;
import com.baoke.common.util.api.json.JsonApiUtil;

public class ResponseParamServiceImpl implements ResponseParamService {

	public static final String RESPONSE_DOMAIN = "com.baoke.api.domain.response.";

	@Override
	public BaseResponseParam convertToResponse(String method, BaseResultDto baseResultDto) throws MainException {

		String className = RESPONSE_DOMAIN + method.substring(0, 1).toUpperCase() + method.substring(1) + "Response";

		try {
			Class<?> clazz = Class.forName(className);
			BaseResponseParam baseResponseParam = (BaseResponseParam) clazz.newInstance();
			baseResponseParam.convert(baseResultDto);

			if (baseResultDto != null) {
				baseResponseParam.setResultSuccess(baseResultDto.isSuccess());
				baseResponseParam.setResultMessage(baseResultDto.getMessage());
			}
			return baseResponseParam;
		} catch (Exception e) {
			throw new MainException(e);
		}
	}

	@Override
	public BaseResponseParam appendParams(BaseResponseParam baseResponseParam, ApiProcessStatus apiProcessStatus,
			String responseMessage) {

		if (null == baseResponseParam) {
			baseResponseParam = new BaseResponseParam() {
				private static final long serialVersionUID = -2701860308439975398L;

				@Override
				public BaseResponseParam convert(BaseResultDto baseResultDto) {
					return this;
				}
			};
		}
		baseResponseParam.setApiStatus(apiProcessStatus);
		if (StringUtil.isNotBlank(responseMessage)) {
			baseResponseParam.setResultMessage(responseMessage);
		}
		return baseResponseParam;
	}

	@Override
	public String convertToJson(BaseResponseParam baseResponseParam) {

		return JsonApiUtil.convertToJson(baseResponseParam);
	}

	@Override
	public void gzipProcess(HttpServletRequest request, String resultJson, OutputStream os) throws IOException {

		String encrypt = request.getHeader("Encrypt");
		boolean encoded = StringUtils.isNotBlank(encrypt) && "yes".equalsIgnoreCase(encrypt);
		byte[] bytes = encoded ? GZip.compressToByte(resultJson, "UTF-8") : resultJson.getBytes("UTF-8");

		// GZIPOutputStream gos = null;
		// try{
		// gos = new GZIPOutputStream(os);
		// gos.write(bytes);
		// gos.finish();
		// gos.flush();
		// }finally{
		// if(gos != null){
		// gos.close();
		// }
		// }

		try {
			os.write(bytes);
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}

	}

}
