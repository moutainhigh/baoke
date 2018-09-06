package com.baoke.common.dto;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.PostageInfoDto;

/**
 * 物流信息
 * 
 * @author wyh
 * @date 2018年6月30日 下午4:57:25
 *
 */
public class PostageDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private PostageInfoDto postageInfo;

	public PostageInfoDto getPostageInfo() {
		return postageInfo;
	}

	public void setPostageInfo(PostageInfoDto postageInfo) {
		this.postageInfo = postageInfo;
	}

}
