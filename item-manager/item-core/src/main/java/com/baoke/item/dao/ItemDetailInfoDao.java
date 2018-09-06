package com.baoke.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.domain.ItemDetailInfo;

public interface ItemDetailInfoDao {

	ItemDetailInfo queryItemDetailInfoById(long id);

	List<ItemDetailInfo> queryItemDetailInfoByItemId(long itemId);

	List<ItemDetailInfo> queryItemDetailInfoList(ItemDetailInfo itemDetailInfo);

	List<ItemDetailInfo> queryItemDetailInfoLessTenBySellerId(@Param("itemDetailInfo") ItemDetailInfo itemDetailInfo,
			@Param("pageInfo") PageInfo pageInfo);

	int countItemDetailInfoLessTenBySellerId(ItemDetailInfo itemDetailInfo);

	int modifyItemDetailNum(ItemDetailInfo itemDetailInfo);

	int modifyItemTotalAndSaleNum(ItemDetailInfo itemDetailInfo);

	Long addItemDetailInfo(ItemDetailInfo itemDetailInfo);

}
