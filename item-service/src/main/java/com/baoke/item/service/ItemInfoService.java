package com.baoke.item.service;

import java.util.List;

import com.baoke.common.dto.ItemDetailDto;
import com.baoke.common.dto.ItemDto;
import com.baoke.common.dto.ItemListDto;
import com.baoke.common.dto.SellerItemListDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;

/**
 * 商品Service
 * 
 * @author zdy
 * @date: 2018年6月13日 下午2:22:18
 */
public interface ItemInfoService {

	/**
	 * 根据ID查询商品及详情信息
	 * 
	 * @author wyh
	 * @date 2018年7月7日 下午9:49:04
	 * 
	 * @param itemId
	 * @return 可为空
	 */
	ItemInfoDto queryItemInfoByItemId(long itemId);

	/**
	 * 查询商品及详情信息、及关联的所有视频信息
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:22:39
	 * @param itemInfoDto.itemId
	 * @return
	 * @throws MainException
	 */
	ItemDetailDto queryItemByItemId(ItemInfoDto itemInfoDto) throws MainException;

	/**
	 * 分页查询播主（卖家）的商品信息
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:22:49
	 * @param sellerInfoDto.sellerId
	 * @return
	 * @throws ParamInvalidException
	 */
	SellerItemListDto queryItemListBySellerId(SellerInfoDto sellerInfoDto) throws ParamInvalidException;

	/**
	 * 分页查询最新商品信息(商城－新品)
	 * 
	 * @author zdy
	 * @date: 2018年7月4日 下午5:27:37
	 * @return
	 */
	List<ItemInfoDto> queryItemNewList(PageInfo pageInfo);

	/**
	 * 根据商品名称/状态查询商品
	 * 
	 * @author ljj
	 * @date: 2018年7月3日 下午4:22:49
	 */
	ItemListDto queryItemInfoListByPage(ItemDto ItemDto) throws ParamInvalidException;

}
