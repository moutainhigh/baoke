package com.baoke.admin.configuration;

import java.io.Serializable;

public class WebsocketConfig implements Serializable{
	private static final long serialVersionUID = -1879714794042793848L;
	
	public static String httpPushAddress;

	public static String getHttpPushAddress() {
		return httpPushAddress;
	}

	public void setHttpPushAddress(String httpPushAddress) {
		WebsocketConfig.httpPushAddress = httpPushAddress;
	}
	
	
}
