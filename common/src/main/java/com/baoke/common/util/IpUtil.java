package com.baoke.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * IP 工具类
 *
 * @author zjj
 * @date 2018年7月2日 下午1:25:47
 */
public class IpUtil {

	/**
	 * 获取Nginx代理之后的IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		try {
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (ip.length() > 32) {
				ip = ip.substring(0, 31);
			}
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
			return ip;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 检查Ip合法
	 * 
	 * @author zjj
	 * @date 2018年7月1日 下午11:04:16
	 * @param input
	 * @return
	 */
	public static boolean checkIp(String input) {
		if (StringUtil.isBlank(input)) {
			return false;
		}
		return StringUtil.match(input.trim(),
				"^((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))$");
	}

	public static void main(String[] args) {
		String ip = "192.168.1.11";
		boolean checkIp = checkIp(ip);
		System.out.println(checkIp);
	}
}
