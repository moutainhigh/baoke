package com.baoke.admin.sys.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.admin.sys.dao.AdminRoleDao;
import com.baoke.admin.sys.domain.AdminRole;
import com.baoke.admin.sys.manager.AdminRoleManager;
import com.baoke.common.db.DataSource;

/**
 * 角色manager实现
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:14:00
 */
@DataSource(value = "miscDataSource")
@Component("adminRoleManager")
public class AdminRoleManagerImpl implements AdminRoleManager {

	@Resource
	private AdminRoleDao adminRoleDao;

	@Override
	public void addAdminRole(Map<String, Object> map) {
		adminRoleDao.addAdminRole(map);
	}

	@Override
	public void updateAdminRole(Map<String, Object> map) {
		adminRoleDao.updateAdminRole(map);
	}

	@Override
	public List<AdminRole> getAdminRoleByCondition(Map<String, Object> map) {
		return adminRoleDao.getAdminRoleByCondition(map);
	}

	@Override
	public int countAdminRoleByCondition(Map<String, String> map) {
		return adminRoleDao.countAdminRoleByCondition(map);
	}

	@Override
	public AdminRole getAdminRoleById(Long id) {
		return adminRoleDao.getAdminRoleById(id);
	}

}
