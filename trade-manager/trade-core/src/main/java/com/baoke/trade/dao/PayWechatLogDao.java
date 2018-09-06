package com.baoke.trade.dao;

import com.baoke.trade.domain.PayWechatLog;

/**
 * 微信支付记录Dao
 * 
 * @author: wyj
 * @date: 2018年6月25日 下午1:46:58
 */
public interface PayWechatLogDao {

	int addPayWechatLog(PayWechatLog payWechatLog);

}
