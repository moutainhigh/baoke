package com.baoke.trade.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.constant.SellerIncomeStatus;
import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.SellerIncomeAmountDetailDao;
import com.baoke.trade.domain.SellerIncomeAmountDetail;
import com.baoke.trade.manager.SellerIncomeAmountDetailManager;

@Component
@DataSource("coreDataSource")
public class SellerIncomeAmountDetailManagerImpl implements SellerIncomeAmountDetailManager {

	@Resource
	private SellerIncomeAmountDetailDao sellerIncomeAmountDetailDao;

	@Override
	public SellerIncomeAmountDetail querySellerIncomeAmountDetailByOrderNo(String orderNo) {
		return sellerIncomeAmountDetailDao.querySellerIncomeAmountDetailByOrderNo(orderNo);
	}

	@Override
	public int queryTotalTradeAmount(long sellerId) {
		return sellerIncomeAmountDetailDao.queryTotalTradeAmount(sellerId);
	}

	@Override
	public int querylastdayTradeAmount(long sellerId) {
		return sellerIncomeAmountDetailDao.querylastdayTradeAmount(sellerId);
	}

	@Override
	public int queryTotalAccountAmount(long sellerId, SellerIncomeStatus sellerIncomeStatus) {
		return sellerIncomeAmountDetailDao.queryTotalAccountAmount(sellerId, sellerIncomeStatus.getCode());
	}

	@Override
	public int queryWithdrawAmount(long sellerId, SellerIncomeStatus sellerIncomeStatus) {
		return sellerIncomeAmountDetailDao.queryWithdrawAmount(sellerId, sellerIncomeStatus.getCode());
	}

	public long addSellerIncomeAmountDetail(SellerIncomeAmountDetail sellerIncomeAmountDetail) {
		sellerIncomeAmountDetailDao.addSellerIncomeAmountDetail(sellerIncomeAmountDetail);
		return sellerIncomeAmountDetail.getId();
	}

	@Override
	public int modifySellerIncomeAmountDetailPayType(String orderNo, SellerIncomeStatus sellerIncomeStatus) {
		return sellerIncomeAmountDetailDao.modifySellerIncomeAmountDetailPayType(orderNo, sellerIncomeStatus.getCode());
	}

}
