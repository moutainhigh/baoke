package com.baoke.api.handler;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.api.handler.chain.AfterOperationHandlerChain;
import com.baoke.common.exception.base.MainException;

public interface AfterOperationHandler {
	/**
	 * 
	 * @param baseRequestParam
	 *            解析后的请求参数基类 , 可根据请求类型 , 强转至目标类型
	 * @param baseResponseParam
	 *            业务方法处理结果
	 * @param chain
	 */
	public void handle(BaseRequestParam baseRequestParam, BaseResponseParam baseResponseParam,
			AfterOperationHandlerChain chain) throws MainException;
}
