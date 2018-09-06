package com.baoke.admin.sys.manager;

import java.util.List;

import com.baoke.admin.sys.domain.AdminMenu;

/**
 * admin 菜单manager
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:10:56
 */
public interface AdminMenuManager {

	/**
	 * 通过id获取菜单
	 * 
	 * @param id
	 * @return
	 */
	AdminMenu getAdminMenuById(Long id);

	/**
	 * 获取菜单列表
	 * 
	 * @param id
	 * @return
	 */
	List<AdminMenu> getAdminMenuList();

}
