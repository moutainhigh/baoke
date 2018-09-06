package com.baoke.push.manager;

import com.baoke.push.domain.SendPushDetail;

/**
 * 保存消息记录
 * 
 * @author ljj
 * @date: 2018年6月19日 上午11:04:30
 */
public interface SendPushDetailManager {

	long addSendPushDetail(SendPushDetail sendPushDetail);
}
