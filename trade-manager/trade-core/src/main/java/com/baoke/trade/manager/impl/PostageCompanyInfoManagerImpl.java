package com.baoke.trade.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.PostageCompanyInfoDao;
import com.baoke.trade.domain.PostageCompanyInfo;
import com.baoke.trade.manager.PostageCompanyInfoManager;

/**
 * 快递公司表实现
 * 
 * @author zjm
 * @date: 2018年7月3日 下午7:25:08
 */
@Component
@DataSource(value = "miscDataSource")
public class PostageCompanyInfoManagerImpl implements PostageCompanyInfoManager {

	@Resource
	private PostageCompanyInfoDao postageCompanyInfoDao;

	@Override
	public List<PostageCompanyInfo> queryPostageCompanyInfoList() {
		return postageCompanyInfoDao.queryPostageCompanyInfoList();
	}

	@Override
	public PostageCompanyInfo queryPostageCompanyInfoByCode(String code) {
		return postageCompanyInfoDao.queryPostageCompanyInfoByCode(code);
	}

}
