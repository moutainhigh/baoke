package com.baoke.item.service;

import java.util.List;

import com.baoke.common.dto.api.MallHomeDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.MallItemHotInfoDto;
import com.baoke.common.dto.seller.MallItemHotListDto;
import com.baoke.common.dto.seller.MallRecommendListDto;
import com.baoke.common.exception.base.MainException;

public interface MallService {
	/**
	 * 商城－查询商城首页
	 * 
	 * @author zdy
	 * @date: 2018年7月4日 下午4:47:56
	 * @return
	 */
	MallHomeDto queryMallHome(BaseDto baseDto);

	/**
	 * 商城-人气热门商品信息
	 * 
	 * @author zdy
	 * @date: 2018年7月4日 下午5:26:51
	 * @return
	 */
	List<ItemInfoDto> queryItemHotList(PageInfo pageInfo);

	/**
	 * 分页查询热门商品
	 * 
	 * @author: wyj
	 * @throws MainException
	 * @date: 2018年7月11日 上午9:59:41
	 */
	MallItemHotListDto queryItemHotListByPage(PageInfo pageInfo) throws MainException;

	/**
	 * 商城-明星推荐列表(分页)
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午1:55:01
	 */
	MallRecommendListDto queryItemRecommendListByPage(PageInfo pageInfo);

	/**
	 * 保存人气商品
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 上午11:15:04
	 */
	void saveHotRecommendItemInfo(MallItemHotInfoDto mallItemHotInfoDto);

}
