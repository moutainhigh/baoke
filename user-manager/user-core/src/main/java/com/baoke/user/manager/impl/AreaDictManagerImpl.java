package com.baoke.user.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.db.DataSource;
import com.baoke.user.dao.AreaDictDao;
import com.baoke.user.domain.AreaDictInfo;
import com.baoke.user.manager.AreaDictManager;

/**
 * 地区字典manager实现
 * 
 * @author wyj
 * @date: 2018年6月13日 下午6:12:27
 */
@Component
@DataSource("miscDataSource")
public class AreaDictManagerImpl implements AreaDictManager {

	@Resource
	private AreaDictDao areaDictDao;

	@Override
	public AreaDictInfo queryAddressByCode(String code, BooleanEnum booleanEnum) {
		return areaDictDao.queryAreaDictByCode(code, booleanEnum.getCode());
	}

	@Override
	public List<AreaDictInfo> queryAddressDictByParentCode(String parentCode) {
		return areaDictDao.queryAreaDictList(new AreaDictInfo(parentCode, BooleanEnum.TRUE));
	}

}
