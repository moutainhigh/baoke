package com.baoke.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.domain.ItemInfo;

public interface ItemInfoDao {

	ItemInfo queryItemInfoById(long id);

	ItemInfo queryItemInfoByIdWithStatus(@Param("id") long id, @Param("status") int status);

	List<ItemInfo> queryItemInfoListByPage(@Param("itemInfo") ItemInfo itemInfo, @Param("pageInfo") PageInfo pageInfo);

	List<ItemInfo> queryItemInfoList(ItemInfo itemInfo);

	int countItemInfo(ItemInfo itemInfo);

	int countItemInfoByStatuses(@Param("itemInfo") ItemInfo itemInfo, @Param("statuses") List<Integer> statuses);

	List<ItemInfo> queryItemInfoListByPageAndStatuses(@Param("itemInfo") ItemInfo itemInfo,
			@Param("statuses") List<Integer> statuses, @Param("pageInfo") PageInfo pageInfo);

	int addItemInfo(ItemInfo itemInfo);

	int modifyItemPostage(ItemInfo itemInfo);

	int modifyItemStatusById(@Param("id") Long id, @Param("status") Integer status);

	int modifyItemByItemIds(@Param("itemIds") List<Long> itemIds, @Param("itemInfo") ItemInfo itemInfo);

	int modifyItemByitemId(ItemInfo itemInfo);

}
