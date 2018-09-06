package com.baoke.common.dto;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 分享的渠道
 * 
 * @author zdy
 * @date: 2018年7月9日 下午9:20:45
 */
public class ChannelShareDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private String shareType;// 分享渠道类型，多个用逗号分隔

	public ChannelShareDto() {
		super();
	}

	public ChannelShareDto(String shareType, boolean success, String message) {
		super(success, message);
		this.shareType = shareType;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

}
