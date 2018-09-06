package com.baoke.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.WechatDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.PhoneNumberUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.domain.UserPhoneCode;
import com.baoke.user.domain.UserWechatInfo;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.manager.UserPhoneCodeManager;
import com.baoke.user.manager.UserWechatInfoManager;
import com.baoke.user.service.UserBindService;
import com.baoke.user.service.UserInfoService;
import com.baoke.user.service.UserLoginWechatService;
import com.baoke.user.service.UserWechatInfoService;

@ServiceDefinition(value = "userBindService")
@Service("userBindService")
public class UserBindServiceImpl implements UserBindService {

	private static final Logger logger = Logger.getLogger(UserBindServiceImpl.class);

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserLoginWechatService userLoginWechatService;

	@Resource
	private UserWechatInfoService userWechatInfoService;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private UserWechatInfoManager userWechatInfoManager;

	@Resource
	private UserPhoneCodeManager userPhoneCodeManager;

	@Override
	@MethodDefinition(value = "bindPhone", needLogin = true)
	public UserInfoDto bindPhone(SmsDto smsDto) throws MainException {
		if (null == smsDto || null == smsDto.getUserId()) {
			throw new ParamInvalidException("userId不能为空");
		}
		if (StringUtil.isEmpty(smsDto.getPhone())) {
			throw new ParamInvalidException("手机号不能为空");
		}
		if (!PhoneNumberUtil.check(smsDto.getPhone())) {
			logger.error("wechat bind phone error. phone format false, phone=" + smsDto.getPhone() + ", userId="
					+ smsDto.getUserId());
			throw new ParamInvalidException("手机号不正确");
		}
		if (StringUtil.isEmpty(smsDto.getCode())) {
			throw new ParamInvalidException("验证码不能为空");
		}

		UserPhoneCode userPhoneCode = userPhoneCodeManager
				.queryUserPhoneCode(new UserPhoneCode(smsDto.getPhone(), UserPhoneCodeTypeEnum.APP_BIND));

		if (null == userPhoneCode || new Date().getTime() > userPhoneCode.getDeadlineTime().getTime()
				|| !StringUtil.equals(userPhoneCode.getSmsCode(), smsDto.getCode())) {
			logger.warn("wechat bind phone smslogin error. smscode error. phone=" + smsDto.getPhone() + ", smscode="
					+ smsDto.getCode());
			return new UserInfoDto(false, "验证码错误或已失效");
		}

		UserInfo userInfo = userInfoManager.queryUserInfoById(smsDto.getUserId());
		if (null == userInfo) {
			logger.error("wechat bind phone error. userinfo is null, phone=" + smsDto.getPhone() + ", userId="
					+ smsDto.getUserId());
			throw new ParamInvalidException("不存在该用户");
		}

		if (UserStatusEnum.NORMAL.getCode() != userInfo.getStatus()) {
			logger.error("wechat bind phone error, user status=" + userInfo.getStatus() + ",  " + userInfo);
			return new UserInfoDto(false, "账户已被封禁");
		}

		if (StringUtil.hasLength(userInfo.getPhone())) {
			logger.error("wechat bind phone error. phone already bind. phone=" + smsDto.getPhone() + ", userId="
					+ smsDto.getUserId());
			return new UserInfoDto(false, "已绑定其他手机号");
		}

		// 双向判断
		List<UserInfo> userInfoList = userInfoManager.queryUserInfoByPhone(smsDto.getPhone());
		if (null != userInfoList) {
			for (UserInfo userInfoTemp : userInfoList) {
				if (!StringUtil.isEmpty(userInfoTemp.getUnionId())) {
					logger.error("wechat bind phone error, wechat already beBind. code=" + smsDto.getPhone()
							+ ".unionid=" + smsDto.getCode());
					return new UserInfoDto(false, "该手机号已绑定其他微信");
				}
			}
		}

		userInfoManager.modifyUserInfoById(new UserInfo(smsDto.getUserId(), smsDto.getPhone(), null));
		logger.info("wechat bind phone success, userId=" + smsDto.getUserId() + ", phone=" + smsDto.getPhone());

		UserInfoDto userInfoDto = userInfoService
				.queryUserInfoById(new BaseDto(smsDto.getUserId(), smsDto.getDeviceId()));
		userInfoDto.setSuccess(true);
		return userInfoDto;

	}

	@MethodDefinition(value = "bindWechat")
	@Override
	public UserInfoDto bindWechat(WechatDto wechatDto) throws MainException {
		if (null == wechatDto || null == wechatDto.getUserId()) {
			throw new ParamInvalidException("userId不能为空");
		}

		if (StringUtil.isEmpty(wechatDto.getCode())) {
			throw new ParamInvalidException("微信code不能为空");
		}

		UserInfo userInfo = userInfoManager.queryUserInfoById(wechatDto.getUserId());
		if (null == userInfo) {
			logger.error("phone bind wechat error, userinfo is empty, code=" + wechatDto.getCode() + ", userId="
					+ wechatDto.getUserId());
			throw new ParamInvalidException("不存在该用户");
		}

		if (UserStatusEnum.NORMAL.getCode() != userInfo.getStatus()) {
			logger.error("phone bind wechat error, user status=" + userInfo.getStatus() + ",  " + userInfo);
			return new UserInfoDto(false, "账户已被封禁");
		}

		if (StringUtil.hasLength(userInfo.getUnionId())) {
			logger.error("phone bind wechat error, wechat already bind, code=" + wechatDto.getCode() + ", userId="
					+ wechatDto.getUserId() + ", unionId=" + userInfo.getUnionId());
			return new UserInfoDto(false, "已绑定其他微信号");
		}

		UserWechatInfo userWechatInfo = userLoginWechatService.getLoginWechatResponse(wechatDto);
		if (null == userWechatInfo) {
			logger.error("phone bind wechat error, wechat login error, " + wechatDto);
			return new UserInfoDto(false, "获取微信用户信息失败");
		}

		// 双向判断
		List<UserInfo> userInfoList = userInfoManager.queryUserInfoByUnionId(userWechatInfo.getUnionId());
		if (CollectionUtil.isNotEmpty(userInfoList)) {
			for (UserInfo userInfoTemp : userInfoList) {
				if (StringUtil.isNotEmpty(userInfoTemp.getPhone())) {
					logger.error("phone  bind wechat error, wechat already beBind. code=" + wechatDto.getCode()
							+ ", unionid=" + userWechatInfo.getUnionId());
					return new UserInfoDto(false, "该微信号已绑定其他手机");
				}
			}
		}

		boolean result = userWechatInfoService.saveUserWechatInfo(userWechatInfo);
		if (result) {
			userInfoManager.modifyUserInfoById(new UserInfo(wechatDto.getUserId(), null, userWechatInfo.getUnionId()));
			logger.info("phone bind wechat success, userId=" + wechatDto.getUserId() + ", unionId="
					+ userWechatInfo.getUnionId());
		}

		UserInfoDto userInfoDto = userInfoService
				.queryUserInfoById(new BaseDto(wechatDto.getUserId(), wechatDto.getDeviceId()));
		userInfoDto.setSuccess(true);
		return userInfoDto;

	}

}
