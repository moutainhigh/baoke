package com.baoke.sms.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baoke.common.domain.message.SmsMessage;
import com.baoke.common.util.DateUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.sms.constant.SmsChannelCode;
import com.baoke.sms.constant.SmsSendStatusEnum;
import com.baoke.sms.constant.YunTongXunSmsCode;
import com.baoke.sms.domain.SendSmsDetail;
import com.baoke.sms.manager.SendSmsDetailManager;
import com.baoke.sms.service.SmsSentService;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 云通讯短信发送
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午11:05:32
 */
@Service("yuntongxunSmsSentService")
public class YunTongXunSmsSentServiceImpl implements SmsSentService {

	private static final Logger logger = Logger.getLogger(YunTongXunSmsSentServiceImpl.class);

	@Resource
	private SendSmsDetailManager sendSmsDetailManager;

	/** 线上短信品台ip */
	@Value("${yuntongxun.prod.server.ip}")
	private String prodServerIp;

	/** 服务端口 */
	@Value("${yuntongxun.server.port}")
	private String serverPort;

	/** 账号 */
	@Value("${yuntongxun.account.sid}")
	private String accountSid;

	/** token */
	@Value("${yuntongxun.account.token}")
	private String accountToken;

	/** appId */
	@Value("${yuntongxun.app.id}")
	private String appId;

	/** templateId */
	@Value("${yuntongxun.template.id}")
	private String templateId;

	@Override
	public void sendMessage(SmsMessage smsMessage) {

		// 创建短信发送记录
		SendSmsDetail sendSmsDetail = new SendSmsDetail();
		sendSmsDetail.setPhone(smsMessage.getPhone());
		sendSmsDetail.setMessage(smsMessage.getMessage());
		sendSmsDetail.setValidMillisecond(smsMessage.getValidMillisecond());
		sendSmsDetail.setValidSendTime(new Date(smsMessage.getSendTime()));
		sendSmsDetail.setChannel(SmsChannelCode.YunTongXun.getCode());

		if (StringUtil.isEmpty(sendSmsDetail.getPhone()) || StringUtil.isEmpty(sendSmsDetail.getMessage())) {
			sendSmsDetail.setSendStatus(SmsSendStatusEnum.DATA_ERRO.getCode());
			sendSmsDetail.setSmsUnionId("-1");
			sendSmsDetail.setSendDesc("手机号码或内容为空");
			sendSmsDetailManager.addSendSmsDetail(sendSmsDetail);
			return;
		}

		if (new Date().getTime() - sendSmsDetail.getValidSendTime().getTime() >= sendSmsDetail.getValidMillisecond()) {
			sendSmsDetail.setSendStatus(SmsSendStatusEnum.INVALID.getCode());
			sendSmsDetail.setSmsUnionId("-1");
			sendSmsDetail.setSendDesc("已失效");
			sendSmsDetailManager.addSendSmsDetail(sendSmsDetail);
			return;
		}

		HashMap<String, Object> result = sentSms(sendSmsDetail.getPhone(), sendSmsDetail.getMessage());

		String resCode = result.get("statusCode") == null ? "" : result.get("statusCode").toString();
		sendSmsDetail.setSendDesc(YunTongXunSmsCode.getDesc(resCode).toString());
		sendSmsDetail.setSendStatus(YunTongXunSmsCode.SUCCESS.getCode().equals(resCode)
				? SmsSendStatusEnum.SUCCESS.getCode() : SmsSendStatusEnum.SENDING.getCode());

		String data = result.get("data") == null ? "" : result.get("data").toString();

		JSONObject dataObject = (JSONObject) JSON.parse(data);
		if (dataObject != null) {

			JSONObject templateSms = dataObject.getJSONObject("templateSMS");
			String smsUnionId = templateSms.get("smsMessageSid") == null ? "-1"
					: result.get("smsMessageSid").toString();
			sendSmsDetail.setSmsUnionId(smsUnionId);
			try {
				String dateCreate = templateSms.get("dateCreate") == null ? "" : result.get("dateCreate").toString();
				sendSmsDetail.setSendTime(DateUtil.parse(dateCreate, DateUtil.DATE_TIME_FORMAT_STR));
			} catch (ParseException e) {
			}
		}
		sendSmsDetailManager.addSendSmsDetail(sendSmsDetail);
	}

	private HashMap<String, Object> sentSms(String phone, String content) {
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init(prodServerIp, serverPort);
		// 初始化主帐号和主帐号令牌
		restAPI.setAccount(accountSid, accountToken);
		// 初始化应用ID *
		restAPI.setAppId(appId);
		HashMap<String, Object> resultMap = restAPI.sendTemplateSMS(phone, templateId, new String[] { content });
		if (logger.isDebugEnabled()) {
			logger.debug("send sms end, phone=" + phone + ", result=" + resultMap);
		}
		return resultMap;
	}
}
