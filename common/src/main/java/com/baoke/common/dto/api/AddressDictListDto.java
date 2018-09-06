package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AreaDictInfoDto;

public class AddressDictListDto extends BaseResultDto {
	private static final long serialVersionUID = 1L;

	private List<AreaDictInfoDto> addressList;// 地区集合

	public AddressDictListDto() {
	}

	public AddressDictListDto(boolean success, String message) {
		super(success, message);
	}

	public List<AreaDictInfoDto> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AreaDictInfoDto> addressList) {
		this.addressList = addressList;
	}

}
