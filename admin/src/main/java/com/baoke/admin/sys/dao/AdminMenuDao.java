package com.baoke.admin.sys.dao;

import java.util.List;

import com.baoke.admin.sys.domain.AdminMenu;

public interface AdminMenuDao {
	/**
	 * 通过id获取菜单
	 * 
	 * @param id
	 * @return
	 */
	public AdminMenu getAdminMenuById(Long id);

	/**
	 * 获取菜单列表
	 * 
	 * @param id
	 * @return
	 */
	public List<AdminMenu> getAdminMenuList();

}