package com.baoke.api.handler.chain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.handler.BeforeOperationHandler;
import com.baoke.common.exception.base.MainException;

/**
 * 注入在此chain中的handler , 将在业务方法调用之前被调用
 *
 */
public class BeforeOperationHandlerChain {

	private BeforeOperationHandler[] handlers = null;

	private ThreadLocal<Integer> currentPoint = new ThreadLocal<Integer>();

	public void setHandlers(List<BeforeOperationHandler> handlers) {
		this.handlers = handlers.toArray(new BeforeOperationHandler[handlers.size()]);
	}

	public void doHandle(String method, BaseRequestParam baseRequestParam, HttpServletRequest request)
			throws MainException {
		if (handlers != null && handlers.length > currentPoint.get()) {
			int point = currentPoint.get();
			currentPoint.set(point + 1);
			handlers[point].handle(method, baseRequestParam, request, this);
		}
	}

	public void reset() {
		currentPoint.set(0);
	}
}
