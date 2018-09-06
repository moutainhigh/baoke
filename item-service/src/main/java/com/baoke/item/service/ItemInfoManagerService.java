package com.baoke.item.service;

import com.baoke.common.dto.ItemListDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.dto.seller.ItemPostageDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 商品管理服务
 * 
 * @author ljj
 * @date: 2018年7月4日 下午1:45:06
 */
public interface ItemInfoManagerService {

	/**
	 * 首页商品预警少于10件的商品
	 * 
	 * @author ljj
	 * @date: 2018年7月12日 下午7:10:49
	 */
	ItemListDto queryItemDetailInfoLessTenBySellerId(long sellerId, PageInfo pageInfo) throws ParamInvalidException;

	/**
	 * 修改商品邮费
	 * 
	 * @author ljj
	 * @date: 2018年7月3日 下午7:22:49
	 */
	void modifyItemPostage(ItemPostageDto itemInfoPostDto) throws ParamInvalidException;

	/**
	 * 修改商品库存
	 * 
	 * @author ljj
	 * @date: 2018年7月5日 下午7:13:54
	 * @param itemDetailInfoDto
	 * @return
	 * @throws ParamInvalidException
	 */
	int modifyItemDetailNum(ItemDetailInfoDto itemDetailInfoDto) throws ParamInvalidException;

}
