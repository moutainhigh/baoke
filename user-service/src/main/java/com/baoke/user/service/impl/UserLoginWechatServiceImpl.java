package com.baoke.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.constant.config.WechatMobileAppLoginConfig;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.WechatDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.HttpClientHelper;
import com.baoke.common.util.RandomNumUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.user.constant.UserBannedEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.constant.UserTypeEnum;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.domain.UserWechatInfo;
import com.baoke.user.domain.model.WechatTokenResponse;
import com.baoke.user.domain.model.WechatUserInfoResponse;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.service.DeviceUserRelationService;
import com.baoke.user.service.UserInfoService;
import com.baoke.user.service.UserLoginWechatService;
import com.baoke.user.service.UserWechatInfoService;

@ServiceDefinition(value = "userLoginWechatService")
@Service("userLoginWechatService")
public class UserLoginWechatServiceImpl implements UserLoginWechatService {
	private static final Logger logger = Logger.getLogger(UserLoginWechatServiceImpl.class);

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserWechatInfoService userWechatInfoService;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private DeviceUserRelationService deviceUserRelationService;

	@Override
	@MethodDefinition(value = "loginWechat")
	public LoginResultDto loginApp(WechatDto wechatDto) throws MainException {

		if (null == wechatDto || StringUtil.isEmpty(wechatDto.getCode())) {
			throw new ParamInvalidException("code不能为空");
		}

		UserWechatInfo userWechatInfo = getLoginWechatResponse(wechatDto);
		if (null == userWechatInfo) {
			logger.error("wechat login error, get wechatUserInfoResponse error. " + wechatDto);
			return new LoginResultDto(null, wechatDto.getDeviceId(), false, "获取微信用户信息失败", null);
		}

		// 获取昵称
		String nickName = getWechatNickName(userWechatInfo.getNickName());
		userWechatInfo.setNickName(nickName);
		userWechatInfoService.saveUserWechatInfo(userWechatInfo);

		UserInfo userInfo = null;
		List<UserInfo> userInfoList = userInfoManager.queryUserInfoByUnionId(userWechatInfo.getUnionId());
		if (CollectionUtil.isEmpty(userInfoList)) {
			logger.info("wechat login, code=" + wechatDto.getCode() + ", need add userInfo, unionId="
					+ userWechatInfo.getUnionId());
			userInfo = userWechatInfo.converUserInfo();
			userInfo.setStatus(UserStatusEnum.NORMAL.getCode());
			userInfo.setType(UserTypeEnum.USER.getCode());
			userInfo.setBannedStatus(UserBannedEnum.NO.getCode());
			userInfoManager.addUserInfo(userInfo);
			logger.info("wechat login, add userInfo success. " + userInfo);
		} else {
			userInfo = userInfoList.get(0);
			for (UserInfo userInfoTemp : userInfoList) {
				if (StringUtil.hasLength(userInfoTemp.getUnionId()) && StringUtil.hasLength(userInfoTemp.getPhone())) {
					userInfo = userInfoTemp;
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("wechat login. code=" + wechatDto.getCode() + ".modify userInfo, " + userInfo);
			}
			// 不修改用户信息
			// userInfoManager.modifyUserInfo(userInfoData);
		}

		if (UserStatusEnum.NORMAL.getCode() != userInfo.getStatus()) {
			logger.error("wechat login error, user status=" + userInfo.getStatus() + ",  " + userInfo);
			return new LoginResultDto(userInfo.getId(), wechatDto.getDeviceId(), false, "账户已被封禁", null);
		}

		if (WechatUserSourceTypeEnum.MOBILE_APP == wechatDto.getWechatSourceTypeEnum()) {
			deviceUserRelationService.addDeviceUserRelation(userInfo.getId(), wechatDto.getDeviceId());
		}

		BaseDto baseDto = new BaseDto(userInfo.getId(), wechatDto.getDeviceId());
		UserInfoDto userInfoDto = userInfoService.queryUserInfoById(baseDto);

		return new LoginResultDto(userInfoDto.getUserId(), wechatDto.getDeviceId(), true, "", userInfoDto);
	}

	// 获取昵称
	// 如果微信昵称长度达到昵称字数上限（8个汉字）有重复的，则末尾开始替换成随机数字。
	// 例“我是微信昵称名字”-“我是微信昵称名1”。如果有十一个重复的昵称，则“我是微信昵称11”。
	// 如果微信昵称少于等于7个汉字，则自动填充2位数字和字母，例“大锤”-“大锤a1”，如果有101个相同昵称，则再填充1位，例“大锤”-“大锤a1c”
	// ，“我的名字叫大锤2b”-“我的名字叫大2ba”，以此类推。
	private String getWechatNickName(String wechatNickName) {
		// 微信昵称中有符号和表情的内容自动替换0-9随机数字
		String nickName = StringUtil.replaceAllSpecialCharToRandomNum(wechatNickName);
		// 如果微信昵称长度达到昵称字数上限（8个汉字）,截掉末尾多余值
		if (StringUtil.byteLengthByCode(nickName) > CommonConfig.NICKNAME_MAXLENGTH) {
			nickName = StringUtil.byteSubstring(nickName, 16);
		}
		if (StringUtil.isEmpty(nickName)) {
			nickName = CommonConfig.DEFAULT_USER_NICK_PREX + RandomNumUtil.getRandomString(6);
		}
		boolean result = false;
		while (!result) {
			// 如果微信昵称有重复的，则末尾开始替换成随机数字
			int countNum = userInfoManager.countUserInfoByNickName(nickName, UserStatusEnum.NORMAL);

			if (countNum > 0) {
				// 字节长度
				int nickNameLength = StringUtil.byteLengthByCode(nickName);
				int userInfoListLength = String.valueOf(countNum).length();
				if (nickNameLength == CommonConfig.NICKNAME_MAXLENGTH) {
					int lengthTemp = CommonConfig.NICKNAME_MAXLENGTH - userInfoListLength;
					nickName = StringUtil.byteSubstring(nickName, lengthTemp) + countNum;
				} else {
					// 需追加的字节数
					int appendByteLength = 2;
					if (countNum > 100) {
						appendByteLength = userInfoListLength;
					}
					int differenceValue = (nickNameLength + appendByteLength) - CommonConfig.NICKNAME_MAXLENGTH;
					if (differenceValue <= 0) {
						nickName += RandomNumUtil.getRandomString(appendByteLength);
					} else {
						nickName = StringUtil.byteSubstring(nickName, (nickNameLength - differenceValue))
								+ RandomNumUtil.getRandomString(appendByteLength);
					}
				}
			} else {
				result = true;
			}
		}

		return nickName;
	}

	@Override
	public UserWechatInfo getLoginWechatResponse(WechatDto wechatDto) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("{code}", wechatDto.getCode());
		map.put("{appId}", WechatMobileAppLoginConfig.APPID);
		map.put("{secret}", WechatMobileAppLoginConfig.SECRET);

		// 根据code获取access_token
		String tokenUrl = StringUtil.urlFormat(WechatMobileAppLoginConfig.WECHAT_TOKEN_URL, map);
		String tokenResponse = HttpClientHelper.getStringByUrl(tokenUrl, true, WechatMobileAppLoginConfig.CHARSET_NAME);

		if (null == tokenResponse) {
			logger.error("wechat login error, tokenResponse is empty. code=" + wechatDto.getCode());
			return null;
		}

		WechatTokenResponse wechatTokenResponse = new WechatTokenResponse();
		wechatTokenResponse = JSONObject.parseObject(tokenResponse, wechatTokenResponse.getClass());

		if (null == wechatTokenResponse || null != wechatTokenResponse.getErrcode()) {
			logger.error("wechat login error, wechatTokenResponse error. errorCode=" + wechatTokenResponse.getErrcode()
					+ ", errorMsg=" + wechatTokenResponse.getErrmsg());
			return null;
		}

		// 根据token获取用户信息
		map.put("{access_token}", wechatTokenResponse.getAccess_token());
		map.put("{openid}", wechatTokenResponse.getOpenid());
		map.put("{lang}", WechatMobileAppLoginConfig.WECHAT_LANG);

		String userInfoUrl = StringUtil.urlFormat(WechatMobileAppLoginConfig.WECHAT_USER_INFO_URL, map);
		String userInfoResponse = HttpClientHelper.getStringByUrl(userInfoUrl, true,
				WechatMobileAppLoginConfig.CHARSET_NAME);

		if (null == userInfoResponse) {
			logger.error("wechat login error, userInfoResponse is empty. code=" + wechatDto.getCode());
			return null;
		}

		WechatUserInfoResponse wechatUserInfoResponse = new WechatUserInfoResponse();
		wechatUserInfoResponse = JSONObject.parseObject(userInfoResponse, wechatUserInfoResponse.getClass());

		if (null == wechatUserInfoResponse || null != wechatUserInfoResponse.getErrcode()) {
			logger.error("wechat login error, wechatUserInfoResponse error. errorCode="
					+ wechatUserInfoResponse.getErrcode() + ", errorMsg=" + wechatUserInfoResponse.getErrmsg());
			return null;
		}

		return wechatUserInfoResponse.convert(wechatDto.getWechatSourceTypeEnum());
	}

}
