package com.baoke.trade.service;

import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.domain.Order;

/**
 * 卖家收入明细
 * 
 * @author zjj
 * @date 2018年7月13日 下午8:54:53
 */
public interface SellerIncomeRecordService {

	/**
	 * 保存卖家收入明细
	 * 
	 * @author zjj
	 * @date 2018年7月4日 下午11:30:27
	 * @param orderList
	 */
	void addSellerIncomeRecord(Order order, Long logId, OrderPayTypeEnum payType);

}
