package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.AddressListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AddressInfoDto;

/**
 * 查询我的收货地址列表请求体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:54:19
 */
public class QueryMyAddressResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1754659033952500715L;

	private List<AddressInfoDto> addressList;

	public List<AddressInfoDto> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressInfoDto> addressList) {
		this.addressList = addressList;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		AddressListDto addressInfoListDto = (AddressListDto) baseResultDto;
		this.addressList = addressInfoListDto.getAddressList();
		return this;
	}

}
