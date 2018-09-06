package com.baoke.seller.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.seller.SellerOrderListDto;
import com.baoke.common.dto.seller.SellerOrderQueryDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.seller.util.PageUtil;
import com.baoke.trade.service.OrderService;
import com.baoke.trade.service.PostageCompanyInfoService;

/**
 * 主播后台订单相关Controller
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午2:53:22
 */
@RestController
@RequestMapping("order")
public class OrderController {

	@Resource
	private OrderService orderService;

	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	@Resource
	private PostageCompanyInfoService postageCompanyInfoService;

	/**
	 * 查询主播的订单列表
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 上午10:14:19
	 */
	@RequestMapping(value = "querySellerOrderList", method = RequestMethod.POST)
	public ResponsePageResult querySellerOrderList(SellerOrderQueryDto sellerOrderQueryDto, HttpServletRequest request,
			HttpSession session) {
		try {
			sellerOrderQueryDto.setSellerId((Long) session.getAttribute("userId"));
			PageInfo pageInfo = PageUtil.getPageInfo(request);
			SellerOrderListDto sellerOrderListDto = orderService.querySellerOrderList(sellerOrderQueryDto, pageInfo);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", sellerOrderListDto, sellerOrderListDto.getPageInfo());
		} catch (MainException e) {
			logger.error("querySellerOrderList error sellerId=" + session.getAttribute("USER_ID"), e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("querySellerOrderList error sellerId=" + session.getAttribute("USER_ID"), e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

}
