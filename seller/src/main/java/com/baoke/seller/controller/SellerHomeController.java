package com.baoke.seller.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.seller.SellerHomeDto;
import com.baoke.item.service.SellerHomeService;

@RestController
public class SellerHomeController {

	private static final Logger logger = LoggerFactory.getLogger(SellerHomeController.class);

	@Resource
	private SellerHomeService sellerHomeService;

	@RequestMapping(value = "/querySellerHome", method = RequestMethod.POST)
	public ResponseResult querySellerHome(HttpServletRequest request) {
		try {
			SellerHomeDto sellerHomeDto = sellerHomeService
					.sellerHomeStatistics((long) request.getSession().getAttribute("userId"));
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", sellerHomeDto);
		} catch (Exception e) {
			logger.error("query seller home error.", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}
}
