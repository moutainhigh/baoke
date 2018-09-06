package com.baoke.common.exception;

import com.baoke.common.exception.base.MainException;

/**
 * 参数解析异常
 * 
 * @author wyh
 * @date: 2018年6月15日 下午6:58:42
 */
public class ParamParseException extends MainException {

	private static final long serialVersionUID = 1808422111219819372L;

	public ParamParseException() {
		super();
	}

	public ParamParseException(String responseMsg) {
		super(responseMsg);
	}

	public ParamParseException(Throwable cause) {
		super(cause);
	}

}
