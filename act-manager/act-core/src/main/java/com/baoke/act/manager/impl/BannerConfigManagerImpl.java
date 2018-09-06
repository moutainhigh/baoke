package com.baoke.act.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.act.dao.BannerConfigDao;
import com.baoke.act.domain.BannerConfig;
import com.baoke.act.manager.BannerConfigManager;
import com.baoke.common.constant.BannerScenTypeEnum;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;

/**
 * Banner 配置 ManagerImpl
 * 
 * @author zdy
 * @date: 2018年6月5日 下午5:14:48
 */
@Component
@DataSource("miscDataSource")
public class BannerConfigManagerImpl implements BannerConfigManager {

	@Resource
	private BannerConfigDao bannerConfigDao;

	@Override
	public List<BannerConfig> queryBannerConfigByStatusAndScenType(BannerScenTypeEnum bannerScenTypeEnum,
			BooleanEnum booleanEnum) {
		return bannerConfigDao.queryBannerConfigByStatusAndScenType(booleanEnum.getCode(),
				bannerScenTypeEnum.getName());
	}

	@Override
	public List<BannerConfig> queryBannerConfigByStatusAndScenTypePage(String scenType, Integer status,
			PageInfo pageInfo) {
		return bannerConfigDao.queryBannerConfigByStatusAndScenTypePage(scenType, status, pageInfo);
	}

	@Override
	public int countBannerConfigByStatusAndScenType(Integer status, String scenType) {
		return bannerConfigDao.countBannerConfigByStatusAndScenType(status, scenType);
	}

	@Override
	public int modifyBannerConfigStatusExceptId(Integer status, Long id, String scenType) {
		return bannerConfigDao.modifyBannerConfigStatusExceptId(status, id, scenType);
	}

	@Override
	public int addBannerConfig(BannerConfig bannerConfig) {
		return bannerConfigDao.addBannerConfig(bannerConfig);
	}

	@Override
	public int modifyBannerConfigById(BannerConfig bannerConfig) {
		return bannerConfigDao.modifyBannerConfigById(bannerConfig);
	}

}
