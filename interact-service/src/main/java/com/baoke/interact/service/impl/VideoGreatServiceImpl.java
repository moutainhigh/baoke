package com.baoke.interact.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.api.VideoGreatDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.interact.constant.VideoDeLikeStatusEnum;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.domain.VideoGreat;
import com.baoke.interact.manager.VideoGreatManager;
import com.baoke.interact.service.VideoGreatService;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserInfoManager;

/**
 * 视频点赞(喜欢)
 * 
 * @author: zdy
 * @date: 2018年6月12日
 */
@Service("videoGreatService")
@ServiceDefinition(value = "videoGreatService")
public class VideoGreatServiceImpl implements VideoGreatService {

	private static final Logger logger = Logger.getLogger(VideoGreatServiceImpl.class);

	@Resource
	private VideoGreatManager videoGreatManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private SendSiteManager sendSiteManager;

	@MethodDefinition(value = "saveVideoGreat", needLogin = true)
	@Override
	public BaseResultDto saveVideoGreat(VideoGreatDto videoGreatDto) throws ParamInvalidException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(videoGreatDto);

		if (null == videoGreatDto.getVideoId() || null == videoGreatDto.getIsGreat()) {
			throw new ParamInvalidException("参数不合法");
		}
		if (null != videoGreatDto.getIsGreat()) {
			if (!GreatStatusEnum.isExist(videoGreatDto.getIsGreat())) {
				throw new ParamInvalidException("点赞状态不合法");
			}
		}

		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoGreatDto.getVideoId());
		if (videoInfo == null || videoInfo.getSellerId() == null) {
			throw new ParamInvalidException("视频信息有误！");
		}

		VideoGreat videoGreat = new VideoGreat();
		videoGreat.setVideoId(videoGreatDto.getVideoId());
		videoGreat.setUserId(videoGreatDto.getUserId());
		videoGreat.setSellerId(videoInfo.getSellerId());
		List<VideoGreat> videoGreatList = videoGreatManager.queryVideoGreatList(videoGreat);

		if (GreatStatusEnum.GREAT.getCode() == videoGreatDto.getIsGreat()) {
			videoGreat.setGreatStatus(GreatStatusEnum.GREAT.getCode());
		} else {
			videoGreat.setGreatStatus(GreatStatusEnum.UNGREAT.getCode());
		}

		if (CollectionUtil.isNotEmpty(videoGreatList)) { // 修改
			videoGreat.setId(videoGreatList.get(0).getId());
			videoGreatManager.modifyVideoGreat(videoGreat);
		} else {
			videoGreat.setDelikeStatus(VideoDeLikeStatusEnum.LIKE.getCode());
			videoGreatManager.addVideoGreat(videoGreat);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("save user video great success, " + videoGreat);
		}

		// 站内信，暂未区分点赞与取消点赞
		SiteMessage siteMessage = SiteMessage.createSellerBeGreatSiteMessage(userInfo.getId(), videoInfo.getSellerId(),
				videoGreatDto.getVideoId());
		sendSiteManager.sendSite(siteMessage);

		return new BaseResultDto(true, "");
	}

	@MethodDefinition(value = "saveVideoDelike", needLogin = true)
	@Override
	public BaseResultDto saveVideoDelike(VideoGreatDto videoGreatDto) throws ParamInvalidException {
		if (null == videoGreatDto || null == videoGreatDto.getUserId() || null == videoGreatDto.getVideoId()
				|| null == videoGreatDto.getIsDelike()) {
			throw new ParamInvalidException("参数不合法");
		}
		if (null != videoGreatDto.getIsDelike()) {
			if (!VideoDeLikeStatusEnum.isExist(videoGreatDto.getIsDelike())) {
				throw new ParamInvalidException("喜欢(不喜欢)状态不合法");
			}
		}
		if (null == userInfoManager.queryUserInfoById(videoGreatDto.getUserId())) {
			throw new ParamInvalidException("用户信息不合法");
		}

		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoGreatDto.getVideoId());
		if (videoInfo == null || videoInfo.getSellerId() == null) {
			throw new ParamInvalidException("视频信息有误！");
		}

		VideoGreat videoGreat = new VideoGreat();
		videoGreat.setVideoId(videoGreatDto.getVideoId());
		videoGreat.setUserId(videoGreatDto.getUserId());
		videoGreat.setSellerId(videoInfo.getSellerId());
		List<VideoGreat> videoGreatList = videoGreatManager.queryVideoGreatList(videoGreat);

		videoGreat.setDelikeStatus(videoGreatDto.getIsDelike());

		if (CollectionUtil.isNotEmpty(videoGreatList)) { // 修改
			videoGreat.setId(videoGreatList.get(0).getId());
			videoGreatManager.modifyVideoGreat(videoGreat);
		} else {
			videoGreat.setGreatStatus(VideoDeLikeStatusEnum.DELIKE.getCode());
			videoGreatManager.addVideoGreat(videoGreat);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("save user video delike success, " + videoGreat);
		}

		return new BaseResultDto(true, "");
	}

}
