package com.baoke.user.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.domain.message.SmsMessage;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.DateUtil;
import com.baoke.common.util.PhoneNumberUtil;
import com.baoke.common.util.RandomNumUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.user.domain.UserPhoneCode;
import com.baoke.user.manager.SendSmsManager;
import com.baoke.user.manager.UserPhoneCodeManager;
import com.baoke.user.service.UserPhoneCodeService;

/**
 * 验证码发送service实现
 * 
 * @author zjm
 * @date: 2018年7月4日 下午7:59:30
 */
@ServiceDefinition(value = "userPhoneCodeService")
@Service("userPhoneCodeService")
public class UserPhoneCodeServiceImpl implements UserPhoneCodeService {

	private static final Logger logger = Logger.getLogger(UserPhoneCodeServiceImpl.class);

	@Resource
	private UserPhoneCodeManager userPhoneCodeManager;

	@Resource
	private SendSmsManager sendSmsManager;

	private Object objectLock = new Object();

	@Override
	@MethodDefinition(value = "createLoginPhoneCode")
	public BaseResultDto createLoginPhoneCode(SmsDto smsDto) throws MainException {
		return this.createPhoneCode(smsDto);
	}

	@MethodDefinition(value = "createBindPhoneCode", needLogin = true)
	@Override
	public BaseResultDto createBindPhoneCode(SmsDto smsDto) throws MainException {
		return this.createPhoneCode(smsDto);
	}

	private BaseResultDto createPhoneCode(SmsDto smsDto) throws ParamInvalidException {
		if (null == smsDto || StringUtil.isEmpty(smsDto.getPhone())) {
			throw new ParamInvalidException("手机号不能为空");
		}

		if (!PhoneNumberUtil.check(smsDto.getPhone())) {
			logger.error("create Login phone code error, user phone error, phone=" + smsDto.getPhone() + ", type="
					+ smsDto.getUserPhoneCodeTypeEnum().getName());
			throw new ParamInvalidException("请输入正确的手机号码");
		}

		String code = RandomNumUtil.getRandomNum(6);
		String message = smsDto.getUserPhoneCodeTypeEnum().getMessageTemplate().replace("{code}", code);
		Calendar calendar = Calendar.getInstance();

		synchronized (objectLock) {
			int countTime = userPhoneCodeManager.countUserPhoneCodeByPhoneAndDate(smsDto.getPhone(),
					DateUtil.getFirstTimeOfDate(new Date()), smsDto.getUserPhoneCodeTypeEnum());

			if ((countTime + 1) > smsDto.getUserPhoneCodeTypeEnum().getSendLimit()) {
				logger.warn("create Login phone code error, message code sent more than "
						+ smsDto.getUserPhoneCodeTypeEnum().getSendLimit() + " times, " + "phone=" + smsDto.getPhone()
						+ ", type=" + smsDto.getUserPhoneCodeTypeEnum().getName());

				return new BaseResultDto(false, "今天验证码发送已达上限");
			}

			userPhoneCodeManager.addUserPhoneCode(new UserPhoneCode(smsDto.getPhone(), code,
					DateUtil.addMillis(calendar.getTime(),
							(long) smsDto.getUserPhoneCodeTypeEnum().getValidMillisecond()),
					smsDto.getUserPhoneCodeTypeEnum()));
		}

		SmsMessage smsMessage = new SmsMessage(smsDto.getPhone(), message, calendar.getTimeInMillis(),
				smsDto.getUserPhoneCodeTypeEnum().getValidMillisecond());
		sendSmsManager.sendSms(smsMessage);

		if (logger.isDebugEnabled()) {
			logger.debug("create Login phone code success, phone=" + smsDto.getPhone() + ", type="
					+ smsDto.getUserPhoneCodeTypeEnum().getName() + ", code=" + code);
		}

		return new BaseResultDto(true, "");
	}

}
