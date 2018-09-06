package com.baoke.common.exception;

import com.baoke.common.exception.base.MainException;

/**
 * 参数无效异常
 * 
 * @author wyh
 * @date: 2018年6月15日 下午6:58:06
 */
public class ParamInvalidException extends MainException {

	private static final long serialVersionUID = 1808422111219819372L;

	public ParamInvalidException() {
		super();
	}

	public ParamInvalidException(String responseMsg) {
		super(responseMsg);
	}

	public ParamInvalidException(Throwable cause) {
		super(cause);
	}

}
