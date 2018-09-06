package com.baoke.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.constant.SellerIncomeStatus;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.SellerIncomeAmountDetail;
import com.baoke.trade.manager.SellerIncomeAmountDetailManager;
import com.baoke.trade.service.SellerIncomeRecordService;

@Service("sellerIncomeRecordService")
public class SellerIncomeRecordServiceImpl implements SellerIncomeRecordService {

	@Resource
	private SellerIncomeAmountDetailManager sellerIncomeAmountDetailManager;

	@Override
	public void addSellerIncomeRecord(Order order, Long logId, OrderPayTypeEnum payType) {
		SellerIncomeAmountDetail sellerIncomeAmountDetail = new SellerIncomeAmountDetail();
		sellerIncomeAmountDetail.setUserId(order.getUserId());
		sellerIncomeAmountDetail.setSellerId(order.getSellerId());
		sellerIncomeAmountDetail.setEstimateAmount(order.getTotalItemPrice() + order.getTotalPostage());
		sellerIncomeAmountDetail.setAmount(order.getTotalItemPrice() + order.getTotalPostage());
		sellerIncomeAmountDetail.setParentOrderNo(order.getParentOrderNo());
		sellerIncomeAmountDetail.setOrderNo(order.getOrderNo());
		sellerIncomeAmountDetail.setPayLogId(logId);
		sellerIncomeAmountDetail.setPayType(payType.getCode());
		sellerIncomeAmountDetail.setStatus(SellerIncomeStatus.FREEZEN.getCode());
		sellerIncomeAmountDetailManager.addSellerIncomeAmountDetail(sellerIncomeAmountDetail);
	}

}
