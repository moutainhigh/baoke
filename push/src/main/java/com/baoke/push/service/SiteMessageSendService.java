package com.baoke.push.service;

import com.baoke.common.domain.message.SiteMessage;

/**
 * 站内消息
 * 
 * @author zjj
 * @date 2018年7月16日 下午8:17:31
 */
public interface SiteMessageSendService {

	void addSiteMessage(SiteMessage siteMessage);
}
