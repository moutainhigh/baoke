package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;

public class UploadUserHeadImgResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private String headImgUrl;// 用户头像路径

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {
		if (baseResultDto == null) {
			return this;
		}
		UserInfoDto userInfoDto = (UserInfoDto) baseResultDto;
		this.headImgUrl = userInfoDto.getHeadImgUrl();
		return this;
	}

}
