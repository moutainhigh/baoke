package com.baoke.act.service;

import java.text.ParseException;

import com.baoke.common.dto.BannerDto;
import com.baoke.common.dto.BannerListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.BannerConfigDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;

/**
 * banner配置Service
 * 
 * @author zdy
 * @date: 2018年7月10日 下午2:53:50
 */
public interface BannerConfigService {
	/**
	 * 查询启动页配置信息
	 * 
	 * @author zdy
	 * @date: 2018年7月10日 下午3:08:18
	 * @param baseDto
	 * @return
	 */
	BannerConfigDto queryStartPageInfo(BaseDto baseDto);

	/**
	 * 
	 * banner列表
	 * 
	 * @author ljj
	 * @date: 2018年7月10日 上午10:56:20
	 * @param bannerDto
	 * @return
	 * @throws MainException
	 * @throws ParseException
	 */
	BannerListDto queryBannerConfigByStatusOrScenType(BannerDto bannerDto) throws MainException, ParseException;

	/**
	 * 保存banner的信息
	 * 
	 * @author ljj
	 * @date: 2018年7月10日 上午10:56:38
	 * @param bannerConfigDto
	 * @return
	 * @throws ParseException
	 * @throws MainException
	 */
	int saveBannerConfig(BannerConfigDto bannerConfigDto) throws ParamInvalidException, ParseException;
}
