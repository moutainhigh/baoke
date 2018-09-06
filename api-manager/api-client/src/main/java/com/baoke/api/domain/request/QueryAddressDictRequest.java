package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.AreaDictInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询地区字典列表请求
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:47:01
 */
public class QueryAddressDictRequest extends BaseRequestParam {

	private static final long serialVersionUID = -3591935581336825905L;

	// 上级机构
	private String parentCode;

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Override
	public BaseDto convert() throws MainException {
		AreaDictInfoDto areaDictInfoDto = new AreaDictInfoDto();
		areaDictInfoDto.setParentCode(parentCode);
		return areaDictInfoDto;
	}

}
