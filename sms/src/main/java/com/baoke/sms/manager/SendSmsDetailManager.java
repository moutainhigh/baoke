package com.baoke.sms.manager;

import com.baoke.sms.domain.SendSmsDetail;

/**
 * 保存短信记录
 * 
 * @author ljj
 * @date: 2018年6月22日 下午12:43:54
 */
public interface SendSmsDetailManager {

	int addSendSmsDetail(SendSmsDetail sendSmsDetail);

}
