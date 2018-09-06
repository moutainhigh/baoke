package com.baoke.trade.dao;

import org.apache.ibatis.annotations.Param;

import com.baoke.trade.domain.SellerIncomeAmountDetail;

/**
 * 卖家入账明细Dao
 * 
 * @author: wyj
 * @date: 2018年6月21日 下午6:38:24
 */
public interface SellerIncomeAmountDetailDao {
	SellerIncomeAmountDetail querySellerIncomeAmountDetailByOrderNo(@Param("orderNo") String orderNo);

	int queryTotalTradeAmount(long sellerId);

	int querylastdayTradeAmount(long sellerId);

	int queryTotalAccountAmount(@Param("sellerId") long sellerId, @Param("status") Integer status);

	int queryWithdrawAmount(@Param("sellerId") long sellerId, @Param("status") Integer status);

	int addSellerIncomeAmountDetail(SellerIncomeAmountDetail sellerIncomeAmountDetail);

	int modifySellerIncomeAmountDetailPayType(@Param("orderNo") String orderNo, @Param("status") int status);
}
