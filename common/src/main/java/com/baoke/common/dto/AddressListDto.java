package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AddressInfoDto;

/**
 * 地址列表
 * 
 * @author zdy
 * @date: 2018年6月13日 下午6:07:48
 */
public class AddressListDto extends BaseResultDto {

	private static final long serialVersionUID = 1407479669143306930L;

	private List<AddressInfoDto> addressList;// 地址集合

	public AddressListDto() {
	}

	public AddressListDto(boolean success, String message, List<AddressInfoDto> addressList) {
		super(success, message);
		this.addressList = addressList;
	}

	public List<AddressInfoDto> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressInfoDto> addressList) {
		this.addressList = addressList;
	}

}
