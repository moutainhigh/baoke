package com.baoke.common.cloud.upload;

/**
 * 腾讯云相关配置
 * 
 * @author: wyj
 * @date: 2018年6月26日 下午3:07:13
 */
public class TencentCloudUploadConfig {

	/** 开发者访问 COS 服务时拥有的用户维度唯一资源标识，用以标识资源 */
	public static final String APPID = "1254128350";

	/** 开发者拥有的项目身份识别 ID，用以身份认证 */
	public static final String SECRET_ID = "AKIDgGftz4fhFcnneM7GUYUIwPr9GICwxAWg";

	/** 开发者拥有的项目身份密钥 */
	public static final String SECRET_KEY = "leNuFPNcnnbCbrvrdEdrtjNq8IH4f4JM";

	// /** bucketName 前缀名称仅支持小写字母、数字和 - 的组合，不能超过40字符 */
	// public static final String BUCKET_NAME = "{0}-" + APPID;
	//
	// /** bucketName 前缀名称仅支持小写字母、数字和 - 的组合，不能超过40字符 */
	// public static final String DEFAULT_BUCKET = "baoke-" + APPID;

	/**
	 * 默认地域为{上海（华东）:ap-shanghai}
	 * ,其他地域还有{北京一区（华北）:ap-beijing-1},{北京:ap-beijing},{上海（华东）:ap-shanghai},{成都（
	 * 西南）:ap-chengdu},{重庆:ap-chongqing},{香港:ap-hongkong},{广州（华南）:ap-guangzhou}
	 */
	public static final String DEFAULT_REGION = "ap-shanghai";

	/** 腾讯云COS访问url {BUCKET_NAME},{REGION},{KEY} 注意文件KEY需要urlEncode */
	public static final String URL = "%s.cos.%s.myqcloud.com/%s";

	/**
	 * 图片基础处理万象优图地址
	 */
	public static final String DEFAULT_REGION_URL = "http://%s.picsh.myqcloud.com%s";

	/**
	 * 腾讯云转码后视频地址(COS地址)
	 */
	public static final String TRANSFER_VIDEO_URL = "http://%s.cos.ap-shanghai.myqcloud.com%s.f30.mp4";

	/**
	 * 腾讯云转码后抽图地址(万象优图)
	 */
	public static final String TRANSFER_PICTRURE_URL = "http://%s.picsh.myqcloud.com%s.0_0.p0.jpg";

}
