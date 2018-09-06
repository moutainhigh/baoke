package com.baoke.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baoke.api.constant.ApiProcessStatus;
import com.baoke.api.core.MainServiceExecutor;
import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.api.handler.chain.AfterOperationHandlerChain;
import com.baoke.api.handler.chain.BeforeOperationHandlerChain;
import com.baoke.api.service.RequestParamService;
import com.baoke.api.service.ResponseParamService;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.ParamMethodInvalidException;
import com.baoke.common.exception.ParamParseException;
import com.baoke.common.exception.UserEmptyException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.StringUtil;

/**
 * 主服务入口
 */
@Controller
@RequestMapping("/service")
public class MainController {
	private static Log log = LogFactory.getLog(MainController.class);
	@Autowired
	private BeforeOperationHandlerChain beforeHandlerChain;
	@Autowired
	private AfterOperationHandlerChain afterHandlerChain;

	@Autowired
	private MainServiceExecutor mainServiceExecutor;

	@Autowired
	private RequestParamService requestParamService;

	@Autowired
	private ResponseParamService responseParamService;

	/**
	 * 请求主方法
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午6:04:47
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "app.htm")
	public void process(HttpServletRequest request, HttpServletResponse response) {
		String requestData = null;
		String sign = null;
		String method = null;
		BaseRequestParam baseRequestParam = null;
		BaseResponseParam baseResponseParam = null;

		try {
			Map<String, String> params = requestParamService.parseParams(request);
			requestData = params.get("requestData");
			sign = params.get("sign");

			if (log.isDebugEnabled()) {
				log.debug(String.format("api process start, requestData=[%s], sign=[%s]", requestData, sign));
			}

			requestParamService.checkParams(requestData, sign);

			baseRequestParam = requestParamService.convertToRequest(requestData);

			method = baseRequestParam.getMethod();

			if (StringUtil.isBlank(method)) {
				throw new ParamMethodInvalidException("参数方法无效！");
			}

			// 前置handlers处理
			beforeHandlerChain.reset();
			beforeHandlerChain.doHandle(method, baseRequestParam, request);

			long start = System.currentTimeMillis();
			BaseResultDto baseResultDto = mainServiceExecutor.execute(baseRequestParam, request);
			long end = System.currentTimeMillis();

			if (end - start > 400) {
				log.warn("[API_SLOW_SERVICE], method=" + method + ", time=" + (end - start) + "ms");
			}

			baseResponseParam = responseParamService.convertToResponse(method, baseResultDto);

			baseResponseParam = responseParamService.appendParams(baseResponseParam, ApiProcessStatus.PROCESS_SUCCESS,
					"");

		} catch (ParamInvalidException pie) {
			baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.PARAM_INVALID_ERROR, pie);
		} catch (ParamParseException ppe) {
			baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.PARAM_PARSE_ERROR, ppe);
		} catch (ParamMethodInvalidException pmie) {
			baseResponseParam = appendErrorParams(method, requestData, sign,
					ApiProcessStatus.PARAM_INVALID_METHOD_ERROR, pmie);
		} catch (UserEmptyException uee) {
			baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.NEED_LOGIN, uee);
		} catch (Exception e) {
			if (e instanceof InvocationTargetException) {
				Throwable t = ((InvocationTargetException) e).getTargetException();

				if (t instanceof ParamParseException) {
					baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.PARAM_PARSE_ERROR,
							((ParamParseException) t));
				} else if (t instanceof ParamInvalidException) {
					baseResponseParam = appendErrorParams(method, requestData, sign,
							ApiProcessStatus.PARAM_INVALID_ERROR, ((ParamInvalidException) t));
				} else if (t instanceof ParamMethodInvalidException) {
					baseResponseParam = appendErrorParams(method, requestData, sign,
							ApiProcessStatus.PARAM_INVALID_METHOD_ERROR, ((ParamMethodInvalidException) t));
				} else if (t instanceof UserEmptyException) {
					baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.NEED_LOGIN,
							((UserEmptyException) t));
				} else if (t instanceof MainException) {
					baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.PROCESS_ERROR,
							((MainException) t));
				} else {
					baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.UNKNOW_ERROR, e,
							"");
				}
			} else {
				baseResponseParam = appendErrorParams(method, requestData, sign, ApiProcessStatus.UNKNOW_ERROR, e, "");
			}

		}

		try {
			if (baseRequestParam != null) {
				afterHandlerChain.reset();
				afterHandlerChain.doHandle(baseRequestParam, baseResponseParam);
			}

			ServletOutputStream os = null;
			try {
				if (baseResponseParam != null) {

					String responseData = responseParamService.convertToJson(baseResponseParam);
					response.setHeader("Content-type", "application/json;charset=UTF-8");
					os = response.getOutputStream();

					if (log.isDebugEnabled()) {
						log.debug(String.format("api process end, requestData=[%s], responseData=[%s]", requestData,
								responseData));
					}
					responseParamService.gzipProcess(request, responseData, os);
				}
			} finally {
				if (os != null) {
					os.close();
				}
			}
		} catch (Exception e) {
			log.error("api process error:", e);
		}
	}

	/**
	 * 追加错误消息
	 * 
	 * @author wyh
	 * @date 2018年7月2日 下午7:36:41
	 * 
	 * @param method
	 * @param requestData
	 * @param sign
	 * @param apiProcessStatus
	 * @param e
	 * @param baseResponseParam
	 * @return
	 */
	private BaseResponseParam appendErrorParams(String method, String requestData, String sign,
			ApiProcessStatus apiProcessStatus, MainException e) {
		log.error("api process error" + ", method=" + method + ", errorCode=" + e.getErrorCode() + ", errorMsg="
				+ e.getErrorMsg() + ", responseMsg=" + e.getResponseMsg() + ", message=" + e.getMessage()
				+ ", requestData=" + requestData + ", sign=" + sign, e);
		BaseResponseParam baseResponseParam = new BaseResponseParam() {
			private static final long serialVersionUID = -2701860308439975398L;

			@Override
			public BaseResponseParam convert(BaseResultDto baseResultDto) {
				return this;
			}
		};
		baseResponseParam.setResultSuccess(false);
		baseResponseParam = responseParamService.appendParams(baseResponseParam, apiProcessStatus, e.getResponseMsg());
		return baseResponseParam;
	}

	/**
	 * 追加错误消息
	 * 
	 * @author wyh
	 * @date 2018年7月2日 下午7:48:08
	 * 
	 * @param method
	 * @param requestData
	 * @param sign
	 * @param apiProcessStatus
	 * @param e
	 * @param baseResponseParam
	 * @return
	 */
	private BaseResponseParam appendErrorParams(String method, String requestData, String sign,
			ApiProcessStatus apiProcessStatus, Exception e, String responseMsg) {
		log.error("api process error" + ", method=" + method + ", responseMsg=" + responseMsg + ", message="
				+ e.getMessage() + ", requestData=" + requestData + ", sign=" + sign, e);
		BaseResponseParam baseResponseParam = new BaseResponseParam() {
			private static final long serialVersionUID = -2701860308439975398L;

			@Override
			public BaseResponseParam convert(BaseResultDto baseResultDto) {
				return this;
			}
		};
		baseResponseParam.setResultSuccess(false);
		baseResponseParam = responseParamService.appendParams(baseResponseParam, apiProcessStatus, responseMsg);
		return baseResponseParam;
	}

}