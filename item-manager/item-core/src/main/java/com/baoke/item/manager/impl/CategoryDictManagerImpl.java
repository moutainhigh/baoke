package com.baoke.item.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.db.DataSource;
import com.baoke.item.dao.CategoryDictDao;
import com.baoke.item.domain.CategoryDict;
import com.baoke.item.manager.CategoryDictManager;

@Component
@DataSource("coreDataSource")
public class CategoryDictManagerImpl implements CategoryDictManager {

	@Resource
	private CategoryDictDao categoryDictDao;

	@Override
	public List<CategoryDict> queryCategoryDictList(Long parentId, BooleanEnum booleanEnum) {
		return categoryDictDao.queryCategoryDictList(parentId, booleanEnum.getCode());
	}

	@Override
	public String queryCategoryDictByids(List<String> ids) {
		return categoryDictDao.queryCategoryDictByids(ids);
	}

}
