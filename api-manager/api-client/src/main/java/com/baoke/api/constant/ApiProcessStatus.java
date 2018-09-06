package com.baoke.api.constant;

public enum ApiProcessStatus {
	PROCESS_SUCCESS(100, "处理成功"),

	NEED_LOGIN(200, "强制登录跳转"),
	// MAY_UPDATE_CLIENT("201","客户端可以更新到新版本"),
	// MUST_UPDATE_CLIENT("202","客户端必须更新到新版本"),

	PARAM_PARSE_ERROR(301, "参数解析错误"),

	PARAM_INVALID_ERROR(302, "参数值校验失败"),

	PARAM_INVALID_METHOD_ERROR(310, "业务方法校验失败"),

	// COOKIE_NOT_EXISTS("303","cookie不存在"),
	// USER_UN_LOGIN("304","未登录"),
	// MOBILE_ID_NOT_EXISTS("305", "mobileId不存在"),
	// USER_NOT_EXISTS("309","用户不存在"),
	// COOKIE_NOT_VALID("401","Cookie无效"),
	PROCESS_ERROR(360, "服务端业务错误"),

	UNKNOW_ERROR(400, "未知错误"),;

	private int code;
	private String name;

	public int getCode() {
		return code;
	}

	public static ApiProcessStatus getProcessStatusCodeByMessage(String name) {
		if (name == null || "".equals(name)) {
			return UNKNOW_ERROR;
		}
		for (ApiProcessStatus code : ApiProcessStatus.values()) {
			if (name.equalsIgnoreCase(code.getName())) {
				return code;
			}
		}
		return UNKNOW_ERROR;
	}

	public String getName() {
		return name;
	}

	ApiProcessStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static ApiProcessStatus getResultCodebyCodeId(long code) {
		for (ApiProcessStatus e : ApiProcessStatus.values()) {
			if (e.code == code)
				return e;
		}
		return null;
	}

}
