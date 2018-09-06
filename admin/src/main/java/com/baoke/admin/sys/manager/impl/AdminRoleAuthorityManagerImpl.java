package com.baoke.admin.sys.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.admin.sys.dao.AdminRoleAuthorityDao;
import com.baoke.admin.sys.domain.AdminRoleAuthority;
import com.baoke.admin.sys.manager.AdminRoleAuthorityManager;
import com.baoke.common.db.DataSource;

/**
 * 角色权限manager实现
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:13:45
 */
@DataSource(value = "miscDataSource")
@Component("adminRoleAuthorityManager")
public class AdminRoleAuthorityManagerImpl implements AdminRoleAuthorityManager {

	@Resource
	private AdminRoleAuthorityDao adminRoleAuthorityDao;

	@Override
	public List<AdminRoleAuthority> getAdminRoleAuthorityByCondition(Map<String, Object> map) {
		return adminRoleAuthorityDao.getAdminRoleAuthorityByCondition(map);
	}

	@Override
	public int countAdminRoleAuthorityByCondition(Map<String, String> map) {
		return adminRoleAuthorityDao.countAdminRoleAuthorityByCondition(map);
	}

	@Override
	public void addAdminRoleAuthority(Map<String, Object> map) {
		adminRoleAuthorityDao.addAdminRoleAuthority(map);
	}

	@Override
	public void updateAdminRoleAuthority(Map<String, Object> map) {
		adminRoleAuthorityDao.updateAdminRoleAuthority(map);
	}

}
