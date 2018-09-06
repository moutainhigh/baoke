package com.baoke.interact.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.SellerListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.UserEmptyException;
import com.baoke.common.exception.base.MainException;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.domain.SellerFocus;
import com.baoke.interact.manager.SellerFocusManager;
import com.baoke.interact.service.SellerFocusService;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserInfoManager;

@Service("sellerFocusService")
@ServiceDefinition(value = "sellerFocusService")
public class SellerFocusServiceImpl implements SellerFocusService {

	private static final Logger logger = Logger.getLogger(SellerFocusServiceImpl.class);

	@Resource
	private SellerFocusManager sellerFocusManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private SendSiteManager sendSiteManager;

	@MethodDefinition(value = "queryMyFocusSeller", needLogin = true)
	@Override
	public SellerListDto queryMyFocusSeller(SellerInfoDto sellerInfoDto) throws MainException {
		if (sellerInfoDto == null || sellerInfoDto.getUserId() == null || sellerInfoDto.getUserId() == 0L) {
			throw new UserEmptyException();
		}

		List<SellerFocus> sellerFocusList = sellerFocusManager.querySellerFocusPage(
				new SellerFocus(sellerInfoDto.getUserId(), SellerFocusStatusEnum.FOCUS, null),
				sellerInfoDto.getPageInfo());

		List<SellerInfoDto> sellerInfoDtoList = new ArrayList<SellerInfoDto>();
		for (SellerFocus sellerFocus : sellerFocusList) {
			UserInfo userInfo = userInfoManager.queryUserInfoById(sellerFocus.getSellerId());
			if (null == userInfo) {
				continue;
			}

			// 统计播主的关注量
			int beFocusNum = sellerFocusManager
					.countSellerFocus(new SellerFocus(null, SellerFocusStatusEnum.FOCUS, sellerFocus.getSellerId()));

			SellerInfoDto sellerInfoDtoResult = new SellerInfoDto();
			sellerInfoDtoResult.setSellerId(userInfo.getId());
			sellerInfoDtoResult.setSellerImgUrl(userInfo.getHeadImgUrl());
			sellerInfoDtoResult.setSellerNickName(userInfo.getNickName());
			sellerInfoDtoResult.setIsFocus(SellerFocusStatusEnum.FOCUS.getCode());
			sellerInfoDtoResult.setBeFocusNum(beFocusNum);
			sellerInfoDtoList.add(sellerInfoDtoResult);
		}

		return new SellerListDto(true, "", sellerInfoDtoList);
	}

	@MethodDefinition(value = "saveFocusSeller", needLogin = true)
	@Override
	public BaseResultDto saveFocusSeller(SellerInfoDto sellerInfoDto) throws MainException {
		if (sellerInfoDto == null || sellerInfoDto.getUserId() == null || sellerInfoDto.getUserId() == 0L) {
			throw new UserEmptyException();
		}
		if (sellerInfoDto.getSellerId() == null || sellerInfoDto.getIsFocus() == null) {
			throw new ParamInvalidException("主播ID、关注类型均不可为空");
		}
		if (!SellerFocusStatusEnum.isExist(sellerInfoDto.getIsFocus())) {
			throw new ParamInvalidException("关注类型不合法");
		}
		UserInfo userInfo = userInfoManager.queryUserInfoById(sellerInfoDto);

		boolean result = this.saveSellerFocus(sellerInfoDto);
		if (!result) {
			logger.error("save seller focus error, " + sellerInfoDto);
			return new BaseResultDto(false, "操作失败，请重试！");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("save seller focus success, " + sellerInfoDto);
			}
		}

		// 站内信，暂未区分关注与取消关注
		SiteMessage siteMessage = SiteMessage.createSellerBeFocusSiteMessage(userInfo.getId(),
				sellerInfoDto.getSellerId());
		sendSiteManager.sendSite(siteMessage);

		return new BaseResultDto(true, "");
	}

	// 保存播主关注信息
	private boolean saveSellerFocus(SellerInfoDto sellerInfoDto) {

		SellerFocus sellerFocus = new SellerFocus();
		sellerFocus.setUserId(sellerInfoDto.getUserId());
		sellerFocus.setSellerId(sellerInfoDto.getSellerId());

		List<SellerFocus> list = sellerFocusManager.querySellerFocusList(sellerFocus);

		if (SellerFocusStatusEnum.FOCUS.getCode() == sellerInfoDto.getIsFocus()) {
			sellerFocus.setStatus(SellerFocusStatusEnum.FOCUS.getCode());
		} else {
			sellerFocus.setStatus(SellerFocusStatusEnum.UNFOCUS.getCode());
		}

		if (!CollectionUtils.isEmpty(list)) {
			sellerFocus.setId(list.get(0).getId());
			return sellerFocusManager.modifySellerFocus(sellerFocus) > 0;
		} else {
			return sellerFocusManager.addSellerFocus(sellerFocus) > 0;
		}
	}

}
