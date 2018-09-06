package com.baoke.api.handler.chain;

import java.util.List;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.api.handler.AfterOperationHandler;
import com.baoke.common.exception.base.MainException;

/**
 * 注入在此chain中的handler , 将在业务方法调用之后被调用
 *
 */
public class AfterOperationHandlerChain {
	private AfterOperationHandler[] handlers = null;

	private ThreadLocal<Integer> currentPoint = new ThreadLocal<Integer>();

	public void setHandlers(List<AfterOperationHandler> handlers) {
		this.handlers = handlers.toArray(new AfterOperationHandler[handlers.size()]);
	}

	public void doHandle(BaseRequestParam baseRequestParam, BaseResponseParam baseResponseParam) throws MainException {
		if (handlers != null && handlers.length > currentPoint.get()) {
			int point = currentPoint.get();
			currentPoint.set(point + 1);
			handlers[point].handle(baseRequestParam, baseResponseParam, this);
		}
	}

	public void reset() {
		currentPoint.set(0);
	}

}
