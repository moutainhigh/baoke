package com.baoke.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.admin.util.PageUtil;
import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.SellerAuditListDto;
import com.baoke.common.dto.info.SellerAuditInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.user.service.SellerInfoService;

/**
 * 主播审核控制
 * 
 * @author ljj
 * @date: 2018年7月7日 下午3:23:56
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	@Resource
	private SellerInfoService sellerInfoService;

	/**
	 * 主播（店铺）审核列表
	 * 
	 * @author ljj
	 * @date: 2018年7月9日 下午3:23:14
	 * @param sellerInfoDto
	 * @return
	 */

	@RequestMapping(value = "/querySellerAuditList", method = RequestMethod.POST)
	public ResponsePageResult querySellerAuditList(SellerInfoDto sellerInfoDto, HttpServletRequest request) {

		sellerInfoDto.setPageInfo(PageUtil.getPageInfo(request));
		try {
			SellerAuditListDto sellerAuditInfoAdminListDto = sellerInfoService
					.querySellerAuditInfoByName(sellerInfoDto);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", sellerAuditInfoAdminListDto.getSellerAuditInfoList(),
					sellerAuditInfoAdminListDto.getPageInfo());
		} catch (MainException e) {
			logger.error("admin query SellerAudit error." + sellerInfoDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("admin query SellerAudit error." + sellerInfoDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 
	 * 修改主播（店铺）的审核状态
	 * 
	 * @author ljj
	 * @date: 2018年7月9日 下午3:23:14
	 * @param SellerAuditInfoDto
	 *            sellerId 不为空
	 * @return
	 */
	@RequestMapping(value = "/auditSellerInfo", method = RequestMethod.POST)
	public ResponseResult auditSellerInfo(SellerAuditInfoDto sellerAuditInfoDto) {

		try {
			sellerInfoService.modifySellerAuditResult(sellerAuditInfoDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (MainException e) {
			logger.error("admin saveUpdate Seller error." + sellerAuditInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("admin saveUpdate Seller error." + sellerAuditInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}
}
