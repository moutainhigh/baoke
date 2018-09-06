package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.dto.WechatDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 手机号绑定微信
 * 
 * @author zjm
 * @date: 2018年7月5日 上午10:49:47
 */
public class BindWechatRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private String wechatCode;

	private WechatUserSourceTypeEnum wechatSourceTypeEnum = WechatUserSourceTypeEnum.MOBILE_APP;

	public WechatUserSourceTypeEnum getWechatSourceTypeEnum() {
		return wechatSourceTypeEnum;
	}

	public void setWechatSourceTypeEnum(WechatUserSourceTypeEnum wechatSourceTypeEnum) {
		this.wechatSourceTypeEnum = wechatSourceTypeEnum;
	}

	public String getWechatCode() {
		return wechatCode;
	}

	public void setWechatCode(String wechatCode) {
		this.wechatCode = wechatCode;
	}

	@Override
	public BaseDto convert() throws MainException {
		WechatDto wechatDto = new WechatDto();
		wechatDto.setCode(this.wechatCode);
		wechatDto.setWechatSourceTypeEnum(this.wechatSourceTypeEnum);

		return wechatDto;
	}

}
