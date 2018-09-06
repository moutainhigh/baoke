package com.baoke.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class ServletUtil {

	/**
	 * 通过UA判断是否是来自无线的请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMobileRequestByUa(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");

		if (!StringUtils.isBlank(userAgent)
				&& (userAgent.toLowerCase().indexOf("iphone") > 0 || userAgent.toLowerCase().indexOf("android") > 0)) {
			return true;

		}

		return false;
	}

	/**
	 * 302跳转设置
	 * 
	 * @param response
	 * @param redirectURL
	 */
	public static void set302Redirect(HttpServletResponse response, String redirectURL) {
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		response.addHeader("Location", redirectURL);
	}
}
