package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.user.constant.UserAddressDefaultEnum;
import com.baoke.user.constant.UserStatusEnum;

/**
 * 收货地址实体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:31:44
 */
public class UserAddress extends BaseDomain {

	private static final long serialVersionUID = 3358735891092995064L;

	// 地址id
	private Long id;

	// 用户id
	private Long userId;

	// 收货人姓名
	private String name;

	// 电话
	private String phone;

	// 省code
	private String province;

	// 市code
	private String city;

	// 区code
	private String area;

	// 详细地址
	private String address;

	// 是否默认
	private Integer defaultFlag;

	// 状态 0：不可用；1可用
	private Integer status;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public UserAddress() {
		super();
	}

	public UserAddress(Long id, UserAddressDefaultEnum userAddressDefaultEnum) {
		super();
		this.id = id;
		if (null != userAddressDefaultEnum) {
			this.defaultFlag = userAddressDefaultEnum.getCode();
		}
	}

	public UserAddress(Long id, BooleanEnum status) {
		super();
		this.id = id;
		if (null != status) {
			this.status = status.getCode();
		}
	}

	public UserAddress(AddressInfoDto addressInfoDto) {
		if (null != addressInfoDto.getAddressId() && addressInfoDto.getAddressId() > 0) {
			this.id = addressInfoDto.getAddressId();
		}
		this.address = addressInfoDto.getAddress();
		this.area = addressInfoDto.getArea();
		this.city = addressInfoDto.getCity();
		this.defaultFlag = addressInfoDto.getDefaultFlag() == null ? 0 : addressInfoDto.getDefaultFlag();
		this.name = addressInfoDto.getName();
		this.phone = addressInfoDto.getPhone();
		this.province = addressInfoDto.getProvince();
		this.userId = addressInfoDto.getUserId();
		this.status = UserStatusEnum.NORMAL.getCode();
	}

	public AddressInfoDto convert() {
		AddressInfoDto addressInfoDto = new AddressInfoDto();
		addressInfoDto.setAddressId(this.id);
		addressInfoDto.setAddress(this.address);
		addressInfoDto.setAreaCode(this.area);
		addressInfoDto.setCityCode(this.city);
		addressInfoDto.setDefaultFlag(this.defaultFlag);
		addressInfoDto.setName(this.name);
		addressInfoDto.setPhone(this.phone);
		addressInfoDto.setProvinceCode(this.province);
		addressInfoDto.setUserId(this.userId);
		return addressInfoDto;
	}

	public AddressInfoDto convert(AreaDictInfo provinceInfo, AreaDictInfo cityInfo, AreaDictInfo areaInfo) {
		AddressInfoDto addressInfoDto = new AddressInfoDto();
		addressInfoDto.setAddressId(this.id);
		addressInfoDto.setAddress(this.address);
		addressInfoDto.setAreaCode(this.area);
		addressInfoDto.setCityCode(this.city);
		addressInfoDto.setDefaultFlag(this.defaultFlag);
		addressInfoDto.setName(this.name);
		addressInfoDto.setPhone(this.phone);
		addressInfoDto.setProvinceCode(this.province);
		addressInfoDto.setUserId(this.userId);
		addressInfoDto.setProvince(provinceInfo.getName());
		addressInfoDto.setCity(cityInfo.getName());
		addressInfoDto.setArea(areaInfo.getName());
		return addressInfoDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
