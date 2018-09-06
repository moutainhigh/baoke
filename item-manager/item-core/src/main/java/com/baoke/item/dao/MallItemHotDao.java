package com.baoke.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.domain.MallItemHot;

public interface MallItemHotDao {
	List<MallItemHot> queryMallItemHotPage(@Param("mallItemHot") MallItemHot mallItemHot,
			@Param("pageInfo") PageInfo pageInfo);

	int countMallItemHotPage(MallItemHot mallItemHot);

	int addMallItemHot(MallItemHot mallItemHot);

	int modifyMallItemHot(MallItemHot mallItemHot);

}
