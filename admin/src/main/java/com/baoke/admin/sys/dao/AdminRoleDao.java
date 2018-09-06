package com.baoke.admin.sys.dao;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminRole;

public interface AdminRoleDao {
	public void addAdminRole(Map<String, Object> map);

	public void updateAdminRole(Map<String, Object> map);

	public List<AdminRole> getAdminRoleByCondition(Map<String, Object> map);

	public int countAdminRoleByCondition(Map<String, String> map);

	public AdminRole getAdminRoleById(Long id);

}