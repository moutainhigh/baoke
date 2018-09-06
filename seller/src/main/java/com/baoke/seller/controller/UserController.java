package com.baoke.seller.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.user.service.UserLoginSmsService;
import com.baoke.user.service.UserPhoneCodeService;

/**
 * 用户相关控制器
 * 
 * @author zjm
 * @date: 2018年6月22日 上午11:01:48
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserLoginSmsService userLoginSmsService;
	@Resource
	private UserPhoneCodeService userPhoneCodeService;

	/**
	 * 获取验证码
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/createLoginPhoneCode", method = RequestMethod.POST)
	public ResponseResult createLoginPhoneCode(SmsDto smsDto) {
		try {
			smsDto.setUserPhoneCodeTypeEnum(UserPhoneCodeTypeEnum.SELLER_LOGIN);

			BaseResultDto baseResultDto = userPhoneCodeService.createLoginPhoneCode(smsDto);

			if (null == baseResultDto || !baseResultDto.isSuccess()) {
				return new ResponseResult(ResponseResultCodeEnum.ERROR, baseResultDto.getMessage());
			} else {
				return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", baseResultDto);
			}

		} catch (MainException e) {
			logger.error("getcodeLogin PhoneCode error." + smsDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("getcodeLogin PhoneCode error." + smsDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 登录
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/loginSms", method = RequestMethod.POST)
	public ResponseResult loginSms(SmsDto smsDto, HttpSession session) {
		try {
			smsDto.setUserPhoneCodeTypeEnum(UserPhoneCodeTypeEnum.SELLER_LOGIN);
			LoginResultDto loginResultDto = userLoginSmsService.loginSms(smsDto);

			if (null == loginResultDto || !loginResultDto.isSuccess()) {
				return new ResponseResult(ResponseResultCodeEnum.ERROR, loginResultDto.getMessage());
			} else {
				session.setAttribute("userInfo", loginResultDto.getUserInfo());
				session.setAttribute("userId", loginResultDto.getUserInfo().getUserId());
				return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", loginResultDto);
			}

		} catch (MainException e) {
			logger.error("loginSms error." + smsDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("loginSms error." + smsDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

}
