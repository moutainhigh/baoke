package com.baoke.admin.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baoke.admin.controller.base.BaseController;
import com.baoke.admin.sys.domain.AdminRole;
import com.baoke.admin.sys.domain.AdminUser;
import com.baoke.admin.sys.enums.UserConfig;
import com.baoke.admin.sys.manager.AdminRoleManager;
import com.baoke.admin.sys.manager.AdminUserManager;
import com.baoke.admin.util.DateJsonValueProcessorUtil;
import com.baoke.admin.util.ExcelUtil;
import com.baoke.admin.util.PageBean;
import com.baoke.admin.util.UrlParameterUtils;

import net.sf.json.JSONObject;

@RequestMapping("permission")
@Controller
public class AdminUserController extends BaseController {

	private static final Logger log = Logger.getLogger(AdminUserController.class);

	@Resource
	private AdminUserManager adminUserManager;
	@Resource
	private AdminRoleManager adminRoleManager;

	@RequestMapping("saveAdminUser")
	public String saveAdminUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String result = "success";
		JSONObject json = new JSONObject();
		Long id = 0l;
		try {
			Map<String, Object> map = UrlParameterUtils.getParameterFromRequestForHandle(request);
			Long operatorId = (Long) request.getSession().getAttribute(UserConfig.USER_ID.getValue());
			String operatorNick = (String) request.getSession().getAttribute(UserConfig.USER_NAME.getValue());
			if (map.get("oper").equals("edit")) {
				adminUserManager.updateAdminUser(map);
				log.error(
						"updateAdminUser executed,operatorId=" + operatorId + ",nick=" + operatorNick + ",map=" + map);
			} else {
				adminUserManager.addAdminUser(map);
				id = (Long) map.get("id");
				log.error("addAdminUser executed,operatorId=" + operatorId + ",nick=" + operatorNick);
			}
		} catch (Exception e) {
			log.error("saveAdminUser() Error:" + e.getMessage());
			result = "error";
		}
		response.setContentType("text/html; charset=UTF-8");
		json.put("result", result);
		json.put("id", id);
		response.getWriter().print(json.toString());
		return null;
	}

	@RequestMapping("getAdminUserByCondition")
	public String getAdminUserByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int pageSize = 10;
		int pageNo = 1;
		String sidx = "id";
		String sord = "desc";
		Map<String, String> map = UrlParameterUtils.getParameterFromRequest(request);
		if (!StringUtils.isEmpty(map.get("sidx")) && !StringUtils.isEmpty(map.get("sord"))) {
			sidx = map.get("sidx");
			sord = map.get("sord");
		}
		sidx = DateJsonValueProcessorUtil.camel2Underscore(sidx);
		map.put("sidx", sidx);
		map.put("sord", sord);

		if (map.get("page") != null)
			pageNo = Integer.parseInt(map.get("page"));
		if (map.get("rows") != null)
			pageSize = Integer.parseInt(map.get("rows"));

		int totalRecord = adminUserManager.countAdminUserByCondition(map);
		PageBean<AdminUser> pageBean = new PageBean<AdminUser>(pageSize, pageNo, totalRecord);
		Map<String, Object> resultMap = new HashMap<String, Object>(map);
		resultMap.put("start", pageBean.getStartNum());
		resultMap.put("end", pageBean.getPageSize());
		List<AdminUser> adminUserList = adminUserManager.getAdminUserByCondition(resultMap);
		for (AdminUser adminUser : adminUserList) {
			AdminRole role = adminRoleManager.getAdminRoleById(adminUser.getRoleId());
			adminUser.setRoleName(role.getRoleName());
			adminUser.setPassword("");
		}
		pageBean.setList(adminUserList);
		if (!StringUtils.isEmpty((String) map.get("oper")) && map.get("oper").equals("excel")) {
			ExcelUtil<AdminUser> excelUtil = new ExcelUtil<AdminUser>();
			excelUtil.exportExcel("后台用户", pageBean, response);
		} else {
			DateJsonValueProcessorUtil.writeJSON(pageBean, response);
		}
		return null;
	}

}