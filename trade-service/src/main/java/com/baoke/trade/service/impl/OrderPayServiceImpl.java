package com.baoke.trade.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.domain.result.Result;
import com.baoke.common.dto.api.OrderPaySignDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.IpUtil;
import com.baoke.common.util.MoneyUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.trade.constant.OrderPayStatusEnum;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.manager.OrderPayManager;
import com.baoke.trade.service.AlipayService;
import com.baoke.trade.service.OrderPayService;
import com.baoke.trade.service.WechatService;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.UserInfoManager;

/**
 * 订单支付实现
 *
 * @author zjj
 * @date 2018年7月4日 上午11:11:42
 */
@ServiceDefinition(value = "orderPayService")
@Service("orderPayService")
public class OrderPayServiceImpl implements OrderPayService {

	private static final Logger logger = Logger.getLogger(OrderPayServiceImpl.class);

	@Resource
	UserInfoManager userInfoManager;

	@Resource
	OrderPayManager orderPayManager;

	@Resource
	AlipayService alipayService;

	@Resource
	WechatService wechatService;

	@Override
	@MethodDefinition(value = "payMyOrder", needLogin = true, needIp = true)
	public OrderPaySignDto payMyOrder(OrderPaySignDto orderPaySignDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(orderPaySignDto);
		String parentOrderNo = orderPaySignDto.getParentOrderNo();
		Integer payType = orderPaySignDto.getPayType();
		String ip = orderPaySignDto.getIp();
		if (!IpUtil.checkIp(ip)) {
			logger.error("payMyOrder error, ip not vaild, userId=" + userInfo.getId() + ",parentOrderNo="
					+ parentOrderNo + ",payType=" + payType + ",ip=" + ip);
			throw new ParamInvalidException("IP格式不合法！");
		}
		if (!OrderPayTypeEnum.isExist(payType) || payType == OrderPayTypeEnum.UNKNOW.getCode()) {
			logger.error("payMyOrder error, payType not vaild, userId=" + userInfo.getId() + ",parentOrderNo="
					+ parentOrderNo + ",payType=" + payType + ",ip=" + ip);
			throw new ParamInvalidException("支付方式只支持微信和支付宝！");
		}
		if (StringUtil.isBlank(parentOrderNo)) {
			throw new ParamInvalidException("订单编号不能为空！");
		}
		OrderPay orderPay = orderPayManager.queryOrderPayByParentOrderNo(parentOrderNo);
		if (null == orderPay) {
			logger.error("payMyOrder error, order is null, userId=" + userInfo.getId() + ",parentOrderNo="
					+ parentOrderNo + ",payType=" + payType + ",ip=" + ip);
			return new OrderPaySignDto(false, "无法找到订单信息，请刷新重试！");
		}

		String orderSignCode = "";
		Integer totalFee = orderPay.getTotalPrice() + orderPay.getTotalPostage();
		String totalPay = MoneyUtil.changeF2Y(totalFee);
		String payDesc = orderPay.getPayDesc();
		payDesc = payDesc.length() > 9 ? (payDesc.substring(0, 9) + "...") : payDesc;

		if (payType.equals(OrderPayTypeEnum.ALIPAY.getCode())) { // 支付宝
			try {
				orderSignCode = alipayService.payOrder(parentOrderNo, payDesc, totalPay);
			} catch (Exception e) {
				logger.error("payMyOrder error, get alipay orderSignCode error, userId=" + userInfo.getId()
						+ ", parentOrderNo=" + parentOrderNo + ", payType=" + payType + ", ip=" + ip + ", totalPay="
						+ totalPay + ", payDesc=" + payDesc, e);
				return new OrderPaySignDto(false, "获取支付口令失败，请刷新重试！");
			}
			if (StringUtil.isBlank(orderSignCode)) {
				logger.error("payMyOrder error, alipay orderSignCode is empty, userId=" + userInfo.getId()
						+ ", parentOrderNo=" + parentOrderNo + ", payType=" + payType + ", ip=" + ip + ", totalPay="
						+ totalPay + ", payDesc=" + payDesc);
				return new OrderPaySignDto(false, "获取支付口令失败，请刷新重试！");
			}
		} else if (payType.equals(OrderPayTypeEnum.WECHATPAY.getCode())) { // 微信
			Result result = null;
			try {
				result = wechatService.wechatPay(parentOrderNo, payDesc, totalFee, ip);
			} catch (Exception e) {
				return new OrderPaySignDto(false, "获取支付口令失败，请刷新重试！");
			}
			if (!result.isSuccess()) {
				logger.error("payMyOrder error, wechatpay orderSignCode is empty, userId=" + userInfo.getId()
						+ ", parentOrderNo=" + parentOrderNo + ", payType=" + payType + ", ip=" + ip + ", totalPay="
						+ totalPay + ", payDesc=" + payDesc);
				return new OrderPaySignDto(false, "获取支付口令失败" + result.getMessage());
			}
			orderSignCode = (String) result.getBody();
		} else {
			/** 其他支付方式 */
		}

		// 修改支付状态为去支付
		orderPay.setId(orderPay.getId());
		orderPay.setPayType(orderPay.getPayType());
		orderPay.setStatus(OrderPayStatusEnum.GO.getCode());
		orderPayManager.modifyOrderPayTypeAndStatus(orderPay);

		OrderPaySignDto orderPaySign = new OrderPaySignDto(true, "");
		orderPaySign.setParentOrderNo(parentOrderNo);
		orderPaySign.setPayType(payType);
		orderPaySign.setTotalPrice(totalPay);
		orderPaySign.setOrderSignCode(orderSignCode);
		return orderPaySign;
	}

}
