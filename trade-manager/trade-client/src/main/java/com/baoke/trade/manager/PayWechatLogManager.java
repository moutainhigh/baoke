package com.baoke.trade.manager;

import com.baoke.trade.domain.PayWechatLog;

/**
 * 微信支付记录manager
 * 
 * @author: wyj
 * @date: 2018年6月25日 下午1:47:55
 */
public interface PayWechatLogManager {

	/**
	 * 新增payWechatLog
	 * 
	 * @author: wyj
	 * @date: 2018年6月25日 下午3:32:31
	 */
	long addPayWechatLog(PayWechatLog payWechatLog);

}
