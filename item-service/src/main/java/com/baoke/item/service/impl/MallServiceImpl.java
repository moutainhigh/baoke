package com.baoke.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.act.domain.MallItemRecommend;
import com.baoke.act.manager.MallItemRecommendManager;
import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.MallItemRecommandTittleTypeEnum;
import com.baoke.common.dto.MallRecommendDto;
import com.baoke.common.dto.api.MallHomeDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.MallItemHotDto;
import com.baoke.common.dto.info.MallItemHotInfoDto;
import com.baoke.common.dto.info.MallRecommendInfoDto;
import com.baoke.common.dto.seller.MallItemHotListDto;
import com.baoke.common.dto.seller.MallRecommendListDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.item.domain.MallItemHot;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.item.manager.MallItemHotManager;
import com.baoke.item.service.ItemInfoService;
import com.baoke.item.service.MallService;

@Service("mallService")
@ServiceDefinition("mallService")
public class MallServiceImpl implements MallService {

	private static final Logger logger = Logger.getLogger(MallServiceImpl.class);

	@Resource
	private ItemInfoService itemInfoService;
	@Resource
	private MallItemRecommendManager mallItemRecommendManager;

	@Resource
	private MallItemHotManager mallItemHotManager;

	@Resource
	private ItemDetailInfoManager itemDetailInfoManager;

	private static final int ITEM_HOT = 3;// 热门商品数

	private static final int ITEM_NEW = 30;// 最新商品数

	@MethodDefinition(value = "queryMallHome")
	@Override
	public MallHomeDto queryMallHome(BaseDto baseDto) {
		PageInfo pageInfo = new PageInfo(1, ITEM_HOT);
		List<ItemInfoDto> hotItemList = this.queryItemHotList(pageInfo);

		pageInfo.setPageSize(ITEM_NEW);
		List<ItemInfoDto> newItemList = itemInfoService.queryItemNewList(pageInfo);

		// 查询明星推荐集合
		List<MallRecommendDto> recommendList = this.queryMallRecommendInfoDtoList();

		MallHomeDto mallHomeDto = new MallHomeDto(true, "");
		mallHomeDto.setItemHotList(hotItemList);
		mallHomeDto.setItemNewList(newItemList);
		mallHomeDto.setRecommendList(recommendList);
		return mallHomeDto;
	}

	// 查询明星推荐集合
	private List<MallRecommendDto> queryMallRecommendInfoDtoList() {
		List<MallRecommendDto> mallRecommendInfoList = new ArrayList<>();
		// 查询标题集合
		MallItemRecommend mallItemRecommend = new MallItemRecommend(0L, BooleanEnum.TRUE.getCode());
		List<MallItemRecommend> mallRecommendTitleList = mallItemRecommendManager
				.queryMallItemRecommendList(mallItemRecommend);

		// 查询图片集合
		for (MallItemRecommend mallItemRecommendObj : mallRecommendTitleList) {
			mallItemRecommend.setParentId(mallItemRecommendObj.getId());
			List<MallItemRecommend> mallItemRecommendList = mallItemRecommendManager
					.queryMallItemRecommendList(mallItemRecommend);
			MallRecommendDto mallRecommendInfoDto = new MallRecommendDto();

			List<MallRecommendInfoDto> mallRecommendInfoDtoList = this
					.convertBannerConfigDtoList(mallItemRecommendList);
			mallRecommendInfoDto.setMallRecommendList(mallRecommendInfoDtoList);
			mallRecommendInfoDto.setTitle(mallItemRecommendObj.getTitle());
			mallRecommendInfoList.add(mallRecommendInfoDto);
		}
		return mallRecommendInfoList;
	}

	// 获取BannerConfigDtoList
	private List<MallRecommendInfoDto> convertBannerConfigDtoList(List<MallItemRecommend> mallItemRecommendList) {
		List<MallRecommendInfoDto> bannerList = new ArrayList<>();
		for (MallItemRecommend mallItemRecommend : mallItemRecommendList) {
			MallRecommendInfoDto mallItemRecommendDto = new MallRecommendInfoDto();
			mallItemRecommendDto.setImgUrl(mallItemRecommend.getImgUrl());
			mallItemRecommendDto.setTargetUrl(mallItemRecommend.getTargetUrl());
			mallItemRecommendDto.setDispatchType(mallItemRecommend.getDispatchType());
			mallItemRecommendDto.setSort(mallItemRecommend.getSort());
			bannerList.add(mallItemRecommendDto);
		}
		return bannerList;
	}

	@Override
	public List<ItemInfoDto> queryItemHotList(PageInfo pageInfo) {

		List<MallItemHot> mallItemHotList = mallItemHotManager.queryMallItemHotPage(new MallItemHot(BooleanEnum.TRUE),
				pageInfo);

		List<ItemInfoDto> itemInfoList = new ArrayList<>();
		for (MallItemHot mallItemHot : mallItemHotList) {
			ItemInfoDto itemInfoDto = itemInfoService.queryItemInfoByItemId(mallItemHot.getItemId());
			if (null != itemInfoDto) {
				itemInfoList.add(itemInfoDto);
			} else {
				logger.warn("query item hot error,item is empty. itemId=" + mallItemHot.getItemId());
			}
		}
		return itemInfoList;
	}

	@Override
	public MallRecommendListDto queryItemRecommendListByPage(PageInfo pageInfo) {
		// 查询父标题
		int total = mallItemRecommendManager
				.countMallRecommendListByParentId(MallItemRecommandTittleTypeEnum.PARENT.getCode());
		pageInfo.setTotal(total);
		List<MallItemRecommend> parentMallItemRecommendList = mallItemRecommendManager
				.queryMallRecommendListByParentIdAndPage(MallItemRecommandTittleTypeEnum.PARENT.getCode(), pageInfo);
		List<MallRecommendDto> mallRecommendDtoList = new ArrayList<>();
		for (MallItemRecommend mallItemRecommend : parentMallItemRecommendList) {
			List<MallItemRecommend> subMallItemRecommendList = mallItemRecommendManager
					.queryMallRecommendListByParentId(mallItemRecommend.getId());

			List<MallRecommendInfoDto> mallRecommendInfoDtoList = new ArrayList<>();
			for (MallItemRecommend subMallItemRecommend : subMallItemRecommendList) {
				if (subMallItemRecommend != null) {
					MallRecommendInfoDto mallRecommendInfoDto = subMallItemRecommend.convert();
					mallRecommendInfoDto.setMallItemRecommendId(subMallItemRecommend.getId());
					mallRecommendInfoDtoList.add(mallRecommendInfoDto);
				}
			}
			MallRecommendDto mallRecommendDto = new MallRecommendDto(mallRecommendInfoDtoList,
					mallItemRecommend.getTitle());
			mallRecommendDtoList.add(mallRecommendDto);
		}
		MallRecommendListDto mallRecommendListDto = new MallRecommendListDto();
		mallRecommendListDto.setMallRecommendList(mallRecommendDtoList);
		mallRecommendListDto.setPageInfo(pageInfo);
		return mallRecommendListDto;
	}

	@Override
	public MallItemHotListDto queryItemHotListByPage(PageInfo pageInfo) throws MainException {
		int total = mallItemHotManager.countMallItemHotPage(new MallItemHot(BooleanEnum.TRUE));
		pageInfo.setTotal(total);
		List<MallItemHot> mallItemHotList = mallItemHotManager.queryMallItemHotPage(new MallItemHot(BooleanEnum.TRUE),
				pageInfo);
		if (mallItemHotList == null || mallItemHotList.size() < 1) {
			logger.warn("query item hot list by page error, item is empty.");
			return null;
		}
		List<MallItemHotDto> mallItemHotDtos = new ArrayList<>();

		for (MallItemHot mallItemHot : mallItemHotList) {
			ItemInfoDto itemInfoDto = itemInfoService.queryItemInfoByItemId(mallItemHot.getItemId());
			if (null == itemInfoDto) {
				logger.warn("query item hot list by page error, item is empty. itemId=" + mallItemHot.getItemId());
			}

			MallItemHotDto mallItemHotDto = new MallItemHotDto();
			mallItemHotDto.setMallItemHotId(mallItemHot.getId());
			mallItemHotDto.setItemInfo(itemInfoDto);
			mallItemHotDtos.add(mallItemHotDto);
		}
		MallItemHotListDto mallItemHotListDto = new MallItemHotListDto();
		mallItemHotListDto.setMallItemHotList(mallItemHotDtos);
		mallItemHotListDto.setPageInfo(pageInfo);
		return mallItemHotListDto;
	}

	@Override
	public void saveHotRecommendItemInfo(MallItemHotInfoDto mallItemHotInfoDto) {

		if (mallItemHotInfoDto.getMallItemHotId() == null) {
			// 新增热门商品
			MallItemHot mallItemHot = new MallItemHot(mallItemHotInfoDto.getItemId(), mallItemHotInfoDto.getSort(),
					mallItemHotInfoDto.getStatus());
			mallItemHotManager.addMallItemHot(mallItemHot);
			logger.info("add mall recommend item hot success, " + mallItemHot);
		} else {
			// 修改热门商品
			MallItemHot mallItemHot = new MallItemHot(mallItemHotInfoDto.getItemId(), mallItemHotInfoDto.getSort(),
					mallItemHotInfoDto.getStatus());
			mallItemHot.setId(mallItemHotInfoDto.getMallItemHotId());
			mallItemHotManager.modifyMallItemHot(mallItemHot);
			logger.info("modify mall recommend item hot success, " + mallItemHot);
		}
	}
}
