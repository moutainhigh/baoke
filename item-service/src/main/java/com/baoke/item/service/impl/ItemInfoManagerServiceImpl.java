package com.baoke.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.constant.PostageFreeStatusEnum;
import com.baoke.common.dto.ItemListDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.seller.ItemPostageDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.MoneyUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.item.constant.ItemDetailStatusEnum;
import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.item.manager.ItemInfoManager;
import com.baoke.item.service.ItemInfoManagerService;

@Service("itemInfoManagerService")
public class ItemInfoManagerServiceImpl implements ItemInfoManagerService {

	@Resource
	private ItemInfoManager itemInfoManager;

	@Resource
	private ItemDetailInfoManager itemDetailInfoManager;

	@Override
	public ItemListDto queryItemDetailInfoLessTenBySellerId(long sellerId, PageInfo pageInfo)
			throws ParamInvalidException {
		ItemDetailInfo itemDetailInfo = new ItemDetailInfo();
		itemDetailInfo.setSellerId(sellerId);
		itemDetailInfo.setStatus(ItemDetailStatusEnum.ENABLE.getCode());
		List<ItemDetailInfo> ItemDetailInfoList = itemDetailInfoManager
				.queryItemDetailInfoLessTenBySellerId(itemDetailInfo, pageInfo);

		List<ItemInfoDto> itemInfoList = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(ItemDetailInfoList)) {
			for (ItemDetailInfo itemDetailInfoTemp : ItemDetailInfoList) {
				List<ItemDetailInfoDto> itemDetailList = new ArrayList<>();
				itemDetailList.add(itemDetailInfoTemp.convert());

				ItemInfo itemInfo = itemInfoManager.queryItemInfoById(itemDetailInfoTemp.getItemId());
				ItemInfoDto itemInfoDto = itemInfo.convert(null);
				itemInfoDto.setItemDetailList(itemDetailList);

				itemInfoList.add(itemInfoDto);
			}
		}

		int total = itemDetailInfoManager.countItemDetailInfoLessTenBySellerId(itemDetailInfo);
		pageInfo.setTotal(total);

		return new ItemListDto(itemInfoList, pageInfo);
	}

	@Override
	public void modifyItemPostage(ItemPostageDto itemInfoPostDto) throws ParamInvalidException {
		if (itemInfoPostDto == null || itemInfoPostDto.getItemId() == null) {
			throw new ParamInvalidException("商品ID不能为空");
		}

		ItemInfo itemInfo = new ItemInfo(itemInfoPostDto.getItemId());
		if (PostageFreeStatusEnum.FREE.getCode() == itemInfoPostDto.getType()) {
			itemInfo.setPostage(0);
			itemInfo.setAddAreaCodes("");
			itemInfo.setAddAreaPostage(0);
		} else {
			if (null == itemInfoPostDto.getPostage() || itemInfoPostDto.getPostage() < 0) {
				throw new ParamInvalidException("邮费不能小于0");
			}
			itemInfo.setPostage(MoneyUtil.changeY2F(String.valueOf(itemInfoPostDto.getPostage())));
			if (StringUtil.hasLength(itemInfoPostDto.getAppendPostageArea())) {
				itemInfo.setAddAreaCodes(itemInfoPostDto.getAppendPostageArea());
				itemInfo.setAddAreaPostage(MoneyUtil.changeY2F(String.valueOf(itemInfoPostDto.getAppendPostage())));
			}
		}

		itemInfoManager.modifyItemPostage(itemInfo);

	}

	@Override
	public int modifyItemDetailNum(ItemDetailInfoDto itemDetailInfoDto) throws ParamInvalidException {
		if (itemDetailInfoDto == null) {
			throw new ParamInvalidException("商品信息不能为空");
		}
		if (null == itemDetailInfoDto.getItemId()) {
			throw new ParamInvalidException("商品ID不能为空");
		}
		if (null == itemDetailInfoDto.getItemDetailId()) {
			throw new ParamInvalidException("商品详情ID不能为空");
		}
		if (null == itemDetailInfoDto.getTotalNum() && itemDetailInfoDto.getTotalNum() < 0) {
			throw new ParamInvalidException("库存数量不能为空并且不能小于0");
		}
		ItemDetailInfo itemDetailInfo = new ItemDetailInfo();
		itemDetailInfo.setId(itemDetailInfoDto.getItemDetailId());
		itemDetailInfo.setTotalNum(itemDetailInfoDto.getTotalNum());
		itemDetailInfo.setItemId(itemDetailInfoDto.getItemId());
		return itemDetailInfoManager.modifyItemDetailNum(itemDetailInfo);
	}

}
