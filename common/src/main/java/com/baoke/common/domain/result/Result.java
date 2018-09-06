package com.baoke.common.domain.result;

import java.io.Serializable;

/**
 * 返回结果集 Date: 2018年5月17日 上午11:01:12
 * 
 * @author zjm
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 状态描述 */
	private String message = "";

	/** 是否成功 */
	private boolean success = true;

	/** 返回内容 */
	private Object body;

	public Result() {
	}

	public Result(boolean success, String message) {
		this.message = message;
		this.success = success;
		this.body = null;
	}

	public Result(boolean success, String message, Object body) {
		this.message = message;
		this.success = success;
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
