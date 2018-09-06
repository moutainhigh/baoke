package com.baoke.admin.sys.manager;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminRole;

/**
 * 角色manager
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:11:56
 */
public interface AdminRoleManager {

	void addAdminRole(Map<String, Object> map);

	void updateAdminRole(Map<String, Object> map);

	List<AdminRole> getAdminRoleByCondition(Map<String, Object> map);

	int countAdminRoleByCondition(Map<String, String> map);

	AdminRole getAdminRoleById(Long id);

}
