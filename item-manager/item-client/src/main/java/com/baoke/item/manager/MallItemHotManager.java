package com.baoke.item.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.domain.MallItemHot;

/**
 * 人气热门商品推荐
 * 
 * @author zdy
 * @date: 2018年7月4日 下午7:43:08
 */
public interface MallItemHotManager {
	/**
	 * 查询人气热门商品推荐-分页
	 * 
	 * @author zdy
	 * @date: 2018年7月4日 下午7:47:02
	 * @param mallItemHot
	 * @param pageInfo
	 * @return
	 */
	List<MallItemHot> queryMallItemHotPage(@Param("mallItemHot") MallItemHot mallItemHot,
			@Param("pageInfo") PageInfo pageInfo);

	/**
	 * 查询人气热门商品总条数
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 下午6:30:25
	 */
	int countMallItemHotPage(MallItemHot mallItemHot);

	/**
	 * 新增人气商品
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 下午1:22:53
	 */
	long addMallItemHot(MallItemHot mallItemHot);

	/**
	 * 修改人气商品
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 下午1:29:57
	 */
	int modifyMallItemHot(MallItemHot mallItemHot);

}
