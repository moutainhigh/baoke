package com.baoke.admin.sys.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.admin.sys.dao.AdminWhiteListDao;
import com.baoke.admin.sys.domain.AdminWhiteList;
import com.baoke.admin.sys.manager.AdminWhiteListManager;
import com.baoke.common.db.DataSource;

/**
 * 白名单 manager 实现
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:14:45
 */
@DataSource(value = "miscDataSource")
@Component("adminWhiteListManager")
public class AdminWhiteListManagerImpl implements AdminWhiteListManager {

	@Resource
	private AdminWhiteListDao adminWhiteListDao;

	@Override
	public List<AdminWhiteList> getAdminWhiteListByIsLogin(int isLogin) {
		return adminWhiteListDao.getAdminWhiteListByIsLogin(isLogin);
	}

	@Override
	public List<AdminWhiteList> getAdminWhiteListByCondition(Map<String, Object> map) {
		return adminWhiteListDao.getAdminWhiteListByCondition(map);
	}

}
