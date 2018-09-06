package com.baoke.act.manager;

import java.util.List;

import com.baoke.act.domain.BannerConfig;
import com.baoke.common.constant.BannerScenTypeEnum;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.base.PageInfo;

/**
 * Banner 配置 Manager
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:02:15
 */
public interface BannerConfigManager {

	/**
	 * 根据状态和场景查询
	 * 
	 * @author zjm
	 * @date: 2018年7月17日 下午5:30:17
	 * @param bannerScenTypeEnum
	 * @param booleanEnum
	 * @return
	 */
	List<BannerConfig> queryBannerConfigByStatusAndScenType(BannerScenTypeEnum bannerScenTypeEnum,
			BooleanEnum booleanEnum);

	/**
	 * 根据状态和场景分页查询
	 * 
	 * @author zjm
	 * @date: 2018年7月17日 下午5:30:36
	 * @param scenType
	 * @param status
	 * @param pageInfo
	 * @return
	 */
	List<BannerConfig> queryBannerConfigByStatusAndScenTypePage(String scenType, Integer status, PageInfo pageInfo);

	/**
	 * 根据状态和场景查询总数
	 * 
	 * @author zjm
	 * @date: 2018年7月17日 下午5:30:50
	 * @param status
	 * @param scenType
	 * @return
	 */
	int countBannerConfigByStatusAndScenType(Integer status, String scenType);

	/**
	 * 修改除了传值id以外的banner的状态值
	 * 
	 * @author zjm
	 * @date: 2018年7月17日 下午5:28:48
	 * @param bannerConfig
	 * @return
	 */
	int modifyBannerConfigStatusExceptId(Integer status, Long id, String scenType);

	/**
	 * 新增banner
	 * 
	 * @author zjm
	 * @date: 2018年7月17日 下午5:31:09
	 * @param bannerConfig
	 * @return
	 */
	int addBannerConfig(BannerConfig bannerConfig);

	/**
	 * 根据id修改banner
	 * 
	 * @author zjm
	 * @date: 2018年7月17日 下午5:31:15
	 * @param bannerConfig
	 * @return
	 */
	int modifyBannerConfigById(BannerConfig bannerConfig);
}
