package com.baoke.admin.sys.dao;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminUser;

public interface AdminUserDao {
	public void addAdminUser(Map<String, Object> map);

	public void updateAdminUser(Map<String, Object> map);

	public List<AdminUser> getAdminUserByCondition(Map<String, Object> map);

	public int countAdminUserByCondition(Map<String, String> map);

	public AdminUser getAdminUserById(Long id);

	public AdminUser getAdminUserByName(String userName);

}