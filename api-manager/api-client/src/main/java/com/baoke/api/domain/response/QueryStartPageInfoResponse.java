package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.BannerConfigDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 查询启动页配置信息
 * 
 * @author zdy
 * @date: 2018年7月10日 下午3:40:12
 */
public class QueryStartPageInfoResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	// banner
	private BannerConfigDto bannerInfo;

	public BannerConfigDto getBannerInfo() {
		return bannerInfo;
	}

	public void setBannerInfo(BannerConfigDto bannerInfo) {
		this.bannerInfo = bannerInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {
		if (baseResultDto == null) {
			return this;
		}
		bannerInfo = (BannerConfigDto) baseResultDto;
		return this;
	}

}
