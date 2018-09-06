package com.baoke.push.service;

import com.baoke.push.domain.SendPushDetail;

/**
 * 推送消息
 * 
 * @author ljj
 * @date: 2018年6月20日 上午11:04:05
 */
public interface GetuiPushSendService {

	void sendMessage(SendPushDetail sendPushDetail);

}
