package com.baoke.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.ChannelShareTypeEnum;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.dto.ChannelShareDto;
import com.baoke.common.dto.api.SellerShareDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.service.ShareService;

/**
 * 分享serviceImplement
 * 
 * @author zdy
 * @date: 2018年7月9日 下午9:34:40
 */
@Service("shareService")
@ServiceDefinition(value = "shareService")
public class ShareServiceImpl implements ShareService {
	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private UserInfoManager userInfoManager;

	@MethodDefinition(value = "queryShareType")
	@Override
	public ChannelShareDto queryShareType(BaseDto baseDto) {
		String shareType = ChannelShareTypeEnum.WECHAT.getCode() + "," + ChannelShareTypeEnum.WECHAT_FRIEND.getCode()
				+ "," + ChannelShareTypeEnum.QQ.getCode() + "," + ChannelShareTypeEnum.QQ_ZONE.getCode();
		ChannelShareDto channelShareDto = new ChannelShareDto(shareType, true, null);
		return channelShareDto;
	}

	@MethodDefinition(value = "querySellerShare")
	@Override
	public SellerShareDto querySellerShare(SellerInfoDto sellerInfoDto) throws MainException {
		if (sellerInfoDto == null || sellerInfoDto.getSellerId() == null) {
			throw new ParamInvalidException("userId不能为空");
		}

		UserInfo userInfo = userInfoManager.queryUserInfoById(sellerInfoDto.getSellerId());
		if (userInfo == null) {
			throw new ParamInvalidException("主播不存在，请刷新重试");
		}

		// 查询该主播下最新一条视频
		VideoInfo videoInfo = new VideoInfo(sellerInfoDto.getSellerId(), VideoStatusEnum.ONLINE);
		List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByPage(videoInfo, new PageInfo(1, 1));

		SellerShareDto sellerShareDto = new SellerShareDto(true, "查询成功");
		if (CollectionUtil.isNotEmpty(videoInfoList)) {
			sellerShareDto.setImgUrl(videoInfoList.get(0).getIconImgUrl());
		}

		sellerShareDto.setIconImgUrl(userInfo.getHeadImgUrl());
		sellerShareDto.setTitle("主播分享");
		sellerShareDto.setContent("我是主播" + userInfo.getNickName() + "，有意思的视频电商就上追光星球!");
		sellerShareDto.setUrl(CommonConfig.ZHUI_GUANG_URL);
		sellerShareDto.setQrCodeUrl(CommonConfig.ZHUI_GUANG_URL);
		return sellerShareDto;
	}

}
