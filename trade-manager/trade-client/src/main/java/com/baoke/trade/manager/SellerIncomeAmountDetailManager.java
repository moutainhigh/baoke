package com.baoke.trade.manager;

import com.baoke.common.constant.SellerIncomeStatus;
import com.baoke.trade.domain.SellerIncomeAmountDetail;

/**
 * 卖家入账明细manager
 * 
 * @author: wyj
 * @date: 2018年6月21日 下午6:34:03
 */
public interface SellerIncomeAmountDetailManager {
	/**
	 * 根据订单号查询卖家入账明细
	 * 
	 * @author zdy
	 * @date: 2018年7月18日 下午3:46:52
	 * @param orderNo
	 * @return
	 */
	SellerIncomeAmountDetail querySellerIncomeAmountDetailByOrderNo(String orderNo);

	/**
	 * 成交总额
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午1:37:55
	 * @return
	 */
	int queryTotalTradeAmount(long sellerId);

	/**
	 * 昨日成交额
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午1:38:31
	 * @param sellerId
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int querylastdayTradeAmount(long sellerId);

	/**
	 * 
	 * 账户金额
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午1:38:53
	 * @param sellerId
	 * @param status
	 * @return
	 */
	int queryTotalAccountAmount(long sellerId, SellerIncomeStatus sellerIncomeStatus);

	/**
	 * 可提现金额
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午2:25:07
	 * @param sellerId
	 * @param status
	 * @return
	 */
	int queryWithdrawAmount(long sellerId, SellerIncomeStatus sellerIncomeStatus);

	/**
	 * 新增卖家入账明细
	 * 
	 * @author: wyj
	 * @date: 2018年6月23日 下午1:50:19
	 */
	long addSellerIncomeAmountDetail(SellerIncomeAmountDetail sellerIncomeAmountDetail);

	/**
	 * 修改
	 * 
	 * @author zdy
	 * @date: 2018年7月16日 下午3:44:48
	 * @param sellerIncomeAmountDetail
	 * @return
	 */
	int modifySellerIncomeAmountDetailPayType(String orderNo, SellerIncomeStatus sellerIncomeStatus);

}
