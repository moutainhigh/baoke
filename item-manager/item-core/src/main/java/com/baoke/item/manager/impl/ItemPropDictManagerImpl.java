package com.baoke.item.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.item.dao.ItemPropDictDao;
import com.baoke.item.domain.ItemPropDict;
import com.baoke.item.manager.ItemPropDictManager;

/**
 * 商品属性字典ManagerImpl
 * 
 * @author zdy
 * @date: 2018年6月13日 下午1:56:29
 */
@Component
@DataSource("coreDataSource")
public class ItemPropDictManagerImpl implements ItemPropDictManager {
	@Resource
	private ItemPropDictDao itemPropDictDao;

	@Override
	public ItemPropDict queryItemPropDictById(long id) {
		return itemPropDictDao.queryItemPropDictById(id);
	}

}
