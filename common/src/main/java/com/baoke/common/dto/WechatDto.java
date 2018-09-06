package com.baoke.common.dto;

import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 微信登录
 * 
 * @author zjm
 * @Date: 2018年6月12日 下午2:08:42
 */
public class WechatDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private String code;// 微信code

	/** app 10; 微信公众号 20; 小程序 30 */
	private WechatUserSourceTypeEnum wechatSourceTypeEnum;

	public WechatUserSourceTypeEnum getWechatSourceTypeEnum() {
		return wechatSourceTypeEnum;
	}

	public void setWechatSourceTypeEnum(WechatUserSourceTypeEnum wechatSourceTypeEnum) {
		this.wechatSourceTypeEnum = wechatSourceTypeEnum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
