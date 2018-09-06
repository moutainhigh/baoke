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
import com.baoke.admin.sys.domain.AdminOperatorLog;
import com.baoke.admin.sys.domain.AdminUser;
import com.baoke.admin.sys.manager.AdminOperatorLogManager;
import com.baoke.admin.sys.manager.AdminUserManager;
import com.baoke.admin.util.DateJsonValueProcessorUtil;
import com.baoke.admin.util.ExcelUtil;
import com.baoke.admin.util.PageBean;
import com.baoke.admin.util.UrlParameterUtils;

@RequestMapping("permission")
@Controller
public class AdminOperatorLogController extends BaseController {

	private static final Logger log = Logger.getLogger(AdminOperatorLogController.class);

	@Resource
	private AdminOperatorLogManager adminOperatorLogManager;
	@Resource
	private AdminUserManager adminUserManager;

	@RequestMapping("getAdminOperatorLogByCondition")
	public void getAdminOperatorLogByCondition(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int pageSize = 10;
		int pageNo = 1;
		String sidx = "gmtCreate";// 按创建时间倒排序
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

		int totalRecord = adminOperatorLogManager.countAdminOperatorLogByCondition(map);
		PageBean<AdminOperatorLog> pageBean = new PageBean<AdminOperatorLog>(pageSize, pageNo, totalRecord);

		Map<String, Object> resultMap = new HashMap<String, Object>(map);
		resultMap.put("start", pageBean.getStartNum());
		resultMap.put("end", pageBean.getPageSize());
		List<AdminOperatorLog> adminOperatorLogList = adminOperatorLogManager.getAdminOperatorLogByCondition(resultMap);
		for (AdminOperatorLog adminOperatorLog : adminOperatorLogList) {
			AdminUser adminUser = adminUserManager.getAdminUserById(adminOperatorLog.getUserId());
			if (adminUser != null) {
				adminOperatorLog.setUserName(adminUser.getUserName());
			}
		}
		pageBean.setList(adminOperatorLogList);
		if (!StringUtils.isEmpty((String) map.get("oper")) && map.get("oper").equals("excel")) {
			ExcelUtil<AdminOperatorLog> excelUtil = new ExcelUtil<AdminOperatorLog>();
			excelUtil.exportExcel("后台操作日志", pageBean, response);
		} else {
			DateJsonValueProcessorUtil.writeJSON(pageBean, response);
		}
		log.info("getAdminOperatorLogByCondition success");
	}
}