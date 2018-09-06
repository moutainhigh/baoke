package com.baoke.admin.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baoke.admin.controller.base.BaseController;
import com.baoke.admin.sys.domain.AdminMenu;
import com.baoke.admin.sys.domain.AdminRoleAuthority;
import com.baoke.admin.sys.domain.AdminUser;
import com.baoke.admin.sys.domain.Menu;
import com.baoke.admin.sys.enums.UserConfig;
import com.baoke.admin.sys.manager.AdminMenuManager;
import com.baoke.admin.sys.manager.AdminRoleAuthorityManager;
import com.baoke.admin.sys.manager.AdminUserManager;

/**
 * admin菜单权限管理
 * 
 * @author yaohb
 *
 */
@Controller
public class MenuController extends BaseController {
	@Resource
	private AdminUserManager adminUserManager;

	@Resource
	private AdminMenuManager adminMenuManager;

	@Resource
	private AdminRoleAuthorityManager adminRoleAuthorityManager;

	@RequestMapping("/loadMenu")
	public void loadMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 用户权限校验
		 */
		List<Menu> menus = new ArrayList<Menu>();
		Long userId = (Long) request.getSession().getAttribute(UserConfig.USER_ID.getValue());
		AdminUser adminUser = adminUserManager.getAdminUserById(userId);
		// 根据角色权限 显示菜单
		if (adminUser != null) {
			// 获取父菜单
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", adminUser.getRoleId());
			map.put("parentId", AdminRoleAuthority.PARENT_SELF);
			List<AdminRoleAuthority> parentRoleAuthorityList = adminRoleAuthorityManager
					.getAdminRoleAuthorityByCondition(map);
			if (parentRoleAuthorityList != null) {
				// 获取父菜单下的子菜单
				for (AdminRoleAuthority parentRoleAuthority : parentRoleAuthorityList) {
					map = new HashMap<String, Object>();
					map.put("roleId", adminUser.getRoleId());
					map.put("parentId", parentRoleAuthority.getMenuId());
					List<AdminRoleAuthority> childRoleAuthorityList = adminRoleAuthorityManager
							.getAdminRoleAuthorityByCondition(map);
					if (childRoleAuthorityList != null) {
						// 装载父菜单
						Menu parentMenu = new Menu();
						AdminMenu adminMenu = new AdminMenu();
						adminMenu = adminMenuManager.getAdminMenuById(parentRoleAuthority.getMenuId());
						if (adminMenu != null) {
							parentMenu.setId(adminMenu.getMenuTab());
							parentMenu.setText(adminMenu.getTitle());
							parentMenu.setSort(adminMenu.getSort());
							parentMenu.setLeaf(false);
							menus.add(parentMenu);
						}

						for (AdminRoleAuthority chileRoleAuthority : childRoleAuthorityList) {
							Menu childMenu = new Menu();
							adminMenu = new AdminMenu();
							adminMenu = adminMenuManager.getAdminMenuById(chileRoleAuthority.getMenuId());
							if (adminMenu != null) {
								if (AdminMenu.IS_SHOW_TRUE == adminMenu.getIsShow()) {
									childMenu.setId(adminMenu.getMenuTab());
									childMenu.setText(adminMenu.getTitle());
									childMenu.setSort(adminMenu.getSort());
									childMenu.setLeaf(true);
									parentMenu.addChildren(childMenu);
								}
							}
						}
					}
				}
			}
		}
		// 菜单排序展示
		sortMenu(menus);
		for (Menu menu : menus) {
			if (menu.getChildren() != null) {
				sortMenu(menu.getChildren());
			}
		}
		super.writeJsonForJsonArrayWithObjectList(response, menus);
	}

	/**
	 * 菜单排序
	 * 
	 * @param menus
	 */
	public void sortMenu(List<Menu> menus) {

		Collections.sort(menus, new Comparator<Menu>() {

			public int compare(Menu arg0, Menu arg1) {

				return arg0.getSort().compareTo(arg1.getSort());

			}
		});
	}
}
