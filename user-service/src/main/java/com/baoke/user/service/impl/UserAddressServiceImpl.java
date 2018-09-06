package com.baoke.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.AddressListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.PhoneNumberUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.user.constant.UserAddressDefaultEnum;
import com.baoke.user.domain.AreaDictInfo;
import com.baoke.user.domain.UserAddress;
import com.baoke.user.manager.AreaDictManager;
import com.baoke.user.manager.UserAddressManager;
import com.baoke.user.service.UserAddressService;

@ServiceDefinition(value = "userAddressService")
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {

	private static final Logger logger = Logger.getLogger(UserAddressServiceImpl.class);

	@Resource
	private UserAddressManager userAddressManager;

	@Resource
	private AreaDictManager areaDictManager;

	@Override
	@MethodDefinition(value = "queryMyAddress", needLogin = true)
	public AddressListDto queryAddressByUserId(AddressInfoDto addressInfoDto) throws ParamInvalidException {
		if (addressInfoDto == null || addressInfoDto.getUserId() == null) {
			throw new ParamInvalidException("userId不能为空");
		}

		List<AddressInfoDto> addressInfoList = new ArrayList<AddressInfoDto>();
		List<UserAddress> userAddressesList = userAddressManager.queryUserAddressByUserId(addressInfoDto.getUserId(),
				BooleanEnum.TRUE);

		if (CollectionUtil.isNotEmpty(userAddressesList)) {
			for (UserAddress userAddress : userAddressesList) {
				AddressInfoDto addressInfoDtoResult = userAddress.convert();

				if (StringUtil.isNotEmpty(userAddress.getProvince())) {
					AreaDictInfo areaDictInfo = areaDictManager.queryAddressByCode(userAddress.getProvince(),
							BooleanEnum.TRUE);
					addressInfoDtoResult.setProvince(areaDictInfo != null ? areaDictInfo.getName() : "");
				}
				if (StringUtil.isNotEmpty(userAddress.getCity())) {
					AreaDictInfo areaDictInfo = areaDictManager.queryAddressByCode(userAddress.getCity(),
							BooleanEnum.TRUE);
					addressInfoDtoResult.setCity(areaDictInfo != null ? areaDictInfo.getName() : "");
				}
				if (StringUtil.isNotEmpty(userAddress.getArea())) {
					AreaDictInfo areaDictInfo = areaDictManager.queryAddressByCode(userAddress.getArea(),
							BooleanEnum.TRUE);
					addressInfoDtoResult.setArea(areaDictInfo != null ? areaDictInfo.getName() : "");
				}

				addressInfoList.add(addressInfoDtoResult);
			}
		}
		return new AddressListDto(true, "", addressInfoList);
	}

	@Override
	@MethodDefinition(value = "saveMyAddress", needLogin = true)
	public BaseResultDto saveMyAddress(AddressInfoDto addressInfoDto) throws ParamInvalidException {
		if (addressInfoDto == null || StringUtil.isEmpty(addressInfoDto.getProvince())
				|| StringUtil.isEmpty(addressInfoDto.getCity()) || StringUtil.isEmpty(addressInfoDto.getArea())
				|| StringUtil.isEmpty(addressInfoDto.getAddress()) || StringUtil.isEmpty(addressInfoDto.getPhone())
				|| StringUtil.isEmpty(addressInfoDto.getName())) {
			throw new ParamInvalidException("地址信息不完整");
		}

		if (!PhoneNumberUtil.check(addressInfoDto.getPhone())) {
			throw new ParamInvalidException("手机号码格式错误");
		}

		boolean result = false;
		if (addressInfoDto.getAddressId() != null && addressInfoDto.getAddressId() > 0) {
			result = modifyAddress(addressInfoDto);
		} else {
			result = addAddress(addressInfoDto);
		}

		if (result) {
			return new BaseResultDto(true, "保存成功");
		}
		return new BaseResultDto(false, "地址保存失败");
	}

	// 修改地址
	private boolean modifyAddress(AddressInfoDto addressInfoDto) throws ParamInvalidException {
		UserAddress userAddressTemp = userAddressManager.queryUserAddressById(addressInfoDto.getAddressId(),
				BooleanEnum.TRUE);
		if (userAddressTemp == null) {
			throw new ParamInvalidException("当前操作的地址已被删除");
		}

		// 去掉原来的默认值
		if (addressInfoDto.getDefaultFlag() != null && addressInfoDto.getDefaultFlag() > 0) {
			userAddressManager.modifyUserDefaultAddressByUserId(addressInfoDto.getUserId(), UserAddressDefaultEnum.NO);
		}

		UserAddress userAddress = new UserAddress(addressInfoDto);
		return userAddressManager.modifyUserAddressById(userAddress) > 0 ? true : false;
	}

	// 添加地址
	private boolean addAddress(AddressInfoDto addressInfoDto) throws ParamInvalidException {
		List<UserAddress> userAddressesList = userAddressManager.queryUserAddressByUserId(addressInfoDto.getUserId(),
				BooleanEnum.TRUE);
		// 每个用户最多添加10条地址
		if (CollectionUtil.isNotEmpty(userAddressesList) && userAddressesList.size() >= 10) {
			throw new ParamInvalidException("添加失败，收货地址上线为10条");
		}

		UserAddress userAddress = new UserAddress(addressInfoDto);
		if (CollectionUtil.isEmpty(userAddressesList)) {
			userAddress.setDefaultFlag(UserAddressDefaultEnum.YES.getCode());
		} else {
			// 去掉原来的默认值
			if (addressInfoDto.getDefaultFlag() != null && addressInfoDto.getDefaultFlag() > 0) {
				userAddressManager.modifyUserDefaultAddressByUserId(addressInfoDto.getUserId(),
						UserAddressDefaultEnum.NO);
			}
		}
		return userAddressManager.addUserAddress(userAddress) > 0 ? true : false;
	}

	@Override
	@MethodDefinition(value = "saveMyDefaultAddress", needLogin = true)
	public BaseResultDto saveMyDefaultAddress(AddressInfoDto addressInfoDto) throws ParamInvalidException {
		if (null == addressInfoDto || null == addressInfoDto.getUserId() || null == addressInfoDto.getAddressId()) {
			throw new ParamInvalidException("用户ID与地址ID均不能为空");
		}

		UserAddress userAddress = userAddressManager.queryUserAddressById(addressInfoDto.getAddressId(),
				BooleanEnum.TRUE);
		if (null == userAddress) {
			logger.warn("save default address error, userAddress is empty, addressId=" + addressInfoDto.getAddressId()
					+ ", userId=" + addressInfoDto.getUserId());
			return new BaseResultDto(false, "您要操作的地址信息不存在，请刷新重试");
		}

		if (!addressInfoDto.getUserId().equals(userAddress.getUserId())) {
			logger.error(
					"save default address error. addressinfo is not match to userinfo, addressId=" + userAddress.getId()
							+ ", addressUserId=" + userAddress.getUserId() + ", userId=" + addressInfoDto.getUserId());
			throw new ParamInvalidException("用户ID与地址ID不相匹配");
		}

		userAddressManager.modifyUserNewDefaultAddress(addressInfoDto.getUserId(), addressInfoDto.getAddressId());
		return new BaseResultDto(true, "操作成功");
	}

	@Override
	@MethodDefinition(value = "deleteMyAddress", needLogin = true)
	public BaseResultDto deleteAddressById(AddressInfoDto addressInfoDto) throws ParamInvalidException {
		if (null == addressInfoDto || null == addressInfoDto.getUserId() || null == addressInfoDto.getAddressId()) {
			throw new ParamInvalidException("用户ID与地址ID均不能为空");
		}

		UserAddress userAddress = userAddressManager.queryUserAddressById(addressInfoDto.getAddressId(),
				BooleanEnum.TRUE);
		if (null == userAddress) {
			logger.warn("delete address error,user address is empty, addressId=" + addressInfoDto.getAddressId()
					+ ", userId=" + addressInfoDto.getUserId());
			return new BaseResultDto(false, "您要操作的地址信息不存在，请刷新重试");
		}

		if (!addressInfoDto.getUserId().equals(userAddress.getUserId())) {
			logger.error("delete address error. addressinfo is not match to userinfo, addressId=" + userAddress.getId()
					+ ", addressUserId=" + userAddress.getUserId() + ", userId=" + addressInfoDto.getUserId());
			throw new ParamInvalidException("用户ID与地址ID不相匹配");
		}

		userAddressManager.modifyUserAddressById(new UserAddress(addressInfoDto.getAddressId(), BooleanEnum.FALSE));
		return new BaseResultDto(true, "操作成功");
	}

}
