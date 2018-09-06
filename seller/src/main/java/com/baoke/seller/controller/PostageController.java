package com.baoke.seller.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.domain.result.Result;
import com.baoke.common.dto.PostageCompanyListDto;
import com.baoke.common.dto.info.PostageInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.trade.domain.PostageInfo;
import com.baoke.trade.service.PostageCompanyInfoService;
import com.baoke.trade.service.PostageService;

@RestController
@RequestMapping("order")
public class PostageController {

	private static final Logger logger = LoggerFactory.getLogger(PostageController.class);
	@Resource
	private PostageCompanyInfoService postageCompanyInfoService;

	@Resource
	private PostageService postageService;

	@RequestMapping(value = "sendItem", method = RequestMethod.POST)
	public ResponsePageResult sendItem(PostageInfo postageInfo) {
		try {
			Result result = postageService.addPostageInfo(postageInfo);
			if (result.isSuccess()) {
				return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, result.getMessage());
			} else {
				return new ResponsePageResult(ResponseResultCodeEnum.ERROR, result.getMessage());
			}
		} catch (Exception e) {
			logger.error("send item error." + postageInfo, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		}
	}

	/**
	 * 查询物流信息
	 * 
	 * @author ljj
	 * @date: 2018年7月5日 下午3:51:47
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value = "queryPostageDetailList", method = RequestMethod.POST)
	public ResponseResult queryPostageDetailList(String orderNo) {
		try {
			PostageInfoDto postageInfoDto = postageService.queryPostageDetailList(orderNo);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", postageInfoDto);
		} catch (ParamInvalidException e) {
			logger.error("query PostageDetailList error." + orderNo, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query PostageDetailList error." + orderNo, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 查询物流公司
	 * 
	 * @author zjm
	 * @date: 2018年7月3日 下午5:48:41
	 * @param videoInfoDto
	 * @return
	 */
	@RequestMapping(value = "/queryPostageCompanyList", method = RequestMethod.POST)
	public ResponseResult saveUpperLowerVideoInfo() {
		try {
			PostageCompanyListDto postageCompanyListDto = postageCompanyInfoService.queryPostageCompanyInfoList();
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", postageCompanyListDto);
		} catch (Exception e) {
			logger.error("query postage company list error", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}

	}
}
