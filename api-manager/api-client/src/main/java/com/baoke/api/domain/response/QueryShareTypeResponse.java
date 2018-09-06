package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.ChannelShareDto;
import com.baoke.common.dto.base.BaseResultDto;

public class QueryShareTypeResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private String shareType;// 分享渠道类型，多个用逗号分隔

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (null == baseResultDto) {
			return this;
		}
		ChannelShareDto channelShareDto = (ChannelShareDto) baseResultDto;
		this.shareType = channelShareDto.getShareType();
		return this;
	}

}
