package com.baoke.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.IsReadEnum;
import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.dto.UserDto;
import com.baoke.common.dto.UserListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.UserEmptyException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.domain.SellerFocus;
import com.baoke.interact.domain.VideoGreat;
import com.baoke.interact.manager.MessageNotifyManager;
import com.baoke.interact.manager.SellerFocusManager;
import com.baoke.interact.manager.VideoGreatManager;
import com.baoke.item.manager.VideoItemRelationManager;
import com.baoke.user.constant.SellerStatusEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.constant.UserTypeEnum;
import com.baoke.user.domain.SellerInfo;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.domain.UserWechatInfo;
import com.baoke.user.manager.SellerInfoManager;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.manager.UserWechatInfoManager;
import com.baoke.user.service.UserInfoService;

import net.sf.cglib.beans.BeanCopier;

@ServiceDefinition(value = "userInfoService")
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	private static final Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private VideoItemRelationManager videoItemRelationManager;

	@Resource
	private SellerFocusManager sellerFocusManager;

	@Resource
	private VideoGreatManager videoGreatManager;

	@Resource
	private MessageNotifyManager messageNotifyManager;

	@Resource
	private UserWechatInfoManager userWechatInfoManager;

	@Resource
	private SellerInfoManager sellerInfoManager;

	@Override
	public UserInfoDto queryUserInfoById(BaseDto baseDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfo(new UserInfo(baseDto.getUserId(), UserStatusEnum.NORMAL));
		if (null == userInfo) {
			return null;
		}

		UserInfoDto userInfoDto = new UserInfoDto(baseDto.getUserId(), baseDto.getDeviceId(), userInfo.getHeadImgUrl(),
				userInfo.getNickName(), userInfo.getSex(), userInfo.getType());
		userInfoDto.setPhone(userInfo.getPhone());

		userInfoDto.setFocusNum(sellerFocusManager
				.countSellerFocus(new SellerFocus(userInfo.getId(), SellerFocusStatusEnum.FOCUS, null)));

		userInfoDto.setGreatNum(videoGreatManager
				.countVideoGreatNum(new VideoGreat(userInfo.getId(), GreatStatusEnum.GREAT, null)));

		userInfoDto.setUnReadMsgNum(messageNotifyManager.countMessageNotifyByUserIdAndIsRead(baseDto.getUserId(),
				IsReadEnum.UN_READ.getCode()));

		userInfoDto.setIsBindPhone(StringUtil.hasLength(userInfo.getPhone()) ? 1 : 0);
		userInfoDto.setIsBindWechat(StringUtil.hasLength(userInfo.getUnionId()) ? 1 : 0);

		if (StringUtil.hasLength(userInfo.getUnionId())) {
			UserWechatInfo userWechatInfo = userWechatInfoManager.queryUserWechatInfo(
					new UserWechatInfo(null, userInfo.getUnionId(), WechatUserSourceTypeEnum.MOBILE_APP));
			if (userWechatInfo != null) {
				userInfoDto.setWechatNickName(userWechatInfo.getNickName());
			}
		}

		// 添加禁言信息
		userInfoDto.setBannedEndTime(null == userInfo.getBannedEndTime() ? 0 : userInfo.getBannedEndTime().getTime());
		userInfoDto.setBannedStatus(userInfo.getBannedStatus());
		userInfoDto.setBannedReason(userInfo.getBannedReason());

		// 添加主播认证状态
		SellerInfo sellerInfo = sellerInfoManager.querySellerInfoBySellerId(baseDto.getUserId());
		userInfoDto.setAuthStatus((null == sellerInfo || null == sellerInfo.getStatus())
				? SellerStatusEnum.NOT_SELLER.getCode() : sellerInfo.getStatus());
		// 如果为播主则查询播主信息
		if (userInfo.getType() == UserTypeEnum.SELLER.getCode()) {
			userInfoDto.setSellerInfo(this.querySellerInfoById(new SellerInfoDto(userInfo.getId()), userInfo));
		}

		return userInfoDto;
	}

	@Override
	@MethodDefinition(value = "querySellerInfoById", needLogin = true)
	public SellerInfoDto querySellerInfoById(SellerInfoDto sellerInfoDto) throws MainException {
		SellerInfoDto sellerInfo = querySellerInfoById(sellerInfoDto, null);
		sellerInfo.setSuccess(true);
		return sellerInfo;
	}

	public SellerInfoDto querySellerInfoById(SellerInfoDto sellerInfoDto, UserInfo userInfo) throws MainException {

		if (null == sellerInfoDto || null == sellerInfoDto.getSellerId()) {
			throw new ParamInvalidException();
		}

		int beFocusNum = sellerFocusManager
				.countSellerFocus(new SellerFocus(null, SellerFocusStatusEnum.FOCUS, sellerInfoDto.getSellerId()));

		int videoNum = videoItemRelationManager.countVideoItemRelationBySellerId(sellerInfoDto.getSellerId());

		VideoGreat videoGreat = new VideoGreat(GreatStatusEnum.GREAT, sellerInfoDto.getSellerId(), null);
		int beGreatNum = videoGreatManager.countVideoGreatNum(videoGreat);

		int isFocus = 0;
		if (sellerInfoDto.getUserId() != null && sellerInfoDto.getUserId() != 0) {
			isFocus = sellerFocusManager.querySellerFocusStatus(sellerInfoDto.getUserId(), sellerInfoDto.getSellerId());
		}

		if (null == userInfo) {
			userInfo = userInfoManager.queryUserInfo(new UserInfo(sellerInfoDto.getSellerId(), UserStatusEnum.NORMAL));
		}
		if (null != userInfo) {
			sellerInfoDto.setSellerImgUrl(userInfo.getHeadImgUrl());
			sellerInfoDto.setSellerNickName(userInfo.getNickName());
		}
		sellerInfoDto.setBeFocusNum(beFocusNum);
		sellerInfoDto.setVideoNum(videoNum);
		sellerInfoDto.setBeGreatNum(beGreatNum);
		sellerInfoDto.setIsFocus(isFocus);

		return sellerInfoDto;
	}

	@Override
	@MethodDefinition(value = "queryMyUserInfo", needLogin = true)
	public UserInfoDto queryUserInfo(UserInfoDto userInfoDto) throws MainException {

		if (null == userInfoDto || null == userInfoDto.getUserId()) {
			throw new ParamInvalidException();
		}
		long userId = userInfoDto.getUserId();
		userInfoDto = this.queryUserInfoById(new BaseDto(userId, userInfoDto.getDeviceId()));
		if (null == userInfoDto) {
			logger.error("query userInfo error, userInfo is null. userId=" + userId);
			throw new UserEmptyException();
		}
		userInfoDto.setSuccess(true);
		return userInfoDto;
	}

	@Override
	public UserListDto queryUserInfoListByPage(UserDto userDto) throws MainException {
		UserInfo userData = convertUserInfoDtoToInfo(userDto.getUserInfo());
		List<UserInfo> userInfoList = userInfoManager.queryUserInfoListByPage(userData, userDto.getPageInfo());
		int total = userInfoManager.countUserInfo(userData);

		userDto.getPageInfo().setTotal(total);

		List<UserInfoDto> userInfoDtoList = new ArrayList<>();
		for (UserInfo userInfo : userInfoList) {
			userInfoDtoList.add(queryUserInfoById(new BaseDto(userInfo.getId(), userDto.getUserInfo().getDeviceId())));
		}
		return new UserListDto(userInfoDtoList, userDto.getPageInfo());
	}

	public static UserInfo convertUserInfoDtoToInfo(UserInfoDto userInfoDto) {
		UserInfo userInfo = new UserInfo();
		final BeanCopier bc = BeanCopier.create(userInfoDto.getClass(), userInfo.getClass(), false);
		bc.copy(userInfoDto, userInfo, null);
		userInfo.setId(userInfoDto.getUserId());
		userInfo.setType(userInfoDto.getUserType());
		return userInfo;
	}

}
