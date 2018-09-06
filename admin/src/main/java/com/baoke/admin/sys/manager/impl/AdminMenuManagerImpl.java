package com.baoke.admin.sys.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.admin.sys.dao.AdminMenuDao;
import com.baoke.admin.sys.domain.AdminMenu;
import com.baoke.admin.sys.manager.AdminMenuManager;
import com.baoke.common.db.DataSource;

/**
 * admin 菜单manager 实现
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:13:18
 */
@DataSource(value = "miscDataSource")
@Component("adminMenuManager")
public class AdminMenuManagerImpl implements AdminMenuManager {

	@Resource
	private AdminMenuDao adminMenuDao;

	@Override
	public AdminMenu getAdminMenuById(Long id) {
		return adminMenuDao.getAdminMenuById(id);
	}

	@Override
	public List<AdminMenu> getAdminMenuList() {
		return adminMenuDao.getAdminMenuList();
	}

}
