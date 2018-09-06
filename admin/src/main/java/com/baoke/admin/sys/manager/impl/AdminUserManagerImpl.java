package com.baoke.admin.sys.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.admin.sys.dao.AdminUserDao;
import com.baoke.admin.sys.domain.AdminUser;
import com.baoke.admin.sys.manager.AdminUserManager;
import com.baoke.common.db.DataSource;

/**
 * admin 用户manager实现
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:14:32
 */
@DataSource(value = "miscDataSource")
@Component("adminUserManager")
public class AdminUserManagerImpl implements AdminUserManager {

	@Resource
	private AdminUserDao adminUserDao;

	@Override
	public void addAdminUser(Map<String, Object> map) {
		adminUserDao.addAdminUser(map);
	}

	@Override
	public void updateAdminUser(Map<String, Object> map) {
		adminUserDao.updateAdminUser(map);
	}

	@Override
	public List<AdminUser> getAdminUserByCondition(Map<String, Object> map) {
		return adminUserDao.getAdminUserByCondition(map);
	}

	@Override
	public int countAdminUserByCondition(Map<String, String> map) {
		return adminUserDao.countAdminUserByCondition(map);
	}

	@Override
	public AdminUser getAdminUserById(Long id) {
		return adminUserDao.getAdminUserById(id);
	}

	@Override
	public AdminUser getAdminUserByName(String userName) {
		return adminUserDao.getAdminUserByName(userName);
	}

}
