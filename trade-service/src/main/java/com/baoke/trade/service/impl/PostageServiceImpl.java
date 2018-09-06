package com.baoke.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.domain.result.Result;
import com.baoke.common.dto.info.PostageDetailInfoDto;
import com.baoke.common.dto.info.PostageInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.manager.MessageNotifyManager;
import com.baoke.trade.constant.OrderStatusEnum;
import com.baoke.trade.constant.PostageStatusEnum;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.PostageCompanyInfo;
import com.baoke.trade.domain.PostageDetail;
import com.baoke.trade.domain.PostageInfo;
import com.baoke.trade.manager.OrderItemManager;
import com.baoke.trade.manager.OrderManager;
import com.baoke.trade.manager.PostageCompanyInfoManager;
import com.baoke.trade.manager.PostageDetailManager;
import com.baoke.trade.manager.PostageInfoManager;
import com.baoke.trade.service.PostageService;
import com.baoke.user.manager.SendSiteManager;

/**
 * 物流Service实现
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午6:28:42
 */
@ServiceDefinition("postageService")
@Service("postageService")
public class PostageServiceImpl implements PostageService {

	private static final Logger logger = Logger.getLogger(PostageServiceImpl.class);

	@Resource
	private PostageInfoManager postageInfoManager;

	@Resource
	private PostageDetailManager postageDetailManager;

	@Resource
	private PostageCompanyInfoManager postageCompanyInfoManager;

	@Resource
	private MessageNotifyManager messageNotifyManager;

	@Resource
	OrderItemManager orderItemManager;

	@Autowired
	SendSiteManager sendSiteManager;

	@Resource
	OrderManager orderManager;

	@Override
	public Result addPostageInfo(PostageInfo postageInfo) throws ParamInvalidException {
		if (postageInfo == null) {
			throw new ParamInvalidException("物流信息不能为空");
		}
		if (StringUtil.isBlank(postageInfo.getCompanyCode())) {
			throw new ParamInvalidException("物流公司不能为空");
		}
		PostageCompanyInfo postageCompanyInfo = postageCompanyInfoManager
				.queryPostageCompanyInfoByCode(postageInfo.getCompanyCode());
		if (postageCompanyInfo == null) {
			throw new ParamInvalidException("无效的快递公司");
		}
		if (StringUtil.isBlank(postageInfo.getOrderNo())) {
			throw new ParamInvalidException("订单号不能为空");
		}
		Order order = orderManager.queryOrderByOrderNo(postageInfo.getOrderNo());
		if (null == order) {
			return new Result(false, "未查询到订单信息，请重试");
		}
		if (order.getStatus() != OrderStatusEnum.WAIT_DELIVER.getCode()) {
			return new Result(false, "该订单状态不合法，请确认");
		}
		if (StringUtil.isBlank(postageInfo.getPostageNo())) {
			throw new ParamInvalidException("快递号不能为空");
		}
		postageInfo.setStatus(PostageStatusEnum.DELIVED.getCode());
		postageInfo.setPostage(order.getTotalPostage());
		if (postageInfoManager.addPostageInfo(postageInfo) < 1) {
			logger.warn("add postageInfo error, save to database error, postageInfo = " + postageInfo);
			return new Result(false, "操作失败，请重试");
		}
		orderManager.modifyOrderStatusByOrderNo(order.getOrderNo(), OrderStatusEnum.WAIT_RECEIVE);

		// 站内信
		SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID, order.getUserId(),
				null, "订单发货", "订单编号为" + postageInfo.getOrderNo() + "的订单已发货，请注意查收");
		sendSiteManager.sendSite(siteMessage);

		return new Result(true, "");
	}

	@Override
	@MethodDefinition(value = "queryOrderPostage", needLogin = true)
	public PostageInfoDto queryOrderPostage(PostageInfoDto postageInfoDto) throws MainException {
		if (postageInfoDto == null || postageInfoDto.getUserId() == null) {
			throw new ParamInvalidException("请先登陆");
		}

		postageInfoDto = this.queryPostageDetailList(postageInfoDto.getOrderNo());
		return postageInfoDto;
	}

	@Override
	public PostageInfoDto queryPostageDetailList(String orderNo) throws ParamInvalidException {
		if (orderNo == null) {
			throw new ParamInvalidException("订单号不能为空");
		}
		List<PostageInfo> postageInfoList = postageInfoManager.queryPostageInfoByOrderNo(orderNo);
		if (postageInfoList == null || postageInfoList.size() <= 0) {
			throw new ParamInvalidException("订单号无效");
		}
		PostageInfo postageInfo = postageInfoList.get(0);
		PostageCompanyInfo postageCompanyInfo = postageCompanyInfoManager
				.queryPostageCompanyInfoByCode(postageInfo.getCompanyCode());
		if (postageCompanyInfo == null) {
			throw new ParamInvalidException("无效的快递公司");
		}

		String companyName = postageCompanyInfo.getName() == null ? postageCompanyInfo.getSimpleName()
				: postageCompanyInfo.getName();

		List<PostageDetail> postageDetailList = postageDetailManager
				.queryPostageDetailByPostageNo(postageInfo.getPostageNo());
		List<PostageDetailInfoDto> postageDetailInfoDtoList = new ArrayList<>();
		for (PostageDetail postageDetail : postageDetailList) {
			PostageDetailInfoDto postageDetailInfoDto = new PostageDetailInfoDto();
			postageDetailInfoDto.setContent(postageDetail.getContent());
			postageDetailInfoDto.setDateTime(postageDetail.getDateTime().getTime());
			postageDetailInfoDtoList.add(postageDetailInfoDto);
		}

		PostageInfoDto postageInfoDto = new PostageInfoDto(true, "");

		postageInfoDto.setCompanyName(companyName);
		postageInfoDto.setLogoUrl(postageCompanyInfo.getLogoUrl());
		postageInfoDto.setPostageNo(postageInfo.getPostageNo());
		postageInfoDto.setStatus(postageInfo.getStatus());
		postageInfoDto.setStatusDesc(PostageStatusEnum.getNameByCode(postageInfo.getStatus()));
		postageInfoDto.setPostageDetailList(postageDetailInfoDtoList);

		return postageInfoDto;
	}

}
