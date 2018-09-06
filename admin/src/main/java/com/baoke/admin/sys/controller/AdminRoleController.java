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
import com.baoke.admin.sys.manager.AdminRoleManager;
import com.baoke.admin.util.DateJsonValueProcessorUtil;
import com.baoke.admin.util.ExcelUtil;
import com.baoke.admin.util.PageBean;
import com.baoke.admin.util.UrlParameterUtils;

import net.sf.json.JSONObject;

@RequestMapping("permission")
@Controller
public class AdminRoleController extends BaseController {

	private static final Logger log = Logger.getLogger(AdminRoleController.class);

	@Resource
	private AdminRoleManager adminRoleManager;

	@RequestMapping("saveAdminRole")
	public String saveAdminRole(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean success = true;
		JSONObject json = new JSONObject();
		Long id = 0l;
		try {
			Map<String, Object> map = UrlParameterUtils.getParameterFromRequestForHandle(request);
			if (map.get("oper").equals("edit")) {
				adminRoleManager.updateAdminRole(map);
			} else {
				adminRoleManager.addAdminRole(map);
				id = (Long) map.get("id");
			}
		} catch (Exception e) {
			log.error("saveAdminRole() Error:" + e.getMessage());
			success = false;
		}
		response.setContentType("text/html; charset=UTF-8");
		json.put("success", success);
		json.put("id", id);
		response.getWriter().print(json.toString());
		return null;
	}

	@RequestMapping("getAdminRoleByCondition")
	public String getAdminRoleByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		int totalRecord = adminRoleManager.countAdminRoleByCondition(map);
		PageBean<AdminRole> pageBean = new PageBean<AdminRole>(pageSize, pageNo, totalRecord);

		Map<String, Object> resultMap = new HashMap<String, Object>(map);
		resultMap.put("start", pageBean.getStartNum());
		resultMap.put("end", pageBean.getPageSize());
		List<AdminRole> adminRoleList = adminRoleManager.getAdminRoleByCondition(resultMap);
		pageBean.setList(adminRoleList);
		if (!StringUtils.isEmpty(map.get("oper")) && map.get("oper").equals("excel")) {
			ExcelUtil<AdminRole> excelUtil = new ExcelUtil<AdminRole>();
			excelUtil.exportExcel("后台角色表", pageBean, response);
		} else {
			DateJsonValueProcessorUtil.writeJSON(pageBean, response);
		}
		return null;
	}

}