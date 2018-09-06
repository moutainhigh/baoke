package com.baoke.item.service;

import com.baoke.common.dto.seller.SellerHomeDto;
import com.baoke.common.exception.base.MainException;

public interface SellerHomeService {

	/**
	 * seller首页
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午8:08:12
	 * @param sellerId
	 * @return
	 * @throws MainException
	 */
	SellerHomeDto sellerHomeStatistics(long sellerId) throws MainException;

}
