package com.baoke.log.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoke.common.domain.result.Result;
import com.baoke.common.util.StringUtil;
import com.baoke.log.domain.LogStatPv;
import com.baoke.log.domain.LogStatUv;
import com.baoke.log.service.impl.StatExcutorTask;

/**
 * pv.uv 统计控制类
 * 
 * date: 2018年5月18日 下午5:16:28
 * 
 * @author zjm
 * @version
 */
@Controller
@RequestMapping("/stat")
public class StatisticalController {

	/**
	 * 统计uv接口
	 * 
	 * @author zjm
	 * @date 2018年5月18日 下午1:55:39
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uv", method = RequestMethod.POST)
	@ResponseBody
	public Result addUv(HttpServletRequest request) {

		String scen = request.getParameter("scen");
		String unionId = request.getParameter("unionId");

		if (StringUtil.hasLength(scen) && StringUtil.hasLength(unionId)) {
			StatExcutorTask.uvDeque.add(new LogStatUv(scen, unionId, 1));
		} else {
			return new Result(false, "参数为空", null);
		}

		return new Result();

	}

	/**
	 * 统计pv接口
	 * 
	 * @author zjm
	 * @date 2018年5月18日 下午1:55:49
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pv", method = RequestMethod.POST)
	@ResponseBody
	public Result addPv(HttpServletRequest request) {

		String scen = request.getParameter("scen");
		String unionId = request.getParameter("unionId");

		if (StringUtil.hasLength(scen)) {
			StatExcutorTask.pvDeque.add(new LogStatPv(scen, unionId, 1));
			StatExcutorTask.uvDeque.add(new LogStatUv(scen, unionId, 1));
		} else {
			return new Result(false, "参数为空", null);
		}

		return new Result();
	}

}
