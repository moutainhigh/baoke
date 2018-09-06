package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 删除我的收获地址请求体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:45:46
 */
public class DeleteMyAddressRequest extends BaseRequestParam {

	private static final long serialVersionUID = -3881020153128923048L;

	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Override
	public BaseDto convert() throws MainException {
		AddressInfoDto addressInfoDto = new AddressInfoDto();
		addressInfoDto.setAddressId(addressId);
		return addressInfoDto;
	}

}
