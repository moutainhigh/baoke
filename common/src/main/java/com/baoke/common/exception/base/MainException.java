package com.baoke.common.exception.base;

/**
 * 系统定义的所有异常基类
 * 
 * @author wyh
 *
 */
public class MainException extends Exception {

	private static final long serialVersionUID = 1808422111219819372L;

	/**
	 * 错误状态码
	 */
	private Long errorCode;
	/**
	 * 错误描述
	 */
	private String errorMsg;
	/**
	 * 输出客户端的响应文本
	 */
	private String responseMsg;

	public MainException() {
		super();
	}

	public MainException(String responseMsg) {
		super(responseMsg);
		this.responseMsg = responseMsg;
	}

	public MainException(Throwable cause) {
		super(cause);
	}

	public MainException(Long errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public Long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

}
