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
import com.baoke.admin.sys.domain.AdminRoleAuthority;
import com.baoke.admin.sys.enums.UserConfig;
import com.baoke.admin.sys.manager.AdminRoleAuthorityManager;
import com.baoke.admin.util.DateJsonValueProcessorUtil;
import com.baoke.admin.util.ExcelUtil;
import com.baoke.admin.util.PageBean;
import com.baoke.admin.util.UrlParameterUtils;

import net.sf.json.JSONObject;

@RequestMapping("permission")
@Controller
public class AdminRoleAuthorityController extends BaseController {

	private static final Logger log = Logger.getLogger(AdminRoleAuthorityController.class);

	@Resource
	private AdminRoleAuthorityManager adminRoleAuthorityManager;

	@RequestMapping("saveAdminRoleAuthority")
	public String saveAdminRoleAuthority(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String success = "true";
		JSONObject json = new JSONObject();
		try {
			Map<String, String> map = UrlParameterUtils.getParameterFromRequest(request);
			Map<String, Object> resultMap = new HashMap<String, Object>(map);
			if (map.get("oper").equals("edit")) {
				resultMap.put("updatedBy", request.getSession().getAttribute(UserConfig.USER_NAME.getValue()));
				adminRoleAuthorityManager.updateAdminRoleAuthority(resultMap);
			} else {
				resultMap.put("createdBy", request.getSession().getAttribute(UserConfig.USER_NAME.getValue()));
				adminRoleAuthorityManager.addAdminRoleAuthority(resultMap);
			}
		} catch (Exception e) {
			log.error("saveAdminRoleAuthority() Error:" + e.getMessage(), e);
			success = "false";
		}
		response.setContentType("text/html; charset=UTF-8");
		json.put("success", success);
		response.getWriter().print(json.toString());
		return null;
	}

	@RequestMapping("getAdminRoleAuthorityByCondition")
	public void getAdminRoleAuthorityByCondition(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int pageSize = 10;
		int pageNo = 1;
		String sidx = "id";// 默认按照id排序
		String sord = "desc";
		Map<String, String> map = UrlParameterUtils.getParameterFromRequest(request);
		if (!StringUtils.isEmpty(map.get("sidx")) && !StringUtils.isEmpty(map.get("sord"))) {
			sidx = map.get("sidx");
			sord = map.get("sord");
		}
		sidx = DateJsonValueProcessorUtil.camel2Underscore(sidx);// 驼峰字段转下划线字段
		map.put("sidx", sidx);
		map.put("sord", sord);
		if (map.get("page") != null)
			pageNo = Integer.parseInt(map.get("page"));
		if (map.get("rows") != null)
			pageSize = Integer.parseInt(map.get("rows"));

		int totalRecord = adminRoleAuthorityManager.countAdminRoleAuthorityByCondition(map);
		PageBean<AdminRoleAuthority> pageBean = new PageBean<AdminRoleAuthority>(pageSize, pageNo, totalRecord);

		Map<String, Object> resultMap = new HashMap<String, Object>(map);
		resultMap.put("start", pageBean.getStartNum());
		resultMap.put("end", pageBean.getPageSize());
		List<AdminRoleAuthority> adminRoleAuthorityList = adminRoleAuthorityManager
				.getAdminRoleAuthorityByCondition(resultMap);
		pageBean.setList(adminRoleAuthorityList);
		if (!StringUtils.isEmpty(map.get("oper")) && map.get("oper").equals("excel")) {
			ExcelUtil<AdminRoleAuthority> excelUtil = new ExcelUtil<AdminRoleAuthority>();
			excelUtil.exportExcel("后台角色权限表", pageBean, response);
		} else {
			DateJsonValueProcessorUtil.writeJSON(pageBean, response);
		}
	}
}