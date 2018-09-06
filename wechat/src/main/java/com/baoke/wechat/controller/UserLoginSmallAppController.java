package com.baoke.wechat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.constant.config.WechatMobileAppLoginConfig;
import com.baoke.common.constant.config.WechatSmallAppLoginConfig;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.HttpClientHelper;
import com.baoke.common.util.StringUtil;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.domain.UserWechatInfo;
import com.baoke.user.service.impl.UserLoginSmallAppServiceImpl;
import com.baoke.wechat.constant.CodeEnum;
import com.baoke.wechat.domain.WechatUserInfoModel;
import com.baoke.wechat.domain.encrypt.SessionKeyInfo;
import com.baoke.wechat.domain.encrypt.WechatUserInfoEncrypt;
import com.baoke.wechat.result.ResponseResult;
import com.baoke.wechat.util.WxEncryptUtil;

/**
 * 微信小程序用户登录
 * 
 * @author wyh
 * @date 2018年7月16日 下午3:20:39
 *
 */
@RestController
public class UserLoginSmallAppController {

	private static final Logger logger = Logger.getLogger(UserLoginSmallAppController.class);

	@Resource
	private UserLoginSmallAppServiceImpl userLoginSmallAppServiceImpl;

	@RequestMapping(value = "/user/loginWechat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult loginWechat(WechatUserInfoEncrypt wechatUserInfoEncrypt, HttpSession session) {

		if (StringUtils.isEmpty(wechatUserInfoEncrypt.getCode())) {
			return new ResponseResult(CodeEnum.ERROR, "code不能为空");
		}
		if (StringUtils.isEmpty(wechatUserInfoEncrypt.getEncryptedData())) {
			return new ResponseResult(CodeEnum.ERROR, "encryptedData不能为空");
		}

		if (StringUtils.isEmpty(wechatUserInfoEncrypt.getIv())) {
			return new ResponseResult(CodeEnum.ERROR, "iv不能为空");
		}

		Map<String, String> params = new HashMap<>();
		params.put("{appId}", WechatSmallAppLoginConfig.APPID);
		params.put("{secret}", WechatSmallAppLoginConfig.SECRET);
		params.put("{js_code}", wechatUserInfoEncrypt.getCode());
		String userInfoUrl = StringUtil.urlFormat(WechatSmallAppLoginConfig.LOGIN_URL, params);
		String userInfoResponse = HttpClientHelper.getStringByUrl(userInfoUrl, true,
				WechatMobileAppLoginConfig.CHARSET_NAME);

		SessionKeyInfo sessionKeyInfo = JSONObject.parseObject(userInfoResponse, SessionKeyInfo.class);

		if (null == sessionKeyInfo || !sessionKeyInfo.valid()) {
			logger.error(
					"loginWechat get UserInfo erro: " + sessionKeyInfo + "js_code=" + wechatUserInfoEncrypt.getCode());
			return new ResponseResult(CodeEnum.ERROR, sessionKeyInfo == null ? "网络异常" : sessionKeyInfo.getErrmsg());
		}

		WechatUserInfoModel wechatUserInfoModel = WxEncryptUtil.getUserInfo(wechatUserInfoEncrypt.getEncryptedData(),
				sessionKeyInfo.getSession_key(), wechatUserInfoEncrypt.getIv());
		if (null == wechatUserInfoModel || StringUtil.isBlank(wechatUserInfoModel.getUnionId())) {
			logger.error("encryptedData encrypt erro: encryptedData=" + wechatUserInfoEncrypt.getEncryptedData()
					+ "session_key=" + sessionKeyInfo.getSession_key() + "iv=" + wechatUserInfoEncrypt.getIv());
			return new ResponseResult(CodeEnum.ERROR, "数据为空");
		}

		UserWechatInfo userWechatInfo = convertUserWechatInfo(wechatUserInfoModel);
		try {
			LoginResultDto loginResultDto = userLoginSmallAppServiceImpl.loginSmallApp(userWechatInfo);

			if (!loginResultDto.isSuccess()) {
				throw new MainException("登录出错!" + loginResultDto.getMessage());
			}

			session.setAttribute("userInfoDto", loginResultDto.getUserInfo());
			session.setAttribute("userId", loginResultDto.getUserInfo().getUserId());
			session.setAttribute("unionId", loginResultDto.getUserInfo().getUnionId());

		} catch (Exception e) {
			logger.error("user login error, user unionId=" + userWechatInfo.getUnionId(), e);
			return new ResponseResult(CodeEnum.ERROR, e.getMessage());
		}

		return new ResponseResult(CodeEnum.SUCCESS, "", userWechatInfo.getUnionId());
	}

	private UserWechatInfo convertUserWechatInfo(WechatUserInfoModel wechatUserInfoModel) {
		UserWechatInfo userWechatInfo = new UserWechatInfo();
		userWechatInfo.setCity(wechatUserInfoModel.getCity());
		userWechatInfo.setCountry(wechatUserInfoModel.getCountry());
		userWechatInfo.setHeadImgUrl(wechatUserInfoModel.getAvatarUrl());
		userWechatInfo.setNickName(wechatUserInfoModel.getNickName());
		userWechatInfo.setOpenId(wechatUserInfoModel.getOpenId());
		userWechatInfo.setProvince(wechatUserInfoModel.getProvince());
		userWechatInfo.setSex(wechatUserInfoModel.getGender());
		userWechatInfo.setSourceType(WechatUserSourceTypeEnum.SMALL_APP.getCode());
		userWechatInfo.setUnionId(wechatUserInfoModel.getUnionId());
		userWechatInfo.setStatus(UserStatusEnum.NORMAL.getCode());
		return userWechatInfo;
	}
}
