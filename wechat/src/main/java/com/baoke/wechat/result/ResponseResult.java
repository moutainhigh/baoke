package com.baoke.wechat.result;

import java.io.Serializable;

import com.baoke.wechat.constant.CodeEnum;

/**
 * code返回码 结果集
 * 
 * @author zjm
 * @date: 2018年6月25日 上午10:49:58
 */
public class ResponseResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 返回结果体 */
	private Object data;

	/** 返回结果描述 */
	private String message;

	/** 返回码 200：成功 */
	private int code = CodeEnum.ERROR.getCode();

	public ResponseResult(CodeEnum code, String message) {
		super();
		this.message = message;
		this.code = code.getCode();
	}

	public ResponseResult(CodeEnum code, String message, Object data) {
		super();
		this.data = data;
		this.message = message;
		this.code = code.getCode();
	}

	public ResponseResult() {

	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(CodeEnum code) {
		this.code = code.getCode();
	}

}
