package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.AddressDictListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AreaDictInfoDto;

public class QueryAddressDictResponse extends BaseResponseParam {
	private static final long serialVersionUID = 1L;

	private List<AreaDictInfoDto> addressList;// 地区集合

	public List<AreaDictInfoDto> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AreaDictInfoDto> addressList) {
		this.addressList = addressList;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		AddressDictListDto addressDictListDto = (AddressDictListDto) baseResultDto;
		this.addressList = addressDictListDto.getAddressList();
		return this;
	}

}
