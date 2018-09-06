package com.baoke.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.dto.ItemDetailDto;
import com.baoke.common.dto.ItemDto;
import com.baoke.common.dto.ItemListDto;
import com.baoke.common.dto.SellerItemListDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.domain.SellerFocus;
import com.baoke.interact.domain.VideoGreat;
import com.baoke.interact.manager.SellerFocusManager;
import com.baoke.interact.manager.VideoGreatManager;
import com.baoke.item.constant.ItemDetailStatusEnum;
import com.baoke.item.constant.ItemStatusEnum;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.domain.VideoItemRelation;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.item.manager.ItemInfoManager;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.item.manager.VideoItemRelationManager;
import com.baoke.item.service.ItemInfoService;
import com.baoke.item.service.VideoInfoService;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.AreaDictManager;
import com.baoke.user.manager.UserInfoManager;

@Service("itemInfoService")
@ServiceDefinition("itemInfoService")
public class ItemInfoServiceImpl implements ItemInfoService {

	private static final Logger logger = Logger.getLogger(ItemInfoServiceImpl.class);

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private ItemInfoManager itemInfoManager;

	@Resource
	private ItemDetailInfoManager itemDetailInfoManager;

	@Resource
	private VideoItemRelationManager videoItemRelationManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private SellerFocusManager sellerFocusManager;

	@Resource
	private VideoGreatManager videoGreatManager;

	@Resource
	private AreaDictManager areaDictManager;

	@Resource
	private VideoInfoService videoInfoService;

	@Override
	public ItemInfoDto queryItemInfoByItemId(long itemId) {

		ItemInfo itemInfo = itemInfoManager.queryItemInfoById(itemId);
		if (itemInfo == null) {
			logger.warn("query item info error, itemInfo not found. itemId=" + itemId);
			return null;
		}

		List<ItemDetailInfo> itemDetailInfoList = itemDetailInfoManager
				.queryItemDetailInfoList(new ItemDetailInfo(itemId, ItemDetailStatusEnum.ENABLE));
		return itemInfo.convert(itemDetailInfoList);
	}

	@MethodDefinition(value = "queryItemDetail")
	@Override
	public ItemDetailDto queryItemByItemId(ItemInfoDto itemInfoDto) throws MainException {
		if (null == itemInfoDto || null == itemInfoDto.getItemId()) {
			throw new ParamInvalidException("商品ID不能为空");
		}

		ItemInfoDto itemInfoDtoResult = this.queryItemInfoByItemId(itemInfoDto.getItemId());
		if (null == itemInfoDtoResult || ItemStatusEnum.DOWNLINE.getCode() == itemInfoDtoResult.getStatus()) {
			return new ItemDetailDto(false, "商品已下架");
		} else {
			if (ItemStatusEnum.ONLINE.getCode() != itemInfoDtoResult.getStatus()) {
				return new ItemDetailDto(false, "该商品暂时无法查看");
			}
		}

		// video list
		List<VideoItemRelation> relationsList = videoItemRelationManager.queryVideoItemRelationList(
				new VideoItemRelation(null, itemInfoDtoResult.getSellerId(), itemInfoDtoResult.getItemId()));

		List<VideoInfoDto> videoInfoList = new ArrayList<VideoInfoDto>();
		if (CollectionUtil.isNotEmpty(relationsList)) {
			for (VideoItemRelation videoItemRelation : relationsList) {
				VideoInfo videoInfo = videoInfoManager.queryVideoInfoByIdAndStatus(videoItemRelation.getVideoId(),
						VideoStatusEnum.ONLINE);
				if (null != videoInfo) {
					VideoInfoDto videoInfoDto = videoInfoService.queryVideoInfoDto(videoInfo, itemInfoDto.getUserId());
					videoInfoList.add(videoInfoDto);
				}
			}
		}

		SellerInfoDto sellerInfoDto = this.querySellerInfoById(new SellerInfoDto(itemInfoDtoResult.getSellerId()),
				null);

		return new ItemDetailDto(true, "", itemInfoDtoResult, sellerInfoDto, videoInfoList);
	}

	@MethodDefinition(value = "querySellerItem")
	@Override
	public SellerItemListDto queryItemListBySellerId(SellerInfoDto sellerInfoDto) throws ParamInvalidException {
		if (null == sellerInfoDto || null == sellerInfoDto.getSellerId()) {
			throw new ParamInvalidException("播主ID不能为空");
		}

		// 查询该主播下所有商品列表
		ItemInfo itemInfoTemp = new ItemInfo(sellerInfoDto.getSellerId(), ItemStatusEnum.ONLINE);
		List<ItemInfo> itemInfoList = itemInfoManager.queryItemInfoListByPage(itemInfoTemp,
				sellerInfoDto.getPageInfo());
		List<ItemInfoDto> itemInfoDtoList = getItemInfoDtoList(itemInfoList);

		return new SellerItemListDto(true, "", itemInfoDtoList);
	}

	@Override
	public List<ItemInfoDto> queryItemNewList(PageInfo pageInfo) {

		List<ItemInfo> itemList = itemInfoManager.queryItemInfoListByPage(new ItemInfo(ItemStatusEnum.ONLINE),
				pageInfo);

		return getItemInfoDtoList(itemList);
	}

	private List<ItemInfoDto> getItemInfoDtoList(List<ItemInfo> itemList) {
		List<ItemInfoDto> itemInfoDtoList = new ArrayList<ItemInfoDto>();
		for (ItemInfo itemInfo : itemList) {
			ItemDetailInfo itemDetailInfo = new ItemDetailInfo(itemInfo.getId(), ItemDetailStatusEnum.ENABLE);
			List<ItemDetailInfo> itemDetailInfoList = itemDetailInfoManager.queryItemDetailInfoList(itemDetailInfo);
			itemInfoDtoList.add(itemInfo.convert(itemDetailInfoList));
		}
		return itemInfoDtoList;
	}

	@Override
	public ItemListDto queryItemInfoListByPage(ItemDto itemDto) throws ParamInvalidException {

		if (null == itemDto || null == itemDto.getItemInfo()) {
			throw new ParamInvalidException("商品信息不能为空");
		}
		ItemInfoDto itemInfoDtoRequest = itemDto.getItemInfo();
		Integer status = itemInfoDtoRequest.getStatus();
		ItemInfo itemInfoTemp = new ItemInfo(itemInfoDtoRequest.getUserId(), itemInfoDtoRequest.getTitle(),
				null == status ? null : ItemStatusEnum.getByCode(status));

		int count = itemInfoManager.countItemInfoByStatuses(itemInfoTemp, itemDto.getStatuses());
		itemDto.getPageInfo().setTotal(count);

		List<ItemInfo> itemInfoList = itemInfoManager.queryItemInfoListByPageAndStatuses(itemInfoTemp,
				itemDto.getStatuses(), itemDto.getPageInfo());

		List<ItemInfoDto> itemInfoDtoList = new ArrayList<ItemInfoDto>();
		for (ItemInfo itemInfo : itemInfoList) {
			List<ItemDetailInfo> itemDetailInfoList = itemDetailInfoManager
					.queryItemDetailInfoList(new ItemDetailInfo(itemInfo.getId(), ItemDetailStatusEnum.ENABLE));
			// 查询商品关联上线视频数量
			List<VideoItemRelation> videoItemRelationList = videoItemRelationManager
					.queryVideoItemRelationList(new VideoItemRelation(null, itemInfo.getId()));
			List<Long> videoIds = new ArrayList<>();
			for (VideoItemRelation videoItemRelation : videoItemRelationList) {
				videoIds.add(videoItemRelation.getVideoId());
			}
			int videoCount = 0;
			if (CollectionUtil.isNotEmpty(videoIds)) {
				videoCount = videoInfoManager.countVideoInfoListByVideoIds(videoIds, VideoStatusEnum.ONLINE);
			}
			ItemInfoDto itemInfoDto = itemInfo.convert(itemDetailInfoList);

			itemInfoDto.setVideoNum(videoCount);
			itemInfoDtoList.add(itemInfoDto);
		}

		return new ItemListDto(itemInfoDtoList, itemDto.getPageInfo());
	}

	private SellerInfoDto querySellerInfoById(SellerInfoDto sellerInfoDto, UserInfo userInfo) throws MainException {
		if (null == sellerInfoDto || null == sellerInfoDto.getSellerId()) {
			throw new ParamInvalidException();
		}

		int beFocusNum = sellerFocusManager
				.countSellerFocus(new SellerFocus(null, SellerFocusStatusEnum.FOCUS, sellerInfoDto.getSellerId()));

		int videoNum = videoItemRelationManager.countVideoItemRelationBySellerId(sellerInfoDto.getSellerId());

		VideoGreat videoGreat = new VideoGreat(GreatStatusEnum.GREAT, sellerInfoDto.getSellerId(), null);
		int beGreatNum = videoGreatManager.countVideoGreatNum(videoGreat);

		int isFocus = 0;
		if (sellerInfoDto.getUserId() != null && sellerInfoDto.getUserId() != 0) {
			isFocus = sellerFocusManager.querySellerFocusStatus(sellerInfoDto.getUserId(), sellerInfoDto.getSellerId());
		}

		if (null == userInfo) {
			userInfo = userInfoManager.queryUserInfo(new UserInfo(sellerInfoDto.getSellerId(), UserStatusEnum.NORMAL));
		}
		if (null != userInfo) {
			sellerInfoDto.setSellerImgUrl(userInfo.getHeadImgUrl());
			sellerInfoDto.setSellerNickName(userInfo.getNickName());
		}
		sellerInfoDto.setBeFocusNum(beFocusNum);
		sellerInfoDto.setVideoNum(videoNum);
		sellerInfoDto.setBeGreatNum(beGreatNum);
		sellerInfoDto.setIsFocus(isFocus);

		return sellerInfoDto;
	}

}
