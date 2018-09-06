package com.baoke.log.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baoke.common.domain.result.Result;

/**
 * APP主服务LOG上报接口
 * 
 * date: 2018年5月30日 下午7:16:28
 * 
 */
@Controller
@RequestMapping("/log")
public class AppLogController {

	@RequestMapping(value = "/app", method = RequestMethod.POST)
	@ResponseBody
	public Result addUv(HttpServletRequest request) {

		return new Result();

	}

}
