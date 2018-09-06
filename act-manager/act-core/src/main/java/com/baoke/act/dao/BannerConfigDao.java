package com.baoke.act.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.act.domain.BannerConfig;
import com.baoke.common.dto.base.PageInfo;

/**
 * banner配置Dao date: 2018年6月5日
 * 
 * @author zdy
 * @version
 */
public interface BannerConfigDao {
	List<BannerConfig> queryBannerConfigByStatusAndScenType(@Param("status") Integer status,
			@Param("scenType") String scenType);

	List<BannerConfig> queryBannerConfigByStatusAndScenTypePage(@Param("scenType") String scenType,
			@Param("status") Integer status, @Param("pageInfo") PageInfo pageInfo);

	int countBannerConfigByStatusAndScenType(@Param("status") Integer status, @Param("scenType") String scenType);

	int modifyBannerConfigStatusExceptId(@Param("status") Integer status, @Param("id") Long id,
			@Param("scenType") String scenType);

	int addBannerConfig(BannerConfig bannerConfig);

	int modifyBannerConfigById(BannerConfig bannerConfig);
}
