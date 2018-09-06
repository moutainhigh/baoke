package com.baoke.admin.sys.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baoke.admin.controller.base.BaseController;
import com.baoke.admin.sys.domain.AdminMenu;
import com.baoke.admin.sys.domain.AdminRole;
import com.baoke.admin.sys.manager.AdminMenuManager;
import com.baoke.admin.sys.manager.AdminRoleManager;

@Controller
public class JspDispaterController extends BaseController {

	private static final Logger log = Logger.getLogger(JspDispaterController.class);

	@Resource
	private AdminRoleManager adminRoleManager;
	@Resource
	private AdminMenuManager adminMenuManager;

	/**
	 * 处理菜单为：/index?path=xxx的请求
	 * 
	 * @param request
	 *            /index?path=xxx
	 * @param response
	 *            响应path对应的JSP页面
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String requestDispatcher(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "path", required = false) String path) throws Exception {
		log.info(path);
		if (StringUtils.isBlank(path)) {
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} else {
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			// 全局变量
			/**
			 * 1. 角色id-角色name 对应关系值
			 */
			List<AdminRole> adminRoleList = adminRoleManager.getAdminRoleByCondition(null);
			Map<String, Object> roleValue = new HashMap<String, Object>();
			if (adminRoleList != null) {
				for (AdminRole adminRole : adminRoleList)
					roleValue.put(adminRole.getId().toString(), adminRole.getRoleName());
			}
			String roleValueStr = roleValue.toString().replace("{", "").replace("}", "").replace("=", ":")
					.replace(",", ";").replaceAll(" ", "");
			request.setAttribute("roleValue", roleValueStr);
			/**
			 * 2. 菜单id-菜单name 对应关系值
			 */
			List<AdminMenu> adminMenuList = adminMenuManager.getAdminMenuList();
			LinkedHashMap<String, Object> menuValue = new LinkedHashMap<String, Object>();
			if (adminMenuList != null) {
				for (AdminMenu adminMenu : adminMenuList)
					menuValue.put(adminMenu.getId().toString(), adminMenu.getTitle());
			}
			String menuValueStr = menuValue.toString().replace("{", "").replace("}", "").replace("=", ":")
					.replace(",", ";").replaceAll(" ", "");
			request.setAttribute("menuValue", menuValueStr);

			if (path.indexOf("permission") < 0) {
				request.getRequestDispatcher(path + ".html").forward(request, response);
			} else {
				request.getRequestDispatcher(path + ".jsp").forward(request, response);
			}

		}
		return null;
	}
}
