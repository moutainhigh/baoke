package com.baoke.trade.service;

import java.util.Map;

import com.baoke.trade.domain.PayAlipayLog;
import com.baoke.trade.domain.PayWechatLog;

/**
 * 支付日志
 * 
 * @author zjj
 * @date 2018年7月14日 上午10:14:15
 */
public interface PayLogService {

	/**
	 * 保存支付宝回调日志
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午8:02:10
	 * @param paramMap
	 * @param userId
	 * @return
	 */
	PayAlipayLog addPayAlipayLog(Map<String, String> paramMap, Long userId);

	/**
	 * 保存微信回调日志
	 * 
	 * @author zjj
	 * @date 2018年7月10日 下午8:02:10
	 * @param paramMap
	 * @param userId
	 * @return
	 */
	PayWechatLog addPayWechatLog(Map<String, String> paramMap, Long userId);

}
