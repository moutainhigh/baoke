package com.baoke.common.exception;

import com.baoke.common.exception.base.MainException;

/**
 * 参数方法无效
 * 
 * @author wyh
 * @date: 2018年6月15日 下午6:59:20
 */
public class ParamMethodInvalidException extends MainException {

	private static final long serialVersionUID = 1808422111219819372L;

	public ParamMethodInvalidException() {
		super();
	}

	public ParamMethodInvalidException(String responseMsg) {
		super(responseMsg);
	}

	public ParamMethodInvalidException(Throwable cause) {
		super(cause);
	}

}
