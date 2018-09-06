package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 设置默认地址请求体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:51:25
 */
public class SaveMyDefaultAddressRequest extends BaseRequestParam {

	private static final long serialVersionUID = -2489662243795885137L;

	private long addressId;// 地址ID

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	@Override
	public BaseDto convert() throws MainException {
		AddressInfoDto addressInfoDto = new AddressInfoDto();
		addressInfoDto.setAddressId(addressId);
		return addressInfoDto;
	}

}
