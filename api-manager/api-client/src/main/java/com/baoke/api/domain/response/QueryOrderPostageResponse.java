package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.PostageInfoDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 查看物流
 * 
 * @author zdy
 * @date: 2018年7月11日 上午11:01:50
 */
public class QueryOrderPostageResponse extends BaseResponseParam {
	private static final long serialVersionUID = 1L;

	private PostageInfoDto postageInfo;// 物流信息

	public PostageInfoDto getPostageInfo() {
		return postageInfo;
	}

	public void setPostageInfo(PostageInfoDto postageInfo) {
		this.postageInfo = postageInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {
		if (null == baseResultDto) {
			return this;
		}
		postageInfo = (PostageInfoDto) baseResultDto;
		return this;
	}
}
