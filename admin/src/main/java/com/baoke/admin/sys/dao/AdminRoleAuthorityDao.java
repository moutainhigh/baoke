package com.baoke.admin.sys.dao;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminRoleAuthority;

public interface AdminRoleAuthorityDao {
	/**
	 * 查询角色权限列表
	 * 
	 * @param map
	 * @return
	 */
	public List<AdminRoleAuthority> getAdminRoleAuthorityByCondition(Map<String, Object> map);

	public int countAdminRoleAuthorityByCondition(Map<String, String> map);

	/**
	 * 添加记录
	 * 
	 * @param map
	 */
	public void addAdminRoleAuthority(Map<String, Object> map);

	/**
	 * 更新记录
	 * 
	 * @param map
	 */
	public void updateAdminRoleAuthority(Map<String, Object> map);

}