package com.baoke.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.PhoneNumberUtil;
import com.baoke.common.util.RandomNumUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.user.constant.UserBannedEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.constant.UserTypeEnum;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.domain.UserPhoneCode;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.manager.UserPhoneCodeManager;
import com.baoke.user.service.DeviceUserRelationService;
import com.baoke.user.service.UserInfoService;
import com.baoke.user.service.UserLoginSmsService;

/**
 * 用户登录
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午1:57:44
 */
@ServiceDefinition(value = "userLoginSmsService")
@Service("userLoginSmsService")
public class UserLoginSmsServiceImpl implements UserLoginSmsService {

	private static final Logger logger = Logger.getLogger(UserLoginSmsServiceImpl.class);

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserPhoneCodeManager userPhoneCodeManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private DeviceUserRelationService deviceUserRelationService;

	@Override
	@MethodDefinition(value = "loginSms")
	public LoginResultDto loginSms(SmsDto smsDto) throws MainException {

		if (StringUtil.isEmpty(smsDto.getPhone())) {
			throw new ParamInvalidException("手机号不能为空");
		}
		if (!PhoneNumberUtil.check(smsDto.getPhone())) {
			logger.error("smslogin error.user phone format false, phone=" + smsDto.getPhone());
			throw new ParamInvalidException("手机号不正确");
		}
		if (StringUtil.isEmpty(smsDto.getCode())) {
			throw new ParamInvalidException("验证码不能为空");
		}

		UserPhoneCode userPhoneCode = userPhoneCodeManager.queryUserPhoneCode(
				new UserPhoneCode(smsDto.getPhone(), null, null, smsDto.getUserPhoneCodeTypeEnum()));

		if (null == userPhoneCode || new Date().getTime() > userPhoneCode.getDeadlineTime().getTime()
				|| !StringUtil.equals(userPhoneCode.getSmsCode(), smsDto.getCode())) {
			logger.warn("sms login error. smscode error. phone=" + smsDto.getPhone() + ", smscode=" + smsDto.getCode());
			return new LoginResultDto(false, "验证码错误或已失效", null);
		}

		List<UserInfo> userInfoList = userInfoManager.queryUserInfoByPhone(smsDto.getPhone());
		UserInfo userInfo = null;
		if (CollectionUtil.isEmpty(userInfoList)) {

			userInfo = new UserInfo(null, smsDto.getPhone(), null, UserTypeEnum.USER, UserStatusEnum.NORMAL,
					UserBannedEnum.NO);

			String nickName = getDefaultNickName();
			if (StringUtil.isNotEmpty(nickName)) {
				userInfo.setNickName(nickName);
			} else {
				return new LoginResultDto(false, "生成默认昵称失败，请稍后重试", null);
			}
			userInfo.setHeadImgUrl(CommonConfig.DEFAULT_USER_HEAD_URL);
			userInfoManager.addUserInfo(userInfo);
			logger.info("sms login, add user success," + userInfo);
		} else {
			userInfo = userInfoList.get(0);
			for (UserInfo userInfoTemp : userInfoList) {
				if (StringUtil.hasLength(userInfoTemp.getUnionId()) && StringUtil.hasLength(userInfoTemp.getPhone())) {
					userInfo = userInfoTemp;
				}
			}
		}

		if (UserStatusEnum.NORMAL.getCode() != userInfo.getStatus()) {
			logger.error("sms login error, user status=" + userInfo.getStatus() + ",  " + userInfo);
			return new LoginResultDto(userInfo.getId(), smsDto.getDeviceId(), false, "账户已被封禁", null);
		}

		if (UserPhoneCodeTypeEnum.APP_LOGIN == smsDto.getUserPhoneCodeTypeEnum()) {
			deviceUserRelationService.addDeviceUserRelation(userInfo.getId(), smsDto.getDeviceId());
		}

		BaseDto baseDto = new BaseDto(userInfo.getId(), smsDto.getDeviceId());
		UserInfoDto userInfoDto = userInfoService.queryUserInfoById(baseDto);

		return new LoginResultDto(userInfoDto.getUserId(), smsDto.getDeviceId(), true, "", userInfoDto);
	}

	private String getDefaultNickName() {
		String nickName = "";
		boolean result = false;
		while (!result) {
			nickName = CommonConfig.DEFAULT_USER_NICK_PREX + RandomNumUtil.getRandomString(6);
			int countNum = userInfoManager.countUserInfoByNickName(nickName, UserStatusEnum.NORMAL);
			if (countNum <= 0) {
				result = true;
			}
		}
		return nickName;
	}

}
