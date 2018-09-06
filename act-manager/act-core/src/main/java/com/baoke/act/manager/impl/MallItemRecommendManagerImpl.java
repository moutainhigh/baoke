package com.baoke.act.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.act.dao.MallItemRecommendDao;
import com.baoke.act.domain.MallItemRecommend;
import com.baoke.act.manager.MallItemRecommendManager;
import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;

/**
 * 商城-明星推荐ManagerImpl
 * 
 * @author zdy
 * @date: 2018年7月5日 下午3:50:55
 */
@Component
@DataSource("miscDataSource")
public class MallItemRecommendManagerImpl implements MallItemRecommendManager {
	@Resource
	private MallItemRecommendDao mallItemRecommendDao;

	@Override
	public List<MallItemRecommend> queryMallItemRecommendList(MallItemRecommend mallItemRecommend) {
		return mallItemRecommendDao.queryMallItemRecommendList(mallItemRecommend);
	}

	@Override
	public List<MallItemRecommend> queryMallRecommendListByParentIdAndPage(long parentId, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return mallItemRecommendDao.queryMallRecommendListByParentIdAndPage(parentId, pageInfo);
	}

	@Override
	public int countMallRecommendListByParentId(long parentId) {
		return mallItemRecommendDao.countMallRecommendListByParentId(parentId);
	}

	@Override
	public List<MallItemRecommend> queryMallRecommendListByParentId(long parentId) {
		return mallItemRecommendDao.queryMallRecommendListByParentId(parentId);
	}

	@Override
	public Long addMallItemRecommend(MallItemRecommend mallItemRecommend) {
		mallItemRecommendDao.addMallItemRecommend(mallItemRecommend);
		return mallItemRecommend.getId();
	}

	@Override
	public int modifyMallItemRecommend(MallItemRecommend mallItemRecommend) {
		return mallItemRecommendDao.modifyMallItemRecommend(mallItemRecommend);
	}

}
