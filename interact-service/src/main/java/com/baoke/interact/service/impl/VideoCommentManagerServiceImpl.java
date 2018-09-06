package com.baoke.interact.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.api.VideoCommentGreatDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.dto.seller.VideoCommentAdminDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.interact.domain.VideoComment;
import com.baoke.interact.domain.VideoCommentGreat;
import com.baoke.interact.manager.VideoCommentGreatManager;
import com.baoke.interact.manager.VideoCommentManager;
import com.baoke.interact.service.VideoCommentManagerService;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.user.constant.UserBannedEnum;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserInfoManager;

@Service("videoCommentManagerService")
@ServiceDefinition(value = "videoCommentManagerService")
public class VideoCommentManagerServiceImpl implements VideoCommentManagerService {

	private static final Logger logger = Logger.getLogger(VideoCommentManagerServiceImpl.class);

	@Resource
	private VideoCommentManager videoCommentManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private VideoCommentGreatManager videoCommentGreatManager;

	@Resource
	private SendSiteManager sendSiteManager;

	@MethodDefinition(value = "saveVideoCommentGreat", needLogin = true)
	@Override
	public BaseResultDto saveVideoCommentGreat(VideoCommentGreatDto videoCommentGreatDto) throws ParamInvalidException {
		if (videoCommentGreatDto == null) {
			throw new ParamInvalidException("参数错误!");
		}

		UserInfo userInfo = userInfoManager.queryUserInfoById(videoCommentGreatDto);

		Long commentId = videoCommentGreatDto.getCommentId();
		if (commentId == null || videoCommentGreatDto.getIsGreat() == null) {
			logger.warn("saveVideoCommentGreat error! commentId=" + commentId + ",isGreat="
					+ videoCommentGreatDto.getIsGreat());
			throw new ParamInvalidException("参数错误!");
		}
		VideoComment videoComment = videoCommentManager.queryVideoCommentById(commentId, VideoCommentStatusEnum.NORMAL);
		if (null == videoComment) {
			logger.warn("save video comment great error, record is empty, commentId=" + commentId + ", userId="
					+ videoCommentGreatDto.getUserId());
			return new BaseResultDto(false, "该评论已被删除，请刷新重试");
		}

		boolean result = saveVideoCommentGreatInfo(videoCommentGreatDto);
		if (!result) {
			logger.error("save video comment great fail, " + videoCommentGreatDto);
			return new BaseResultDto(false, "操作失败，请重试！");
		}

		// 站内信
		SiteMessage siteMessage = SiteMessage.createCommentGreatSiteMessage(userInfo.getId(), videoComment.getUserId(),
				videoComment.getVideoId(), commentId, videoComment.getParentId(), videoComment.getSecondParentId(),
				videoComment.getContent());
		sendSiteManager.sendSite(siteMessage);

		return new BaseResultDto(true, "");
	}

	private boolean saveVideoCommentGreatInfo(VideoCommentGreatDto videoCommentGreatDto) {

		VideoCommentGreat videoCommentGreat = new VideoCommentGreat(videoCommentGreatDto.getCommentId(),
				videoCommentGreatDto.getUserId());
		if (GreatStatusEnum.GREAT.getCode() == videoCommentGreatDto.getIsGreat()) {
			videoCommentGreat.setStatus(GreatStatusEnum.GREAT.getCode());
		} else {
			videoCommentGreat.setStatus(GreatStatusEnum.UNGREAT.getCode());
		}

		List<VideoCommentGreat> videoCommentGreatList = videoCommentGreatManager.queryVideoCommentGreatList(
				new VideoCommentGreat(videoCommentGreatDto.getCommentId(), videoCommentGreatDto.getUserId()));
		if (CollectionUtils.isEmpty(videoCommentGreatList)) {
			return videoCommentGreatManager.addVideoCommentGreat(videoCommentGreat) > 0;
		} else {
			videoCommentGreat.setId(videoCommentGreatList.get(0).getId());
			return videoCommentGreatManager.modifyVideoCommentGreat(videoCommentGreat) > 0;
		}
	}

	@Override
	@MethodDefinition(value = "saveVideoComment", needLogin = true)
	public VideoCommentInfoDto saveVideoComment(VideoCommentInfoDto videoCommentDto) throws ParamInvalidException {
		if (videoCommentDto == null || videoCommentDto.getVideoId() == null || videoCommentDto.getVideoId() == 0L
				|| StringUtil.isEmpty(videoCommentDto.getContent())) {
			throw new ParamInvalidException("参数错误!");
		}

		UserInfo userInfo = userInfoManager.queryUserInfoById(videoCommentDto);
		if (null != userInfo.getBannedStatus() && UserBannedEnum.YES.getCode() == userInfo.getBannedStatus()) {
			logger.warn("save comment error! user banned comment, userId=" + videoCommentDto.getUserId());
			return new VideoCommentInfoDto(false, "不好意思您已被禁言，操作失败！");
		}

		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoCommentDto.getVideoId());
		if (videoInfo == null || VideoStatusEnum.ONLINE.getCode() != videoInfo.getStatus()) {
			logger.error("save comment error! video info is null or video banned comment, userId=" + userInfo.getId()
					+ ", videoId=" + videoCommentDto.getVideoId());
			return new VideoCommentInfoDto(false, "您要评论的视频暂时无法评论，请稍后重试");
		}

		VideoCommentInfoDto videoCommentInfoDto;
		if (videoCommentDto.getParentId() == null || videoCommentDto.getParentId() == 0) {
			videoCommentInfoDto = this.addVideoComment(videoInfo.getSellerId(), videoCommentDto);
		} else {
			videoCommentInfoDto = this.addReplyVideoComment(videoInfo.getSellerId(), videoCommentDto);
		}
		userInfo.convertVideoCommentInfoDto(videoCommentInfoDto);
		return videoCommentInfoDto;
	}

	// 发布视频评论，一级评论
	private VideoCommentInfoDto addVideoComment(long sellerId, VideoCommentInfoDto videoCommentDto) {
		VideoComment videoComment = new VideoComment(videoCommentDto, sellerId, 0L, 0L);
		long id = videoCommentManager.addVideoComment(videoComment);
		if (id == 0) {
			logger.error("save video comment info error," + videoComment);
			return new VideoCommentInfoDto(false, "操作失败，请重试！");
		}

		// 站内信
		SiteMessage siteMessage = SiteMessage.createSellerBeCommentMessage(videoComment.getUserId(), sellerId,
				videoComment.getVideoId(), videoCommentDto.getParentId(), videoComment.getParentId(),
				videoComment.getSecondParentId(), videoComment.getContent());
		sendSiteManager.sendSite(siteMessage);

		VideoComment videoCommentResult = videoCommentManager.queryVideoCommentById(id, VideoCommentStatusEnum.NORMAL);
		VideoCommentInfoDto videoCommentInfoDto = videoCommentResult.convert();
		videoCommentInfoDto.setSuccess(true);
		return videoCommentInfoDto;
	}

	// 回复视频评论
	private VideoCommentInfoDto addReplyVideoComment(long sellerId, VideoCommentInfoDto videoCommentDto) {
		// 回复评论，二级评论
		VideoComment parentCommentInfo = videoCommentManager.queryVideoCommentById(videoCommentDto.getParentId(),
				VideoCommentStatusEnum.NORMAL);
		if (parentCommentInfo == null) {
			logger.error("save reply comment info error, parent comment is null. userId=" + videoCommentDto.getUserId()
					+ ", parentCommentId=" + videoCommentDto.getParentId());
			return new VideoCommentInfoDto(false, "您要回复的评论已删除，请刷新重试");
		}

		long secondParentId = videoCommentDto.getParentId();
		if (parentCommentInfo.getParentId() != null && parentCommentInfo.getParentId() != 0) {
			secondParentId = parentCommentInfo.getSecondParentId();
		}

		VideoComment videoComment = new VideoComment(videoCommentDto, sellerId, videoCommentDto.getParentId(),
				secondParentId);
		long id = videoCommentManager.addVideoComment(videoComment);
		if (id == 0) {
			logger.error("save reply comment info error,  " + videoComment);
			return new VideoCommentInfoDto(false, "操作失败，请重试！");
		}

		// 站内信
		SiteMessage siteMessageToCommentUser = SiteMessage.createCommentInteractMessage(videoComment.getUserId(),
				parentCommentInfo.getUserId(), videoComment.getVideoId(), parentCommentInfo.getId(),
				parentCommentInfo.getParentId(), parentCommentInfo.getSecondParentId(), videoComment.getContent());
		sendSiteManager.sendSite(siteMessageToCommentUser); // 发送给评论者
		SiteMessage siteMessageToSelf = SiteMessage.createCommentInteractMessage(videoComment.getUserId(),
				videoComment.getUserId(), videoComment.getVideoId(), parentCommentInfo.getId(),
				parentCommentInfo.getParentId(), parentCommentInfo.getSecondParentId(), videoComment.getContent());
		sendSiteManager.sendSite(siteMessageToSelf); // 发送给自己

		VideoComment videoCommentResult = videoCommentManager.queryVideoCommentById(id, VideoCommentStatusEnum.NORMAL);
		VideoCommentInfoDto videoCommentInfoDto = videoCommentResult.convert();

		VideoCommentInfoDto parentCommentInfoDto = new VideoCommentInfoDto();
		if (!videoCommentInfoDto.getParentId().equals(videoCommentInfoDto.getSecondParentId())) {
			UserInfo userInfo = userInfoManager.queryUserInfoById(parentCommentInfo.getUserId());
			if (userInfo != null) {
				parentCommentInfoDto.setUserNick(userInfo.getNickName());
			}
			parentCommentInfoDto.setContent(parentCommentInfo.getContent());
			parentCommentInfoDto.setCommentId(parentCommentInfo.getId());
		}

		videoCommentInfoDto.setParentCommentInfo(parentCommentInfoDto);
		videoCommentInfoDto.setSuccess(true);
		return videoCommentInfoDto;
	}

	@Override
	public int modifyVideoCommentDeleteStatusByids(VideoCommentAdminDto videoCommentDto) throws ParamInvalidException {
		if (videoCommentDto == null || videoCommentDto.getIds() == null || videoCommentDto.getIds().size() == 0) {
			throw new ParamInvalidException("参数不能为空");
		}

		return videoCommentManager.modifyVideoCommentDeleteStatusByids(videoCommentDto.getIds(),
				VideoCommentStatusEnum.DISABLE);
	}

}
