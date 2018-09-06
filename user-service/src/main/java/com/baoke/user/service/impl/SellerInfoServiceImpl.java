package com.baoke.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.SellerAuditListDto;
import com.baoke.common.dto.info.SellerAuditInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.seller.SellerAudioStatusDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.IdCardUtil;
import com.baoke.common.util.SpecharsUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.item.manager.CategoryDictManager;
import com.baoke.user.constant.SellerStatusEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.constant.UserTypeEnum;
import com.baoke.user.domain.SellerInfo;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SellerInfoManager;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.service.SellerInfoService;

@ServiceDefinition(value = "sellerInfoService")
@Service("sellerInfoService")
public class SellerInfoServiceImpl implements SellerInfoService {

	private static final Logger logger = Logger.getLogger(SellerInfoServiceImpl.class);

	@Resource
	private SellerInfoManager sellerInfoManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private SendSiteManager sendSiteManager;

	@Resource
	private CategoryDictManager categoryDictManager;

	@Override
	public SellerAuditInfoDto querySellerAuditInfoBySellerId(Long sellerId) throws MainException {
		if (sellerId == null) {
			throw new ParamInvalidException("请先登录");
		}
		SellerInfo sellerInfo = sellerInfoManager.querySellerInfoBySellerId(sellerId);
		if (sellerInfo == null) {
			return new SellerAuditInfoDto(false, "未找到用户信息，请刷新重试");
		}

		return sellerInfo.convert();
	}

	@Override
	public SellerAudioStatusDto querySellerAudioStatus(long sellerId) throws MainException {

		SellerInfo sellerInfo = sellerInfoManager.querySellerInfoBySellerId(sellerId);

		SellerAudioStatusDto sellerInforStatusDto = new SellerAudioStatusDto();
		if (null == sellerInfo) {
			sellerInforStatusDto.setStatus(SellerStatusEnum.NOT_SELLER.getCode());
		} else {
			sellerInforStatusDto.setStatus(sellerInfo.getStatus());
		}

		return sellerInforStatusDto;
	}

	@Override
	public SellerAuditListDto querySellerAuditInfoByName(SellerInfoDto sellerInfoDto) throws MainException {
		List<SellerInfo> sellerInfoList = sellerInfoManager
				.querySellerInfoByNickNameAndStatus(sellerInfoDto.getCategoryIds(),
						null == sellerInfoDto.getStatus() ? null
								: SellerStatusEnum.getByCode(sellerInfoDto.getStatus()),
						sellerInfoDto.getSellerNickName(), sellerInfoDto.getPageInfo());

		List<SellerAuditInfoDto> sellerAuditInfoList = new ArrayList<>();
		if (sellerInfoList != null && sellerInfoList.size() > 0) {
			for (SellerInfo sellerInfo : sellerInfoList) {
				SellerAuditInfoDto sellerAuditInfo = sellerInfo.convert();
				List<String> ids = Arrays.asList(sellerInfo.getCategoryIds().split(","));
				sellerAuditInfo.setCategoryNames(categoryDictManager.queryCategoryDictByids(ids));
				sellerAuditInfoList.add(sellerAuditInfo);
			}
		}

		int total = sellerInfoManager.countSellerInfoByNickNameAndStatus(sellerInfoDto.getCategoryIds(),
				null == sellerInfoDto.getStatus() ? null : SellerStatusEnum.getByCode(sellerInfoDto.getStatus()),
				sellerInfoDto.getSellerNickName());
		sellerInfoDto.getPageInfo().setTotal(total);

		return new SellerAuditListDto(sellerAuditInfoList, sellerInfoDto.getPageInfo());
	}

	@Override
	public int saveSellerInfo(SellerAuditInfoDto sellerAuditInfoDto) throws MainException {

		checkSellerInfoParam(sellerAuditInfoDto);

		SellerInfo querySellerInfo = sellerInfoManager.querySellerInfoBySellerId(sellerAuditInfoDto.getSellerId());
		if (null != querySellerInfo) {
			if (SellerStatusEnum.SELLER_CENTER.getCode() == querySellerInfo.getStatus()
					|| SellerStatusEnum.SELLER_PASS.getCode() == querySellerInfo.getStatus()) {
				throw new ParamInvalidException("已认证或者认证中");
			}
		}
		// 查询昵称是否合法
		if (SpecharsUtil.isSpechar(sellerAuditInfoDto.getSellerNickName())) {
			throw new ParamInvalidException("主播昵称不能为特殊字符");
		}
		if (userInfoManager.countUserInfoByNickName(sellerAuditInfoDto.getSellerNickName(), UserStatusEnum.NORMAL) > 0
				|| sellerInfoManager.countSellerInfoByNickName(SellerStatusEnum.SELLER_CENTER,
						sellerAuditInfoDto.getSellerNickName()) > 0) {
			throw new ParamInvalidException("主播昵称已被使用");
		}

		SellerInfo sellerInfo = new SellerInfo(sellerAuditInfoDto);
		logger.info("save seller info, " + sellerInfo);
		// 重新提交覆盖原来记录
		if (null != querySellerInfo && SellerStatusEnum.SELLER_REFUSE.getCode() == querySellerInfo.getStatus()) {
			return sellerInfoManager.modifySellerInfoBySellerId(sellerInfo);
		}

		return sellerInfoManager.addSellerInfo(sellerInfo);
	}

	private void checkSellerInfoParam(SellerAuditInfoDto sellerAuditInfoDto) throws ParamInvalidException {
		if (sellerAuditInfoDto == null) {
			throw new ParamInvalidException("认证信息不能为空");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getRealName())) {
			throw new ParamInvalidException("姓名不能为空");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getIdCard())
				|| IdCardUtil.isIDNumber(sellerAuditInfoDto.getIdCard())) {
			throw new ParamInvalidException("身份证号码不正确");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getSellerNickName())) {
			throw new ParamInvalidException("主播名称不能为空");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getBankCardNo())) {
			throw new ParamInvalidException("银行卡号不能为空");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getDepositBank())) {
			throw new ParamInvalidException("开户行不能为空");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getContactPhone())
				|| !StringUtil.inNumber(sellerAuditInfoDto.getContactPhone())) {
			throw new ParamInvalidException("客服电话不正确");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getIdCardImgUrl())) {
			throw new ParamInvalidException("手持身份证照片不能为空");
		}
		if (StringUtil.isBlank(sellerAuditInfoDto.getCategoryIds())) {
			throw new ParamInvalidException("店铺分类不能为空");
		}
	}

	@Override
	public boolean modifySellerAuditResult(SellerAuditInfoDto sellerAuditInfoDto) throws MainException {
		if (sellerAuditInfoDto == null || sellerAuditInfoDto.getSellerId() == null) {
			throw new ParamInvalidException("主播的id不能为空");
		}

		if (SellerStatusEnum.SELLER_PASS.getCode() == sellerAuditInfoDto.getStatus()) {
			SellerInfo sellerInfo = sellerInfoManager.querySellerInfoBySellerId(sellerAuditInfoDto.getSellerId());
			UserInfo userInfo = new UserInfo();
			userInfo.setId(sellerAuditInfoDto.getSellerId());
			userInfo.setNickName(sellerInfo.getNickName()); // 此处需要做用户昵称排重校验
			userInfo.setType(UserTypeEnum.SELLER.getCode());
			userInfoManager.modifyUserInfoById(userInfo);
		}

		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setSellerId(sellerAuditInfoDto.getSellerId());
		sellerInfo.setAuditResult(sellerAuditInfoDto.getAuditResult());
		sellerInfo.setStatus(sellerAuditInfoDto.getStatus());
		sellerInfoManager.modifySellerInfoBySellerId(sellerInfo);

		String content = null;
		if (SellerStatusEnum.SELLER_PASS.getCode() == sellerAuditInfoDto.getStatus()) {
			content = "已认证";
		} else {
			content = "认证失败" + sellerAuditInfoDto.getAuditResult();
		}
		String title = "主播认证";
		SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID,
				sellerAuditInfoDto.getSellerId(), null, title, content);
		sendSiteManager.sendSite(siteMessage);

		return true;
	}
}
