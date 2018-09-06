package com.baoke.item.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.dao.MallItemHotDao;
import com.baoke.item.domain.MallItemHot;
import com.baoke.item.manager.MallItemHotManager;

/**
 * 人气热门商品推荐Impl
 * 
 * @author zdy
 * @date: 2018年7月4日 下午7:46:20
 */
@Component
@DataSource("miscDataSource")
public class MallItemHotManagerImpl implements MallItemHotManager {
	@Resource
	private MallItemHotDao mallItemHotDao;

	@Override
	public List<MallItemHot> queryMallItemHotPage(MallItemHot mallItemHot, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return mallItemHotDao.queryMallItemHotPage(mallItemHot, pageInfo);
	}

	@Override
	public int countMallItemHotPage(MallItemHot mallItemHot) {
		return mallItemHotDao.countMallItemHotPage(mallItemHot);
	}

	@Override
	public long addMallItemHot(MallItemHot mallItemHot) {
		mallItemHotDao.addMallItemHot(mallItemHot);
		return mallItemHot.getId();
	}

	@Override
	public int modifyMallItemHot(MallItemHot mallItemHot) {
		return mallItemHotDao.modifyMallItemHot(mallItemHot);
	}

}
