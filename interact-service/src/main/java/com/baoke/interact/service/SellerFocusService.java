package com.baoke.interact.service;

import com.baoke.common.dto.SellerListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;

/**
 * 卖家（播主）关注
 * 
 * @author: zdy
 * @date: 2018年6月12日
 */
public interface SellerFocusService {
	/**
	 * 查询我的关注
	 * 
	 * @author zdy
	 * @date: 2018年6月14日 上午10:42:07
	 * @param pageInfoDto
	 * @return
	 * @throws ParamInvalidException
	 */
	SellerListDto queryMyFocusSeller(SellerInfoDto sellerInfoDto) throws MainException;

	/**
	 * 保存关注播主（卖家）
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:48:13
	 * @param sellerFocusDto
	 * @throws ParamInvalidException
	 */
	BaseResultDto saveFocusSeller(SellerInfoDto sellerInfoDto) throws MainException;
}
