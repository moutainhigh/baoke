package com.baoke.item.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.constant.ItemStatusEnum;
import com.baoke.item.dao.ItemInfoDao;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.manager.ItemInfoManager;

/**
 * 商品ManagerImpl
 * 
 * @author zdy
 * @date: 2018年6月13日 下午1:53:56
 */
@Component
@DataSource("coreDataSource")
public class ItemInfoManagerImpl implements ItemInfoManager {
	@Resource
	private ItemInfoDao itemInfoDao;

	@Override
	public ItemInfo queryItemInfoById(long id) {
		return itemInfoDao.queryItemInfoById(id);
	}

	@Override
	public ItemInfo queryItemInfoByIdWithStatus(long id, ItemStatusEnum itemStatusEnum) {
		return itemInfoDao.queryItemInfoByIdWithStatus(id, itemStatusEnum.getCode());
	}

	@Override
	public List<ItemInfo> queryItemInfoListByPage(ItemInfo itemInfo, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return itemInfoDao.queryItemInfoListByPage(itemInfo, pageInfo);
	}

	@Override
	public List<ItemInfo> queryItemInfoList(ItemInfo itemInfo) {
		return itemInfoDao.queryItemInfoList(itemInfo);
	}

	@Override
	public int countItemInfo(ItemInfo itemInfo) {
		return itemInfoDao.countItemInfo(itemInfo);
	}

	@Override
	public int countItemInfoByStatuses(ItemInfo itemInfo, List<Integer> statuses) {
		return itemInfoDao.countItemInfoByStatuses(itemInfo, statuses);
	}

	@Override
	public List<ItemInfo> queryItemInfoListByPageAndStatuses(ItemInfo itemInfo, List<Integer> statuses,
			PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return itemInfoDao.queryItemInfoListByPageAndStatuses(itemInfo, statuses, pageInfo);
	}

	@Override
	public long addItemInfo(ItemInfo itemInfo) {
		itemInfoDao.addItemInfo(itemInfo);
		return itemInfo.getId();
	}

	@Override
	public int modifyItemPostage(ItemInfo itemInfo) {
		return itemInfoDao.modifyItemPostage(itemInfo);
	}

	@Override
	public int modifyItemStatusById(Long id, ItemStatusEnum itemStatusEnum) {
		return itemInfoDao.modifyItemStatusById(id, itemStatusEnum.getCode());
	}

	@Override
	public int modifyItemByitemIds(List<Long> itemIds, ItemStatusEnum itemStatusEnum, String auditResult) {

		ItemInfo itemInfo = new ItemInfo();

		if (null != itemStatusEnum) {
			itemInfo.setStatus(itemStatusEnum.getCode());
		}

		itemInfo.setAuditResult(auditResult);

		return itemInfoDao.modifyItemByItemIds(itemIds, itemInfo);
	}

	@Override
	public int modifyItemByitemId(Long itemId, ItemStatusEnum itemStatusEnum, String auditResult) {
		ItemInfo itemInfo = new ItemInfo();

		if (null != itemStatusEnum) {
			itemInfo.setStatus(itemStatusEnum.getCode());
		}

		itemInfo.setAuditResult(auditResult);
		itemInfo.setId(itemId);
		return itemInfoDao.modifyItemByitemId(itemInfo);
	}

}
