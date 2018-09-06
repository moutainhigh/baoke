package com.baoke.extra.sms.dao;

import com.baoke.extra.sms.domain.SendSmsDetail;

/**
 * 短信明细dao
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午9:58:40
 */
public interface SendSmsDetailDao{

	SendSmsDetail querySmsByUnionId(String content);

	int modifySms(SendSmsDetail sendSmsDetail);
}
