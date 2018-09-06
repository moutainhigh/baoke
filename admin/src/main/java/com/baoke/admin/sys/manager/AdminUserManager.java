package com.baoke.admin.sys.manager;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminUser;

/**
 * admin 用户manager
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:12:38
 */
public interface AdminUserManager {
	void addAdminUser(Map<String, Object> map);

	void updateAdminUser(Map<String, Object> map);

	List<AdminUser> getAdminUserByCondition(Map<String, Object> map);

	int countAdminUserByCondition(Map<String, String> map);

	AdminUser getAdminUserById(Long id);

	AdminUser getAdminUserByName(String userName);
}
