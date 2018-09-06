package com.baoke.extra.sms.controller;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoke.common.domain.result.Result;
import com.baoke.common.util.DateUtil;
import com.baoke.extra.sms.constant.SmsTypeEnum;
import com.baoke.extra.sms.dao.SendSmsDetailDao;
import com.baoke.extra.sms.domain.SendSmsDetail;
import com.baoke.extra.sms.domain.request.YunTongXunRequest;

@Controller
@ResponseBody
public class SMSNotifyController {
	private static final Logger logger = LoggerFactory
			.getLogger(SMSNotifyController.class);

	@Autowired
	private SendSmsDetailDao sendSmsDetailDao;

	/**
	 * 云通信第三方回调接口
	 * 
	 * @param yunTongXunRequest
	 * @return
	 */
	@RequestMapping(value = "/sms/notify_ytx", method = RequestMethod.POST)
	public Result smsNotify(YunTongXunRequest yunTongXunRequest) {
		Result result = new Result();
		if (SmsTypeEnum.STATUS_REPORT.getCode() != yunTongXunRequest
				.getSmsType()) // 状态回执
		{
			result.setSuccess(false);
			result.setMessage("");
			result.setBody(null);
			return result;
		}

		SendSmsDetail sendSmsDetail = sendSmsDetailDao
				.querySmsByUnionId(yunTongXunRequest.getContent());

		if (sendSmsDetail == null) // 发送状态未成功
		{
			result.setSuccess(false);
			result.setMessage("");
			result.setBody(null);
			logger.error("回调短信不存在" + yunTongXunRequest);
			return result;
		}

		try {
			// 实际发送时间
			Date sendTime = DateUtil.parse(yunTongXunRequest.getDateSent(),
					DateUtil.DATE_TIME_FORMAT_STR);
			sendSmsDetail.setSendTime(sendTime);
		} catch (ParseException e) {
			logger.error("短信发送时间为空" + yunTongXunRequest);
		}

		try {
			// 到达时间
			Date receiveTime = DateUtil.parse(yunTongXunRequest.getRecvTime(),
					DateUtil.DATE_TIME_FORMAT_STR);
			sendSmsDetail.setReceiveTime(receiveTime);
		} catch (ParseException e) {
			logger.error("短信接收时间为空" + yunTongXunRequest);
		}

		// 接收状态
		sendSmsDetail.setReceiveStatus(
				Integer.valueOf(yunTongXunRequest.getStatus()));

		sendSmsDetailDao.modifySms(sendSmsDetail);

		result.setSuccess(false);
		result.setMessage("未正确发送的短信");
		return result;

	}

}
