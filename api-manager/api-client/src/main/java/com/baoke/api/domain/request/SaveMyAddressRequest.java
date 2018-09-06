package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 保存我的收获地址请求体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:51:06
 */
public class SaveMyAddressRequest extends BaseRequestParam {

	private static final long serialVersionUID = 4153073187172402758L;

	private long addressId;

	// 姓名
	private String name;

	// 电话
	private String phone;

	// 省
	private String province;

	// 市
	private String city;

	// 区
	private String area;

	// 详细地址
	private String address;

	private Integer defaultFlag;// 默认地址标记

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	@Override
	public BaseDto convert() throws MainException {
		AddressInfoDto addressInfoDto = new AddressInfoDto();
		addressInfoDto.setAddressId(addressId);
		addressInfoDto.setAddress(address);
		addressInfoDto.setArea(area);
		addressInfoDto.setCity(city);
		addressInfoDto.setName(name);
		addressInfoDto.setPhone(phone);
		addressInfoDto.setProvince(province);
		addressInfoDto.setDefaultFlag(defaultFlag);
		return addressInfoDto;
	}

}
