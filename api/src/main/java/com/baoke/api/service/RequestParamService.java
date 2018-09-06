package com.baoke.api.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.ParamParseException;
import com.baoke.common.exception.base.MainException;

public interface RequestParamService {

	/**
	 * 参数解析
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午5:23:30
	 * 
	 * @param request
	 * @return
	 * @throws MainException
	 */
	public Map<String, String> parseParams(HttpServletRequest request) throws ParamParseException;

	/**
	 * 合法性校验
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午6:30:21
	 * 
	 * @param requestData
	 * @param sign
	 * @throws ParamInvalidException
	 */
	public void checkParams(String requestData, String sign) throws ParamInvalidException;

	/**
	 * 转为RequestObject
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午6:31:27
	 * 
	 * @param requestData
	 * @return
	 * @throws ParamInvalidException
	 */
	public BaseRequestParam convertToRequest(String requestData) throws ParamInvalidException;

}
