package com.baoke.admin.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.seller.AdminHomeDto;
import com.baoke.item.service.AdminHomeService;

@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource
	private AdminHomeService adminHomeService;

	/**
	 * 首页数据统计
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午4:43:06
	 * @param bannerConfigDto
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryAdminHome")
	public ResponseResult queryAdminHome() {
		try {
			AdminHomeDto adminHomeDto = adminHomeService.queryAdminHome();
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", adminHomeDto);
		} catch (Exception e) {
			logger.error("query AdminHome error.", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		}
	}
}
