package com.baoke.common.constant.config;

/**
 * 微信app登录配置信息
 * 
 * @author zjm
 * @date: 2018年7月5日 下午3:44:31
 */
public class WechatMobileAppLoginConfig {

	/** 微信appid **/
	public static final String APPID = "wx083af9f00fa6f4c0";

	/** 微信api密钥 **/
	public static final String SECRET = "88a57bc348ada13c489ae8305b430543";

	/** 微信国家地区语言版本 */
	public static final String WECHAT_LANG = "zh_CN";//

	/** 编码格式 */
	public static final String CHARSET_NAME = "UTF-8";//

	/** 获取微信token请求地址 */
	public static final String WECHAT_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type=authorization_code";

	/** 获取微信用户信息请求地址 */
	public static final String WECHAT_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openid}";

}
