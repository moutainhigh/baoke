package com.baoke.admin.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baoke.admin.context.AdminApplicationContext;
import com.baoke.admin.sys.domain.AdminMenu;
import com.baoke.admin.sys.domain.AdminRoleAuthority;
import com.baoke.admin.sys.domain.AdminUser;
import com.baoke.admin.sys.domain.AdminWhiteList;
import com.baoke.admin.sys.enums.UserConfig;
import com.baoke.admin.sys.manager.AdminMenuManager;
import com.baoke.admin.sys.manager.AdminOperatorLogManager;
import com.baoke.admin.sys.manager.AdminRoleAuthorityManager;
import com.baoke.admin.sys.manager.AdminUserManager;
import com.baoke.admin.sys.manager.AdminWhiteListManager;
import com.baoke.admin.util.StringUtils;
import com.baoke.admin.util.UrlParameterUtils;

/**
 * 白名单url过滤及访问权限处理
 *
 */
public class UserAccessFilter implements Filter {

	private static Log logger = LogFactory.getLog(UserAccessFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.error("UserAccessFilter初始化......");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String origin = req.getHeader("Origin");
		if (origin == null) {
			resp.addHeader("Access-Control-Allow-Origin", "*");
		} else {
			resp.addHeader("Access-Control-Allow-Origin", origin);
		}
		resp.addHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept,X-Cookie");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS,DELETE");
		if (req.getMethod().equals("OPTIONS")) {
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		AdminUserManager adminUserManager = (AdminUserManager) AdminApplicationContext.getInstance("adminUserManager");
		AdminWhiteListManager adminWhiteListManager = (AdminWhiteListManager) AdminApplicationContext
				.getInstance("adminWhiteListManager");
		AdminRoleAuthorityManager adminRoleAuthorityManager = (AdminRoleAuthorityManager) AdminApplicationContext
				.getInstance("adminRoleAuthorityManager");
		AdminMenuManager adminMenuManager = (AdminMenuManager) AdminApplicationContext.getInstance("adminMenuManager");
		AdminOperatorLogManager adminOperatorLogManager = (AdminOperatorLogManager) AdminApplicationContext
				.getInstance("adminOperatorLogManager");

		HttpSession session = req.getSession();
		String isLogin = (String) session.getAttribute(UserConfig.IS_LOGIN.getValue());

		String pathInfo = req.getRequestURI();
		Map<String, String> paraMap = UrlParameterUtils.getParameterFromRequest(req);
		if ("/index".equals(pathInfo)) {
			String path = paraMap.get("path");
			if (StringUtils.isNotBlank(path)) {
				pathInfo = pathInfo + "?path=" + path;
			}
		}

		boolean isExsitUrl = false;
		// 无需登录链接-白名单校验，链接直接返回
		isExsitUrl = checkWhiteList(adminWhiteListManager, pathInfo, AdminWhiteList.ISLOGIN_FALSE);
		if (isExsitUrl) {
			chain.doFilter(request, response);
			return;
		}
		// 如果没有登录去登录
		if (!"true".equalsIgnoreCase(isLogin)) {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
		Long userId = (Long) session.getAttribute(UserConfig.USER_ID.getValue());
		AdminUser adminUser = adminUserManager.getAdminUserById(userId);
		if (adminUser == null) {
			req.setAttribute("message", "用户不存在，请联系管理员");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			return;
		}
		// 登录后链接-白名单校验
		isExsitUrl = checkWhiteList(adminWhiteListManager, pathInfo, AdminWhiteList.ISLOGIN_TRUE);
		// 链接地址不在白名单时，进行角色访问链接权限校验
		if (!isExsitUrl) {
			// 获取角色权限
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", adminUser.getRoleId());
			List<AdminRoleAuthority> roleAuthorityList = adminRoleAuthorityManager
					.getAdminRoleAuthorityByCondition(map);
			if (roleAuthorityList != null) {
				for (AdminRoleAuthority roleAuthority : roleAuthorityList) {
					AdminMenu adminMenu = adminMenuManager.getAdminMenuById(roleAuthority.getMenuId());
					if (adminMenu == null) {
						logger.error("菜单不存在！");
						continue;
					}
					String urlStr = adminMenu.getUrl() == null ? "" : adminMenu.getUrl();
					String[] urlArray = urlStr.split(",");
					for (int i = 0; i < urlArray.length; i++) {
						if (urlArray[i].trim().equals(pathInfo)) {
							// 记录操作日志
							Map<String, Object> saveMap = new HashMap<String, Object>();
							saveMap.put("userId", userId);
							saveMap.put("roleId", adminUser.getRoleId());
							saveMap.put("url", pathInfo);
							saveMap.put("description", "用户[" + adminUser.getUserName() + "]操作url[" + pathInfo + "]");
							adminOperatorLogManager.addAdminOperatorLog(saveMap);
							isExsitUrl = true;
							chain.doFilter(request, response);
							return;
						}
					}
				}
			}
		}

		if (!isExsitUrl) {
			req.setAttribute("message", "<div style='color:#FF0000;font-size:35px'>您没有权限操作该功能!</div>");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
			;
			return;
		}

		chain.doFilter(request, response);
	}

	public boolean checkWhiteList(AdminWhiteListManager adminWhiteListManager, String pathInfo, int isLogin) {
		// Map<String,Object> map = new HashMap<String, Object>();
		// map.put("isLogin", isLogin);
		// List<AdminWhiteList> adminWhiteLists =
		// adminWhiteListDao.getAdminWhiteListByCondition(map);
		List<AdminWhiteList> adminWhiteLists = adminWhiteListManager.getAdminWhiteListByIsLogin(isLogin);
		for (AdminWhiteList adminWhiteList : adminWhiteLists) {
			String permissionUrl = adminWhiteList.getUrl();
			permissionUrl = permissionUrl.replaceAll("\\*\\*", "").replaceAll("\\*", "");
			if ("/".equals(permissionUrl)) {
				if ("/".equals(pathInfo)) {
					return true;
				}
			} else {
				if (pathInfo.startsWith(permissionUrl)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void destroy() {
		logger.error("UserAccessFilter销毁......");
	}
}
