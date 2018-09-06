package com.baoke.act.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.act.domain.BannerConfig;
import com.baoke.act.manager.BannerConfigManager;
import com.baoke.act.service.BannerConfigService;
import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BannerScenTypeEnum;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.dto.BannerDto;
import com.baoke.common.dto.BannerListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.BannerConfigDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.DateUtil;

/**
 * banner配置服务
 * 
 * @author zdy
 * @date: 2018年7月10日 下午3:08:01
 */
@Service("bannerConfigService")
@ServiceDefinition("bannerConfigService")
public class BannerConfigServiceImpl implements BannerConfigService {
	@Resource
	private BannerConfigManager bannerConfigManager;

	@MethodDefinition(value = "queryStartPageInfo")
	@Override
	public BannerConfigDto queryStartPageInfo(BaseDto baseDto) {
		List<BannerConfig> bannerInfoList = bannerConfigManager
				.queryBannerConfigByStatusAndScenType(BannerScenTypeEnum.APP_STARTPAGE_ADVERT, BooleanEnum.TRUE);

		BannerConfigDto bannerConfigDto = new BannerConfigDto();
		// 返回值转换
		if (bannerInfoList != null && bannerInfoList.size() > 0) {
			BannerConfig bannerConfig = bannerInfoList.get(0);
			bannerConfigDto = bannerConfig.convert();
		}

		return bannerConfigDto;
	}

	@Override
	public BannerListDto queryBannerConfigByStatusOrScenType(BannerDto bannerDto) throws MainException, ParseException {
		List<BannerConfig> BannerConfigList = bannerConfigManager.queryBannerConfigByStatusAndScenTypePage(
				bannerDto.getBannerConfigDto().getScenType(), bannerDto.getBannerConfigDto().getStatus(),
				bannerDto.getPageInfo());

		List<BannerConfigDto> bannerConfigDtoList = new ArrayList<>();
		if (BannerConfigList != null && BannerConfigList.size() > 0) {
			for (BannerConfig bannerConfig : BannerConfigList) {
				BannerConfigDto bannerConfigResultDto = bannerConfig.convert();
				bannerConfigResultDto.setEndTime(bannerConfig.getEndTime().getTime() == DateUtil
						.parse(CommonConfig.DEFAULT_END_DATE, DateUtil.DATE_TIME_FORMAT_STR).getTime() ? null
								: bannerConfig.getEndTime().getTime());
				bannerConfigDtoList.add(bannerConfigResultDto);
			}
		}
		BannerListDto bannerListDto = new BannerListDto();
		int totalNum = bannerConfigManager.countBannerConfigByStatusAndScenType(
				bannerDto.getBannerConfigDto().getStatus(), bannerDto.getBannerConfigDto().getScenType());
		bannerDto.getPageInfo().setTotal(totalNum);
		bannerListDto.setBannerConfigList(bannerConfigDtoList);
		bannerListDto.setPageInfo(bannerDto.getPageInfo());
		return bannerListDto;
	}

	@Override
	public int saveBannerConfig(BannerConfigDto bannerConfigDto) throws ParamInvalidException, ParseException {

		if (bannerConfigDto == null || bannerConfigDto.getImgUrl() == null || bannerConfigDto.getScenType() == null) {
			throw new ParamInvalidException("图片,场景类型,不能为空");
		}

		BannerConfig bannerConfig = new BannerConfig();
		bannerConfig.setId(bannerConfigDto.getBannerId());
		bannerConfig.setImgUrl(bannerConfigDto.getImgUrl());
		bannerConfig.setScenType(bannerConfigDto.getScenType());
		bannerConfig.setDispatchType(bannerConfigDto.getDispatchType());
		bannerConfig.setTargetUrl(bannerConfigDto.getTargetUrl());
		bannerConfig.setSort(bannerConfigDto.getSort());
		bannerConfig.setStatus(bannerConfigDto.getStatus());

		Date startDate = null == bannerConfigDto.getStartTime() ? new Date() : new Date(bannerConfigDto.getStartTime());
		bannerConfig.setStartTime(startDate);
		Date endDate = null == bannerConfigDto.getEndTime()
				? DateUtil.parse(CommonConfig.DEFAULT_END_DATE, DateUtil.DATE_TIME_FORMAT_STR)
				: new Date(bannerConfigDto.getEndTime());
		bannerConfig.setEndTime(endDate);
		if (bannerConfig.getId() == null) {
			bannerConfig.setCreateUserId(bannerConfigDto.getUserId());
			bannerConfig.setModifyUserId(bannerConfigDto.getUserId());
			bannerConfig.setStatus(BooleanEnum.FALSE.getCode());
			return bannerConfigManager.addBannerConfig(bannerConfig);
		} else {
			bannerConfig.setModifyUserId(bannerConfigDto.getUserId());

			if (bannerConfigDto.getStatus() == BooleanEnum.TRUE.getCode()) {
				bannerConfigManager.modifyBannerConfigStatusExceptId(BooleanEnum.FALSE.getCode(),
						bannerConfigDto.getBannerId(), bannerConfigDto.getScenType());
			}

			return bannerConfigManager.modifyBannerConfigById(bannerConfig);
		}
	}

}
