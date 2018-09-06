package com.baoke.api.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import com.baoke.api.constant.ApiProcessStatus;
import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.base.MainException;

public interface ResponseParamService {

	/**
	 * baseDto convert
	 * 
	 * @author wyh
	 * @date 2018年6月10日 下午10:17:14
	 * 
	 * @param baseResultDto
	 * @return
	 */
	public BaseResponseParam convertToResponse(String method, BaseResultDto baseResultDto) throws MainException;

	/**
	 * 追加参数封装
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午7:38:38
	 * 
	 * @param baseResponseParam
	 * @param apiProcessStatus
	 * @param responseMessage
	 *            页面显示文本
	 * @return
	 */
	public BaseResponseParam appendParams(BaseResponseParam baseResponseParam, ApiProcessStatus apiProcessStatus,
			String responseMessage);

	/**
	 * 转换成JSON
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午7:53:58
	 * 
	 * @param baseResponseParam
	 * @return
	 */
	public String convertToJson(BaseResponseParam baseResponseParam);

	/**
	 * 对输出内容进行压缩 , 并输出至客户端
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午8:02:00
	 * 
	 * @param resultJson
	 * @param os
	 * @throws IOException
	 */
	public void gzipProcess(HttpServletRequest request, String resultJson, OutputStream os) throws IOException;

}
