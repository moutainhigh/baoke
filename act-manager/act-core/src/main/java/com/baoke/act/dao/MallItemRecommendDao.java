package com.baoke.act.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.act.domain.MallItemRecommend;
import com.baoke.common.dto.base.PageInfo;

public interface MallItemRecommendDao {
	List<MallItemRecommend> queryMallItemRecommendList(MallItemRecommend mallItemRecommend);

	List<MallItemRecommend> queryMallRecommendListByParentIdAndPage(@Param("parentId") long parentId,
			@Param("pageInfo") PageInfo pageInfo);

	int countMallRecommendListByParentId(long parentId);

	List<MallItemRecommend> queryMallRecommendListByParentId(long parentId);

	Long addMallItemRecommend(MallItemRecommend parentMallItemRecommend);

	int modifyMallItemRecommend(MallItemRecommend mallItemRecommend);

}
