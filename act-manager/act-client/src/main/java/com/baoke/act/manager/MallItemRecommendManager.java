package com.baoke.act.manager;

import java.util.List;

import com.baoke.act.domain.MallItemRecommend;
import com.baoke.common.dto.base.PageInfo;

/**
 * 商城-明星推荐
 * 
 * @author zdy
 * @date: 2018年7月5日 下午3:48:21
 */
public interface MallItemRecommendManager {
	/**
	 * 商城首页-明星推荐集合
	 * 
	 * @author zdy
	 * @date: 2018年7月5日 下午3:48:51
	 * @param mallItemRecommend
	 * @return
	 */
	List<MallItemRecommend> queryMallItemRecommendList(MallItemRecommend mallItemRecommend);

	/**
	 * 分页查询父标题
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午2:56:27
	 */
	List<MallItemRecommend> queryMallRecommendListByParentIdAndPage(long parentId, PageInfo pageInfo);

	/**
	 * 统计父标题数量
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午2:56:27
	 */
	int countMallRecommendListByParentId(long parentId);

	/**
	 * 根据parentId查询子列表
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午2:56:27
	 */
	List<MallItemRecommend> queryMallRecommendListByParentId(long parentId);

	/**
	 * 新增商城推荐
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午5:09:16
	 */
	Long addMallItemRecommend(MallItemRecommend parentMallItemRecommend);

	/**
	 * 修改商城推荐
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午6:06:44
	 */
	int modifyMallItemRecommend(MallItemRecommend parentMallItemRecommend);

}
