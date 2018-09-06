package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 地址信息
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午9:50:02
 */
public class AddressInfoDto extends BaseResultDto {

	private static final long serialVersionUID = -7689505407625768384L;

	// 地址id
	private Long addressId;

	// 姓名
	private String name;

	// 电话
	private String phone;

	// 省Code
	private String provinceCode;

	// 市Code
	private String cityCode;

	// 区Code
	private String areaCode;

	// 省
	private String province;

	// 市
	private String city;

	// 区
	private String area;

	// 详细地址
	private String address;

	// 是否默认
	private Integer defaultFlag;

	public AddressInfoDto() {
		super();
	}

	public AddressInfoDto(Long addressId, String name, String phone, String address) {
		super();
		this.addressId = addressId;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

}
