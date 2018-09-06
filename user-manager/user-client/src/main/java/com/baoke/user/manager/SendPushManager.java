package com.baoke.user.manager;

import com.baoke.common.domain.message.PushMessage;

/**
 * push发送
 * 
 * @author wyh
 * @date 2018年7月6日 下午7:03:56
 *
 */
public interface SendPushManager {

	/**
	 * push消息发送
	 * 
	 * @author wyh
	 * @date 2018年7月6日 下午7:02:20
	 * 
	 * @param pushMessage
	 * @return
	 */
	public boolean sendPush(PushMessage pushMessage);

}
