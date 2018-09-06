package com.baoke.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.JsonUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.user.constant.UserBannedEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.constant.UserTypeEnum;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.domain.UserWechatInfo;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.manager.UserWechatInfoManager;
import com.baoke.user.service.UserLoginSmallAppService;

import net.sf.cglib.beans.BeanCopier;

@Service("userLoginSmallAppService")
public class UserLoginSmallAppServiceImpl implements UserLoginSmallAppService {
	private static final Logger logger = Logger.getLogger(UserLoginSmallAppServiceImpl.class);

	@Resource
	private UserWechatInfoManager userWechatInfoManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Override
	public LoginResultDto loginSmallApp(UserWechatInfo userWechatInfo) throws MainException {
		if (null == userWechatInfo) {
			throw new ParamInvalidException("传入对象不能为空");
		}
		if (StringUtil.isBlank(userWechatInfo.getOpenId()) || StringUtil.isBlank(userWechatInfo.getUnionId())) {
			throw new ParamInvalidException("必传参数不能为空");
		}

		// 判断是否存在该用户，存在则更新，否则新增
		if (!userWechatInfoManager.isExistsUserWechatInfo(new UserWechatInfo(userWechatInfo.getOpenId(),
				userWechatInfo.getUnionId(), WechatUserSourceTypeEnum.SMALL_APP))) {
			logger.info("add user wechat info, userWechatInfo:[" + JsonUtil.map2json(userWechatInfo) + "]");
			userWechatInfoManager.addUserWechatInfo(userWechatInfo);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("modify user wechat info, userWechatInfo:[" + JsonUtil.map2json(userWechatInfo) + "]");
			}
			userWechatInfoManager.modifyUserWechatInfo(userWechatInfo);
		}

		List<UserInfo> userInfoList = userInfoManager.queryUserInfoByUnionId(userWechatInfo.getUnionId());
		UserInfo userInfo = null;
		if (CollectionUtil.isEmpty(userInfoList)) {
			userInfo = convertUserInfo(userWechatInfo);
			logger.info("add user info, " + userInfo);
			userInfoManager.addUserInfo(userInfo);
		} else {
			userInfo = userInfoList.get(0);
			for (UserInfo userInfoTemp : userInfoList) {
				if (StringUtil.hasLength(userInfoTemp.getUnionId()) && StringUtil.hasLength(userInfoTemp.getPhone())) {
					userInfo = userInfoTemp;
				}
			}
		}

		if (UserStatusEnum.NORMAL.getCode() != userInfo.getStatus()) {
			logger.error("login small app error, user status=" + userInfo.getStatus() + ",  " + userInfo);
			throw new ParamInvalidException("账号无法登录");
		}

		UserInfoDto userInfoDto = userInfo.convert();
		userInfoDto.setWechatNickName(userWechatInfo.getNickName());

		return new LoginResultDto(true, "", userInfoDto);
	}

	private UserInfo convertUserInfo(UserWechatInfo userWechatInfo) {
		UserInfo userInfo = new UserInfo();
		final BeanCopier bc = BeanCopier.create(userWechatInfo.getClass(), userInfo.getClass(), false);
		bc.copy(userWechatInfo, userInfo, null);
		userInfo.setBannedStatus(UserBannedEnum.NO.getCode());
		userInfo.setType(UserTypeEnum.USER.getCode());
		return userInfo;
	}
}
