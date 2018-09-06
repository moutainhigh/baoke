package com.baoke.item.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.constant.SellerIncomeStatus;
import com.baoke.common.dto.seller.SellerHomeDto;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.manager.SellerFocusManager;
import com.baoke.interact.manager.VideoGreatManager;
import com.baoke.item.service.SellerHomeService;
import com.baoke.trade.constant.OrderStatusEnum;
import com.baoke.trade.manager.OrderManager;
import com.baoke.trade.manager.SellerIncomeAmountDetailManager;

@Service("sellerHomeService")
public class SellerHomeServiceImpl implements SellerHomeService {

	@Resource
	private SellerIncomeAmountDetailManager sellerIncomeAmountDetailManager;
	@Resource
	private SellerFocusManager sellerFocusManager;
	@Resource
	private VideoGreatManager videoGreatManager;
	@Resource
	private OrderManager orderManager;

	@Override
	public SellerHomeDto sellerHomeStatistics(long sellerId) {

		int totalTradeAmount = sellerIncomeAmountDetailManager.queryTotalTradeAmount(sellerId);
		int lastdayTradeAmount = sellerIncomeAmountDetailManager.querylastdayTradeAmount(sellerId);
		int accountAmount = sellerIncomeAmountDetailManager.queryTotalAccountAmount(sellerId,
				SellerIncomeStatus.SETTLED);
		int withdrawAmount = sellerIncomeAmountDetailManager.queryWithdrawAmount(sellerId, SellerIncomeStatus.NORMAL);
		int lastdayOrderNum = orderManager.queryLastdayOrderNum(sellerId, OrderStatusEnum.WAIT_PAY);
		int lastdayAddFansNum = sellerFocusManager.queryLastdayAddFansNum(sellerId, SellerFocusStatusEnum.FOCUS);
		int lastdayAddLikeNum = videoGreatManager.queryLastdayAddLikeNum(sellerId, GreatStatusEnum.GREAT);

		SellerHomeDto sellerHomeDto = new SellerHomeDto();
		sellerHomeDto.setTotalTradeAmount(totalTradeAmount);
		sellerHomeDto.setLastdayTradeAmount(lastdayTradeAmount);
		sellerHomeDto.setLastdayOrderNum(lastdayOrderNum);
		sellerHomeDto.setTotalAccountAmount(accountAmount);
		sellerHomeDto.setWithdrawAmount(withdrawAmount);
		sellerHomeDto.setLastdayAddFansNum(lastdayAddFansNum);
		sellerHomeDto.setLastdayAddLikeNum(lastdayAddLikeNum);
		return sellerHomeDto;
	}

}
