package com.baoke.item.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.dao.ItemDetailInfoDao;
import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.manager.ItemDetailInfoManager;

/**
 * 商品详情ManagerImpl
 * 
 * @author zdy
 * @date: 2018年6月13日 下午2:08:51
 */
@Component
@DataSource("coreDataSource")
public class ItemDetailInfoManagerImpl implements ItemDetailInfoManager {

	@Resource
	private ItemDetailInfoDao itemDetailInfoDao;

	@Override
	public ItemDetailInfo queryItemDetailInfoById(long id) {
		return itemDetailInfoDao.queryItemDetailInfoById(id);
	}

	@Override
	public List<ItemDetailInfo> queryItemDetailInfoByItemId(long itemId) {
		return itemDetailInfoDao.queryItemDetailInfoByItemId(itemId);
	}

	@Override
	public List<ItemDetailInfo> queryItemDetailInfoList(ItemDetailInfo itemDetailInfo) {
		return itemDetailInfoDao.queryItemDetailInfoList(itemDetailInfo);
	}

	@Override
	public List<ItemDetailInfo> queryItemDetailInfoLessTenBySellerId(ItemDetailInfo itemDetailInfo, PageInfo pageInfo) {
		return itemDetailInfoDao.queryItemDetailInfoLessTenBySellerId(itemDetailInfo, pageInfo);
	}

	@Override
	public int countItemDetailInfoLessTenBySellerId(ItemDetailInfo itemDetailInfo) {
		return itemDetailInfoDao.countItemDetailInfoLessTenBySellerId(itemDetailInfo);
	}

	@Override
	public int modifyItemDetailNum(ItemDetailInfo itemDetailInfo) {
		return itemDetailInfoDao.modifyItemDetailNum(itemDetailInfo);
	}

	@Override
	public int modifyItemTotalAndSaleNum(ItemDetailInfo itemDetailInfo) {
		return itemDetailInfoDao.modifyItemTotalAndSaleNum(itemDetailInfo);
	}

	@Override
	public Long addItemDetailInfo(ItemDetailInfo itemDetailInfo) {
		return itemDetailInfoDao.addItemDetailInfo(itemDetailInfo);
	}

}
