package com.baoke.push.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baoke.common.util.StringUtil;
import com.baoke.push.constant.PushStatusEnum;
import com.baoke.push.domain.SendPushDetail;
import com.baoke.push.manager.SendPushDetailManager;
import com.baoke.push.service.GetuiPushSendService;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;

@Service("getuiPushSendService")
public class GeTuiPushSendServiceImpl implements GetuiPushSendService {

	private static final Logger logger = LoggerFactory.getLogger(GeTuiPushSendServiceImpl.class);

	public static final String APPID = "iaFKj4ex1U7fWbgr9BPvs6";
	public static final String APPK = "tIWjIsotic5cbhVu0GrUZ4";
	public static final String MASTERSECRET = "qfv6LGBiMe72donWBCXGd4";
	public static final String HOST = "http://sdk.open.api.igexin.com/apiex.htm";

	@Resource
	private SendPushDetailManager sendPushDetailManager;

	@Override
	public void sendMessage(SendPushDetail sendPushDetail) {

		if (null == sendPushDetail || StringUtil.isEmpty(sendPushDetail.getCid())
				|| StringUtil.isEmpty(sendPushDetail.getContent())) {
			sendPushDetail.setStatus(PushStatusEnum.UNSENT.getCode());
			sendPushDetailManager.addSendPushDetail(sendPushDetail);

			logger.warn("send push message error, param is empty." + sendPushDetail);
			return;
		}
		IPushResult iPushResult = sentPushMsg(sendPushDetail.getCid(), sendPushDetail.getTitle(),
				sendPushDetail.getContent());
		String getIPushResult = iPushResult.getResponse().get("result").toString();
		if (getIPushResult.equals("ok")) {
			sendPushDetail.setPushTime(new Date());
			sendPushDetail.setStatus(PushStatusEnum.SENT.getCode());
			sendPushDetailManager.addSendPushDetail(sendPushDetail);
		}

	}

	private IPushResult sentPushMsg(String cid, String title, String titleText) {

		SingleMessage message = new SingleMessage();
		message.setData(getNotifacationTemplate(APPID, APPK, title, titleText));
		// 设置推送目标
		Target target = new Target();
		target.setAppId(APPID);
		target.setClientId(cid);
		IGtPush push = new IGtPush(APPK, MASTERSECRET);
		return push.pushMessageToSingle(message, target);

	}

	private NotificationTemplate getNotifacationTemplate(String appId, String appKey, String title, String titleText) {

		NotificationTemplate template = new NotificationTemplate();

		template.setAppId(appId);
		template.setAppkey(appKey);
		// 穿透消息设置为，1 强制启动应用
		template.setTransmissionType(1);
		// 设置穿透内容
		// template.setTransmissionContent(msg.get("transText"));
		Style0 style = new Style0();
		style.setTitle(title);
		style.setText(titleText);
		// 设置通知，响铃、震动、可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		// 设置
		template.setStyle(style);
		return template;
	}

}
