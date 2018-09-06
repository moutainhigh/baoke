package com.baoke.user.manager;

import com.baoke.common.domain.message.SiteMessage;

/**
 * 站内消息发送
 * 
 * @author zjj
 * @date 2018年7月14日 下午2:58:26
 */
public interface SendSiteManager {

	/**
	 * 站内消息发送
	 * 
	 * @author zjj
	 * @date 2018年7月14日 下午2:58:42
	 * @param pushMessage
	 * @return
	 */
	public boolean sendSite(SiteMessage siteMessage);

}
