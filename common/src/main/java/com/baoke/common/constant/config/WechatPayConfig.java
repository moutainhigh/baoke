package com.baoke.common.constant.config;

/**
 * 微信配置
 * 
 * @author: wyj
 * @date: 2018年6月23日 下午3:59:33
 */
public class WechatPayConfig {

	/** 微信appid **/
	public static final String WX_APPID = "wx083af9f00fa6f4c0";

	/** 微信商户号 **/
	public static final String WX_MCHID = "1507178591";

	/** 商户的api密钥 **/
	public static final String PRIVATE_KEY = "BAOKEKEJI1024MA25T002QIX3528XO15";

	/** 异步通知地址 **/
	public static final String WX_NOTIFYURL = "http://extra.baokekeji.com/wechat/notify";

	/** 微信支付方式 **/
	public static final String WX_TRADETYPE = "APP";

	/** 微信统一下单 接口地址 **/
	public static final String WX_UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/** 微信查询订单 接口地址 **/
	public static final String WX_ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

}
