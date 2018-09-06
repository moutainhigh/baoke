package com.baoke.admin.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baoke.admin.controller.base.BaseController;
import com.baoke.admin.sys.domain.AdminUser;
import com.baoke.admin.sys.enums.UserConfig;
import com.baoke.admin.sys.manager.AdminUserManager;
import com.baoke.admin.util.DateUtils;
import com.baoke.admin.util.Md5Encrypt;

@Controller
public class LoginController extends BaseController {

	private Log log = LogFactory.getLog(LoginController.class);
	@Resource
	private AdminUserManager adminUserManager;

	@RequestMapping(value = { "/", "/welcome" })
	public String welcome() {
		return "welcome";
	}

	// 新增加的Action方法，映射到
	// 1. /login 登录页面的常规显示
	// 2. /login?error 登录验证失败的展示
	// 3. /login?logout 注销登录的处理
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userName = request.getParameter("j_username");
		String password = request.getParameter("j_password");

		AdminUser adminUser = adminUserManager.getAdminUserByName(userName);

		if (adminUser == null) {
			request.setAttribute("error", "无效的账号密码!");
			log.error(request.getAttribute("error") + ", userName=" + userName + ", ip=" + getIpAddr(request));
			return "login";
		}
		String pwd = Md5Encrypt.md5(password, "UTF-8");
		if (!pwd.equals(adminUser.getPassword())) {
			request.setAttribute("error", "无效的账号密码!");
			log.error(request.getAttribute("error") + ", userName=" + userName + ", ip=" + getIpAddr(request));
			return "login";
		}

		HttpSession session = request.getSession();
		session.setAttribute(UserConfig.IS_LOGIN.getValue(), "true");
		session.setAttribute(UserConfig.USER_ID.getValue(), adminUser.getId());
		session.setAttribute(UserConfig.USER_NAME.getValue(), adminUser.getUserName());
		// 更新最后登录ip和时间
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("lastLoginTime", DateUtils.formatDateYMDHMS(new Date()));
		updateMap.put("lastLoginIp", getIpAddr(request));
		updateMap.put("id", adminUser.getId());

		adminUserManager.updateAdminUser(updateMap);

		log.error("login success, userId=" + adminUser.getId() + ", name=" + adminUser.getUserName() + ", ip="
				+ getIpAddr(request));

		return "redirect:/";
	}
}
