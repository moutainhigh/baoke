package com.baoke.interact.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.api.VideoCommentListDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.VideoCommentAdminDto;
import com.baoke.common.dto.seller.VideoCommentAdminListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.interact.domain.BlackKeyWord;
import com.baoke.interact.domain.VideoComment;
import com.baoke.interact.domain.VideoCommentGreat;
import com.baoke.interact.manager.BlackKeyWordManager;
import com.baoke.interact.manager.VideoCommentGreatManager;
import com.baoke.interact.manager.VideoCommentManager;
import com.baoke.interact.service.VideoCommentService;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.UserInfoManager;

@Service("videoCommentService")
@ServiceDefinition(value = "videoCommentService")
public class VideoCommentServiceImpl implements VideoCommentService {

	private static final Logger logger = Logger.getLogger(VideoCommentServiceImpl.class);

	@Resource
	private VideoCommentManager videoCommentManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private VideoCommentGreatManager videoCommentGreatManager;

	@Resource
	private BlackKeyWordManager blackKeyWordManager;

	@MethodDefinition(value = "queryVideoComment")
	@Override
	public VideoCommentListDto queryVideoCommentListByVideoId(VideoInfoDto videoInfoDto) throws ParamInvalidException {
		if (videoInfoDto == null || videoInfoDto.getVideoId() == null || videoInfoDto.getVideoId() == 0L) {
			throw new ParamInvalidException("参数错误!");
		}

		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoInfoDto.getVideoId());

		List<BlackKeyWord> blackKeyWordList = blackKeyWordManager.queryBlackKeyWordListByStatus(BooleanEnum.TRUE);

		VideoCommentListDto videoCommentListDto = this.convertVideoComment(videoInfoDto.getVideoId(), 0L,
				videoInfoDto.getUserId(), videoInfoDto.getPageInfo(), videoInfo.getSellerId(), blackKeyWordList);
		videoCommentListDto.setSuccess(true);
		return videoCommentListDto;
	}

	@MethodDefinition(value = "queryReplyVideoComment")
	@Override
	public VideoCommentListDto queryReplyVideoCommentListById(VideoCommentInfoDto videoCommentDto)
			throws ParamInvalidException {
		if (videoCommentDto == null || videoCommentDto.getVideoId() == null || videoCommentDto.getVideoId() == 0L
				|| videoCommentDto.getSecondParentId() == null || videoCommentDto.getSecondParentId() == 0L) {
			throw new ParamInvalidException("参数错误!");
		}

		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoCommentDto.getVideoId());
		if (null == videoInfo) {
			logger.warn("query reply video comment error, video is empty. videoId=" + videoCommentDto.getVideoId());
			return new VideoCommentListDto(false, "视频已被删除");
		}

		VideoComment videoComment = videoCommentManager.queryVideoCommentById(videoCommentDto.getSecondParentId(),
				VideoCommentStatusEnum.NORMAL);
		if (null == videoComment) {
			logger.warn("query reply video comment error, video is empty. videoId=" + videoCommentDto.getVideoId());
			return new VideoCommentListDto(false, "当前评论已被删除，无法继续回复");
		}

		// 查询视频评论信息
		boolean isSeller = false;
		if (videoComment.getUserId() == videoInfo.getSellerId()) {
			isSeller = true;
		}

		List<BlackKeyWord> blackKeyWordList = blackKeyWordManager.queryBlackKeyWordListByStatus(BooleanEnum.TRUE);

		VideoCommentInfoDto videoCommentDtoResult = this.convertVideoCommentInfo(videoComment,
				videoCommentDto.getUserId(), isSeller, blackKeyWordList);

		// 查询回复评论信息
		VideoCommentListDto replyVideoCommentDto = this.convertVideoComment(videoCommentDto.getVideoId(),
				videoCommentDto.getSecondParentId(), videoCommentDto.getUserId(), videoCommentDto.getPageInfo(),
				videoInfo.getSellerId(), blackKeyWordList);

		List<VideoCommentInfoDto> videoCommentDtoList = convertVideoCommentDtoList(
				replyVideoCommentDto.getCommentList());

		VideoCommentListDto videoCommentListDto = new VideoCommentListDto(replyVideoCommentDto.getCommentNum(),
				videoCommentDtoList, videoCommentDtoResult, replyVideoCommentDto.getPageInfo());
		videoCommentListDto.setSuccess(true);
		return videoCommentListDto;
	}

	// 查询视频评论的点赞数和视频的评论数及对应评论人信息
	private VideoCommentInfoDto convertVideoCommentInfo(VideoComment videoComment, Long currentUserId, boolean isSeller,
			List<BlackKeyWord> blackKeyWordList) {
		VideoCommentInfoDto videoCommentDto = videoComment.convert(isSeller, blackKeyWordList);
		VideoComment videoCommentParam = new VideoComment(videoComment.getVideoId(), null, videoComment.getId(),
				VideoCommentStatusEnum.NORMAL);
		int commentNum = videoCommentManager.countVideoComment(videoCommentParam);
		videoCommentDto.setCommentNum(commentNum);

		UserInfo userInfo = userInfoManager.queryUserInfoById(videoComment.getUserId());
		userInfo.convertVideoCommentInfoDto(videoCommentDto);

		VideoCommentGreat videoCommentGreat = new VideoCommentGreat(videoComment.getId(), GreatStatusEnum.GREAT);
		int commentGreatNum = videoCommentGreatManager.countVideoCommentGreat(videoCommentGreat);
		videoCommentDto.setGreatNum(commentGreatNum);

		// 当前登陆用户对该条评论的点赞情况
		if (currentUserId != null && currentUserId > 0) {
			videoCommentGreat.setUserId(currentUserId);
			int commentGreatMyNum = videoCommentGreatManager.countVideoCommentGreat(videoCommentGreat);
			if (commentGreatMyNum > 0) {
				videoCommentDto.setIsGreat(GreatStatusEnum.GREAT.getCode());
			} else {
				videoCommentDto.setIsGreat(GreatStatusEnum.UNGREAT.getCode());
			}
		}
		return videoCommentDto;
	}

	// 回复评论信息封装
	private List<VideoCommentInfoDto> convertVideoCommentDtoList(List<VideoCommentInfoDto> videoCommentInfoDtoList) {
		List<VideoCommentInfoDto> videoCommentDtoList = new ArrayList<>();
		for (VideoCommentInfoDto commentInfoDto : videoCommentInfoDtoList) {
			if (commentInfoDto.getParentId() == null || commentInfoDto.getParentId() <= 0) {
				continue;
			}

			// 查找被回复评论信息
			VideoCommentInfoDto parentCommentInfoDto = new VideoCommentInfoDto();
			for (VideoCommentInfoDto commentTemp : videoCommentInfoDtoList) {
				if (!commentInfoDto.getParentId().equals(commentTemp.getCommentId())) {
					continue;
				}
				parentCommentInfoDto.setUserNick(commentTemp.getUserNick());
				parentCommentInfoDto.setContent(commentTemp.getContent());
				parentCommentInfoDto.setCommentId(commentTemp.getCommentId());
				break;
			}
			commentInfoDto.setParentCommentInfo(parentCommentInfoDto);
			videoCommentDtoList.add(commentInfoDto);
		}
		return videoCommentDtoList;
	}

	private VideoCommentListDto convertVideoComment(Long videoId, Long secondParentId, Long currentUserId,
			PageInfo pageInfo, Long videoSellerId, List<BlackKeyWord> blackKeyWordList) {
		VideoComment videoComment = new VideoComment(videoId, null, secondParentId, VideoCommentStatusEnum.NORMAL);
		int countNum = videoCommentManager.countVideoComment(videoComment);

		List<VideoComment> commentList = new ArrayList<>();
		if (secondParentId == 0) {
			commentList = videoCommentManager.queryVideoCommentListByPage(videoComment, pageInfo);
		} else {
			commentList = videoCommentManager.queryReplyVideoCommentListByPage(videoComment, pageInfo);
		}

		List<VideoCommentInfoDto> videoCommentInfoDtoList = new ArrayList<VideoCommentInfoDto>();
		for (VideoComment videoCommentTemp : commentList) {
			boolean isSeller = false;
			if (videoCommentTemp.getUserId() == videoSellerId) {
				isSeller = true;
			}
			VideoCommentInfoDto videoCommentDto = this.convertVideoCommentInfo(videoCommentTemp, currentUserId,
					isSeller, blackKeyWordList);

			videoCommentInfoDtoList.add(videoCommentDto);
		}
		return new VideoCommentListDto(countNum, videoCommentInfoDtoList, pageInfo);
	}

	@Override
	public VideoCommentAdminListDto queryCommentListByCondition(VideoCommentAdminDto videoCommentQueryDto) {

		List<Long> userIds = new ArrayList<>();
		if (StringUtil.hasLength(videoCommentQueryDto.getNickName())) {
			List<UserInfo> userInfoList = userInfoManager.queryUserInfoByNickName(videoCommentQueryDto.getNickName());
			if (CollectionUtil.isEmpty(userInfoList)) {
				logger.error("query comment list error, user is empty. nickName=" + videoCommentQueryDto.getNickName());
				return new VideoCommentAdminListDto(new PageInfo());
			}
			for (UserInfo userInfo : userInfoList) {
				userIds.add(userInfo.getId());
			}
		}
		List<Long> videoIds = new ArrayList<>();
		if (videoCommentQueryDto.getSellerId() != null) {
			List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByTitleAndSellerId(
					videoCommentQueryDto.getTitle(), videoCommentQueryDto.getSellerId(), VideoStatusEnum.ONLINE);

			if (CollectionUtil.isEmpty(videoInfoList)) {
				logger.error(
						"query comment list error, video is empty. sellerId=" + videoCommentQueryDto.getSellerId());
				return new VideoCommentAdminListDto(new PageInfo());
			}
			for (VideoInfo videoInfo : videoInfoList) {
				videoIds.add(videoInfo.getId());
			}
		}
		if (videoCommentQueryDto.getSellerId() == null && StringUtil.hasLength(videoCommentQueryDto.getTitle())) {
			List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByTitleAndSellerId(
					videoCommentQueryDto.getTitle(), videoCommentQueryDto.getSellerId(), VideoStatusEnum.ONLINE);
			if (CollectionUtil.isEmpty(videoInfoList)) {
				logger.error("query comment list error, video is empty,  sellerId=" + videoCommentQueryDto.getSellerId()
						+ ", title=" + videoCommentQueryDto.getTitle());
				return new VideoCommentAdminListDto(new PageInfo());
			}
			for (VideoInfo videoInfo : videoInfoList) {
				videoIds.add(videoInfo.getId());
			}
		}
		PageInfo pageInfo = videoCommentQueryDto.getPageInfo();

		List<VideoComment> videoCommentList = videoCommentManager.queryVideoCommentByVideoIdsAndUserIdsAndContent(
				videoIds, userIds, videoCommentQueryDto.getContent(), VideoCommentStatusEnum.NORMAL, pageInfo);
		List<VideoCommentAdminDto> VideoCommentDtoList = new ArrayList<>();
		for (VideoComment videoComment : videoCommentList) {

			VideoCommentAdminDto videoCommentAdminDto = new VideoCommentAdminDto();

			// VideoCommentInfo转VideoCommentInfoDto ，主播平台查看评论需过滤敏感词，admin平台不过滤
			VideoCommentInfoDto videoCommentInfoDto = null;
			if (videoCommentQueryDto.getSellerId() != null) {
				List<BlackKeyWord> blackKeyWordList = blackKeyWordManager
						.queryBlackKeyWordListByStatus(BooleanEnum.TRUE);
				videoCommentInfoDto = videoComment.convert(blackKeyWordList);
			} else {
				videoCommentInfoDto = videoComment.convert();
			}

			UserInfo userInfo = userInfoManager.queryUserInfoById(videoComment.getUserId());
			userInfo.convertVideoCommentInfoDto(videoCommentInfoDto);
			videoCommentAdminDto.setBannedStatus(userInfo.getBannedStatus());
			int commentNum = videoCommentManager
					.countVideoComment(new VideoComment(videoComment.getId(), BooleanEnum.TRUE));
			videoCommentInfoDto.setCommentNum(commentNum);
			videoCommentAdminDto.setCommentInfo(videoCommentInfoDto);

			// 外层视频标题 被回复人
			VideoComment videoCommentResult = videoCommentManager.queryVideoCommentById(videoComment.getParentId(),
					VideoCommentStatusEnum.NORMAL);
			if (videoCommentResult != null) {
				UserInfo userInfoResult = userInfoManager.queryUserInfoById(videoCommentResult.getUserId());
				if (userInfoResult != null) {
					videoCommentAdminDto.setToUserId(userInfoResult.getId());
					videoCommentAdminDto.setToUserNick(userInfoResult.getNickName());
				}
			}
			VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoComment.getVideoId());
			if (videoInfo != null) {
				videoCommentAdminDto.setTitle(videoInfo.getTitle());
			}

			VideoCommentDtoList.add(videoCommentAdminDto);
		}

		int count = videoCommentManager.countVideoCommentByVideoIdsAndUserIdsAndContent(
				videoCommentQueryDto.getContent(), videoIds, userIds, VideoCommentStatusEnum.NORMAL);
		pageInfo.setTotal(count);

		return new VideoCommentAdminListDto(VideoCommentDtoList, pageInfo);
	}

}
