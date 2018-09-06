package com.baoke.admin.sys.manager;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminRoleAuthority;

/**
 * 角色权限manager
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:11:35
 */
public interface AdminRoleAuthorityManager {

	/**
	 * 查询角色权限列表
	 * 
	 * @param map
	 * @return
	 */
	List<AdminRoleAuthority> getAdminRoleAuthorityByCondition(Map<String, Object> map);

	int countAdminRoleAuthorityByCondition(Map<String, String> map);

	/**
	 * 添加记录
	 * 
	 * @param map
	 */
	void addAdminRoleAuthority(Map<String, Object> map);

	/**
	 * 更新记录
	 * 
	 * @param map
	 */
	void updateAdminRoleAuthority(Map<String, Object> map);

}
