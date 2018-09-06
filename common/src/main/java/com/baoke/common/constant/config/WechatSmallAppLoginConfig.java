package com.baoke.common.constant.config;

/**
 * 微信小程序登录配置信息
 * 
 * @author zjm
 * @date: 2018年7月5日 下午4:03:51
 */
public class WechatSmallAppLoginConfig {

	public static String APPID = "wxe96c22667461c917";

	public static String SECRET = "fd125ae9b63b1518fd3be8673bca181b";

	public static String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={secret}"
			+ "&js_code={js_code}&grant_type=authorization_code";

}
