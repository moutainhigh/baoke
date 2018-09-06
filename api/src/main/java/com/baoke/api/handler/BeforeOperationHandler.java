package com.baoke.api.handler;

import javax.servlet.http.HttpServletRequest;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.handler.chain.BeforeOperationHandlerChain;
import com.baoke.common.exception.base.MainException;

/**
 * 处理request的接口类
 * 
 */
public interface BeforeOperationHandler {
	/**
	 * 此接口将在执行具体的业务逻辑之前被调用
	 * 
	 * @param method
	 *            本次请求的任务类型
	 * @param baseRequestParam
	 *            解析后的请求参数基类 , 可根据请求类型 , 强转至目标类型
	 * @param request
	 * @param chain
	 *            由此控制是否需要继续处理
	 */
	public void handle(String method, BaseRequestParam baseRequestParam, HttpServletRequest request,
			BeforeOperationHandlerChain chain) throws MainException;
}