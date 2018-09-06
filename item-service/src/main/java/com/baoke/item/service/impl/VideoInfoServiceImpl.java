package com.baoke.item.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baoke.act.domain.BannerConfig;
import com.baoke.act.manager.BannerConfigManager;
import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BannerScenTypeEnum;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.VideoListDto;
import com.baoke.common.dto.api.RecommendHomeDto;
import com.baoke.common.dto.api.VideoDetailDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BannerConfigDto;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.dto.wechat.VideoInfoListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.interact.domain.SellerFocus;
import com.baoke.interact.domain.VideoComment;
import com.baoke.interact.domain.VideoGreat;
import com.baoke.interact.manager.SellerFocusManager;
import com.baoke.interact.manager.VideoCommentManager;
import com.baoke.interact.manager.VideoGreatManager;
import com.baoke.item.constant.VideoDetailQueryScenEnum;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.domain.VideoItemRelation;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.item.manager.ItemInfoManager;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.item.manager.VideoItemRelationManager;
import com.baoke.item.service.VideoInfoService;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.UserInfoManager;

@Service("videoInfoService")
@ServiceDefinition("videoInfoService")
public class VideoInfoServiceImpl implements VideoInfoService {

	private static final Logger logger = Logger.getLogger(VideoInfoServiceImpl.class);

	@Resource
	private BannerConfigManager bannerConfigManager;

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private ItemInfoManager itemInfoManager;

	@Resource
	private VideoItemRelationManager videoItemRelationManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private SellerFocusManager sellerFocusManager;

	@Resource
	private VideoGreatManager videoGreatManager;

	@Resource
	private VideoCommentManager videoCommentManager;

	@Resource
	private ItemDetailInfoManager itemDetailInfoManager;

	@MethodDefinition(value = "queryRecommendHome")
	@Override
	public RecommendHomeDto queryRecommendHome(VideoInfoDto videoInfoDto) {
		// Banner信息
		List<BannerConfig> bannerInfoList = bannerConfigManager
				.queryBannerConfigByStatusAndScenType(BannerScenTypeEnum.APP_INDEX_TOP_BANNER, BooleanEnum.TRUE);
		List<BannerConfigDto> bannerList = this.convertBannerResp(bannerInfoList);

		// 视频及相关信息，并封装数据
		List<VideoInfo> videoInfoList = videoInfoManager
				.queryVideoInfoListByPage(new VideoInfo(null, VideoStatusEnum.ONLINE), videoInfoDto.getPageInfo());
		List<VideoInfoDto> respVideoList = queryDetailOther(videoInfoList, videoInfoDto.getUserId());

		return new RecommendHomeDto(true, "", bannerList, respVideoList, videoInfoDto.getPageInfo());
	}

	@Override
	public VideoInfoListDto queryWechatHome(VideoInfoDto videoInfoDto) {
		VideoInfo videoInfo = new VideoInfo(null, VideoStatusEnum.ONLINE);
		PageInfo pageInfo = videoInfoDto.getPageInfo();
		pageInfo.setTotal(videoInfoManager.countVideoInfo(videoInfo));
		List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByPage(videoInfo, pageInfo);
		List<VideoInfoDto> videoInfoDtoList = queryDetailOther(videoInfoList, videoInfoDto.getUserId());
		return new VideoInfoListDto(videoInfoDtoList, pageInfo);
	}

	@MethodDefinition(value = "queryFollowVideo", needLogin = true)
	@Override
	public VideoListDto queryFollowVideo(VideoInfoDto videoInfoDto) throws ParamInvalidException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(videoInfoDto);

		SellerFocus sellerFocus = new SellerFocus(userInfo.getId(), SellerFocusStatusEnum.FOCUS, null);
		List<SellerFocus> focusList = sellerFocusManager.querySellerFocusList(sellerFocus);

		List<VideoInfoDto> respVideoList = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(focusList)) {
			List<Long> sellerIdlist = new ArrayList<Long>(); // 播主Id集合
			for (int i = 0; i < focusList.size(); i++) {
				sellerIdlist.add(focusList.get(i).getSellerId());
			}

			// 视频及相关信息，并封装数据
			List<Integer> videoStatusList = new ArrayList<>();
			videoStatusList.add(VideoStatusEnum.ONLINE.getCode());
			List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListBySellerIdsAndVideoIds(sellerIdlist,
					null, videoInfoDto.getVideoId(), videoStatusList, videoInfoDto.getPageInfo());
			respVideoList = queryDetailOther(videoInfoList, videoInfoDto.getUserId());
		}

		return new VideoListDto(true, "", new VideoInfoDto(), respVideoList, videoInfoDto.getPageInfo());
	}

	@MethodDefinition(value = "queryVideoDetail")
	@Override
	public VideoListDto queryVideoDetail(VideoDetailDto videoDetailDto) throws MainException {
		if (null == videoDetailDto || null == videoDetailDto.getVideoId() || null == videoDetailDto.getQueryScen()) {
			throw new ParamInvalidException("videoId不能为空");
		}

		if (null == videoDetailDto.getQueryScen()) {
			throw new ParamInvalidException("参数错误！");
		}

		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoDetailDto.getVideoId());
		if (videoInfo == null) {
			return new VideoListDto(true, "", new ArrayList<VideoInfoDto>());
		}
		VideoInfoDto videoInfoDto = this.queryVideoInfoDto(videoInfo, videoDetailDto.getUserId());

		if (videoDetailDto.getPageInfo() == null) {
			videoDetailDto.setPageInfo(new PageInfo());
		}

		VideoListDto videoList = new VideoListDto(true, "", new ArrayList<VideoInfoDto>());
		List<VideoInfoDto> videoInfoDtoList = new ArrayList<>();
		if (VideoDetailQueryScenEnum.INDEX_RECOMMEND.getCode() == videoDetailDto.getQueryScen()) {
			videoInfoDtoList = queryRecommendHomeDetail(videoDetailDto);
			videoList.setVideoList(videoInfoDtoList);
		} else if (VideoDetailQueryScenEnum.INDEX_FOLLOW.getCode() == videoDetailDto.getQueryScen()) {
			VideoInfoDto videoInfoDtoParam = new VideoInfoDto(videoDetailDto.getVideoId(), videoDetailDto.getUserId());
			videoDetailDto.getPageInfo().setCurrent(1);
			videoList = queryFollowVideo(videoInfoDtoParam);
		} else if (VideoDetailQueryScenEnum.MY_LIKE.getCode() == videoDetailDto.getQueryScen()) {
			VideoInfoDto videoInfoDtoParam = new VideoInfoDto(videoDetailDto.getVideoId(), videoDetailDto.getUserId());
			videoDetailDto.getPageInfo().setCurrent(1);
			videoList = queryMyGreatVideo(videoInfoDtoParam);
		} else if (VideoDetailQueryScenEnum.SELLER_HOME.getCode() == videoDetailDto.getQueryScen()) {
			videoInfoDtoList = this.querySellerVideoDetailPage(videoDetailDto);
			videoList.setVideoList(videoInfoDtoList);
		}

		videoList.setVideoInfo(videoInfoDto);
		videoDetailDto.getPageInfo().setCurrent(videoDetailDto.getPageInfo().getCurrent());
		videoList.setPageInfo(videoDetailDto.getPageInfo());

		return videoList;
	}

	// 查询首页推荐视频详情
	private List<VideoInfoDto> queryRecommendHomeDetail(VideoDetailDto videoDetailDto) {
		VideoInfo videoInfo = new VideoInfo(videoDetailDto.getVideoId());
		videoInfo.setStatus(VideoStatusEnum.ONLINE.getCode());
		List<VideoInfo> videoInfoList = videoInfoManager.queryVideoDetailList(videoInfo, videoDetailDto.getPageInfo());

		if (CollectionUtils.isEmpty(videoInfoList)) {
			return new ArrayList<>();
		}
		// 查询视频详情中除视频信息外的其它信息
		List<VideoInfoDto> videoInfoDtoList = queryDetailOther(videoInfoList, videoDetailDto.getUserId());
		return videoInfoDtoList;
	}

	// 查询播主首页视频
	private List<VideoInfoDto> querySellerVideoDetailPage(VideoDetailDto videoDetailDto) throws MainException {
		if (videoDetailDto == null || videoDetailDto.getUserId() == null) {
			throw new ParamInvalidException("请先登陆！");
		}
		// 查询该主播下所有视频列表
		VideoInfo videoInfo = new VideoInfo(videoDetailDto.getSellerId(), VideoStatusEnum.ONLINE);
		videoInfo.setId(videoDetailDto.getVideoId());
		PageInfo pageInfo = new PageInfo(1, videoDetailDto.getPageInfo().getPageSize());
		List<VideoInfo> videoInfoList = videoInfoManager.querySellerVideoDetailListByPage(videoInfo, pageInfo);

		if (CollectionUtils.isEmpty(videoInfoList)) {
			return new ArrayList<>();
		}

		// 循环查询视频对应的播主信息和该视频的点赞数，并封装数据
		List<VideoInfoDto> respVideoList = this.queryDetailOther(videoInfoList, videoDetailDto.getUserId());
		return respVideoList;
	}

	// 查询视频中除视频信息外的其它信息
	private List<VideoInfoDto> queryDetailOther(List<VideoInfo> videoInfoList, Long userId) {
		List<VideoInfoDto> videoInfoDtoList = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(videoInfoList)) {
			for (VideoInfo videoInfo : videoInfoList) {
				videoInfoDtoList.add(queryVideoInfoDto(videoInfo, userId));
			}
		}
		return videoInfoDtoList;
	}

	@Override
	public VideoInfoDto queryVideoInfoDto(VideoInfo videoInfo, Long userId) {
		VideoGreat videoGreatParam = new VideoGreat(GreatStatusEnum.GREAT, videoInfo.getSellerId(), videoInfo.getId());
		int greatNum = videoGreatManager.countVideoGreatNum(videoGreatParam);

		// 统计该视频的评论数
		VideoComment videoComment = new VideoComment(videoInfo.getId(), videoInfo.getSellerId(), 0L,
				VideoCommentStatusEnum.NORMAL);
		long commentNum = videoCommentManager.countVideoComment(videoComment);

		// 当前用户是否对该播主已关注seller_focus
		int status = 0;
		if (userId != null && userId != 0L) {
			status = sellerFocusManager.querySellerFocusStatus(userId, videoInfo.getSellerId());
		}

		// 登陆后验证是否对该视频以点赞
		int isGreat = GreatStatusEnum.UNGREAT.getCode();
		Date greatDate = null;
		if (userId != null && userId > 0) {
			VideoGreat videoGreat = new VideoGreat(videoInfo.getSellerId(), videoInfo.getId(), userId,
					GreatStatusEnum.GREAT, null);
			List<VideoGreat> videoGreatList = videoGreatManager.queryVideoGreatList(videoGreat);
			if (videoGreatList != null && videoGreatList.size() > 0) {
				isGreat = GreatStatusEnum.GREAT.getCode();
				greatDate = videoGreatList.get(0).getGreatTime();
			}
		}

		// 该视频下的商品列表
		VideoItemRelation videoItemRelation = new VideoItemRelation(videoInfo.getId(), videoInfo.getSellerId(), null);
		List<VideoItemRelation> relationList = videoItemRelationManager.queryVideoItemRelationList(videoItemRelation);

		List<ItemInfoDto> itemList = new ArrayList<ItemInfoDto>();
		if (relationList != null) {
			for (VideoItemRelation relationItem : relationList) {
				ItemInfo itemInfo = itemInfoManager.queryItemInfoById(relationItem.getItemId());
				if (itemInfo != null) {
					List<ItemDetailInfo> itemDetailInfoList = itemDetailInfoManager
							.queryItemDetailInfoByItemId(itemInfo.getId());
					itemList.add(itemInfo.convert(itemDetailInfoList));
				}
			}
		}

		UserInfo userInfo = userInfoManager.queryUserInfoById(videoInfo.getSellerId());

		return convertVideoItemToDto(videoInfo, userInfo, greatNum, status, commentNum, isGreat, greatDate, itemList);
	}

	private VideoInfoDto convertVideoItemToDto(VideoInfo videoInfo, UserInfo userInfo, int greatNum, int focusStatus,
			Long commentNum, int isGreat, Date greatDate, List<ItemInfoDto> itemList) {
		VideoInfoDto videoInfoDto = videoInfo.convert();
		videoInfoDto.setGreatNum(greatNum);
		videoInfoDto.setCommentNum(commentNum);
		videoInfoDto.setIsGreat(isGreat);
		if (greatDate != null) {
			videoInfoDto.setGreatTime(greatDate.getTime());
		}
		if (userInfo != null) {
			SellerInfoDto sellerInfoDto = userInfo.convertSellerInfoDto();
			sellerInfoDto.setIsFocus(focusStatus);
			videoInfoDto.setSellerInfo(sellerInfoDto);
		}
		videoInfoDto.setItemList(itemList);
		return videoInfoDto;
	}

	// List<BannerConfig>转List<BannerConfigResponseParam>输出
	private List<BannerConfigDto> convertBannerResp(List<BannerConfig> bannerInfoList) {
		List<BannerConfigDto> bannerList = new ArrayList<BannerConfigDto>();
		for (BannerConfig bannerConfig : bannerInfoList) {
			bannerList.add(bannerConfig.convert());
		}
		return bannerList;
	}

	@MethodDefinition(value = "querySellerVideo")
	@Override
	public SellerVideoListDto querySellerVideo(SellerInfoDto sellerInfoDto) throws ParamInvalidException {
		if (null == sellerInfoDto || null == sellerInfoDto.getSellerId() || sellerInfoDto.getSellerId() == 0) {
			throw new ParamInvalidException("sellerId不可为空!");
		}

		// 查询该主播下所有视频列表
		VideoInfo videoInfo = new VideoInfo(sellerInfoDto.getSellerId(), VideoStatusEnum.ONLINE);
		List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByPage(videoInfo,
				sellerInfoDto.getPageInfo());

		// 循环查询视频对应的播主信息和该视频的点赞数，并封装数据
		List<VideoInfoDto> respVideoList = this.queryDetailOther(videoInfoList, sellerInfoDto.getUserId());

		return new SellerVideoListDto(true, "", respVideoList, sellerInfoDto.getPageInfo());
	}

	@MethodDefinition(value = "queryFindVideo")
	@Override
	public VideoListDto queryFindVideo(VideoInfoDto videoInfoDto) {
		VideoInfo videoInfo = new VideoInfo(null, VideoStatusEnum.ONLINE);
		List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoList(videoInfo);

		// 查询视频中除视频信息外的其它信息，并封装数据
		List<VideoInfoDto> respVideoList = queryDetailOther(videoInfoList, videoInfoDto.getUserId());

		return new VideoListDto(true, "", new VideoInfoDto(), respVideoList, videoInfoDto.getPageInfo());
	}

	@MethodDefinition(value = "queryMyGreatVideo", needLogin = true)
	@Override
	public VideoListDto queryMyGreatVideo(VideoInfoDto videoInfoDto) throws ParamInvalidException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(videoInfoDto);

		VideoGreat videoGreat = new VideoGreat(userInfo.getId(), GreatStatusEnum.GREAT, null);
		List<VideoGreat> videoGreatList = videoGreatManager.queryVideoGreatList(videoGreat);

		List<VideoInfoDto> respVideoList = new ArrayList<>();
		if (videoGreatList != null && videoGreatList.size() > 0) {
			// 获取保存视频Id
			List<Long> videoIdlist = new ArrayList<Long>();

			for (int i = 0; i < videoGreatList.size(); i++) {
				videoIdlist.add(videoGreatList.get(i).getVideoId());
			}

			List<Integer> videoStatusList = new ArrayList<>();
			videoStatusList.add(VideoStatusEnum.ONLINE.getCode());
			videoStatusList.add(VideoStatusEnum.DOWNLINE.getCode());
			videoStatusList.add(VideoStatusEnum.FORCE_DOWNLINE.getCode());

			List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListBySellerIdsAndVideoIds(null, videoIdlist,
					videoInfoDto.getVideoId(), videoStatusList, videoInfoDto.getPageInfo());

			// 循环查询视频对应的播主信息和该视频的点赞数，并封装数据
			respVideoList = this.queryVideoList(videoInfoList, videoInfoDto.getUserId());
		}

		return new VideoListDto(true, "", new VideoInfoDto(), respVideoList, videoInfoDto.getPageInfo());
	}

	// 循环查询视频对应的播主信息和该视频的点赞数，并封装数据
	private List<VideoInfoDto> queryVideoList(List<VideoInfo> videoInfoList, Long userId) {
		// 返回视频集合
		List<VideoInfoDto> videoList = new ArrayList<VideoInfoDto>();
		if (videoInfoList != null && videoInfoList.size() > 0) {
			for (VideoInfo videoInfo : videoInfoList) {
				if (videoInfo != null) {
					UserInfo resUserInfo = userInfoManager.queryUserInfoById(videoInfo.getSellerId());

					// 根据视频Id和播主ID查询统计该视频的点赞数
					VideoGreat videoGreatParam = new VideoGreat(GreatStatusEnum.GREAT, videoInfo.getSellerId(),
							videoInfo.getId());
					int greatNum = videoGreatManager.countVideoGreatNum(videoGreatParam);

					// 首页查询登陆后验证是否对该视频以点赞
					int isGreat = GreatStatusEnum.UNGREAT.getCode();
					Date greatDate = null;
					if (userId != null && userId > 0) {
						VideoGreat videoGreat = new VideoGreat(videoInfo.getSellerId(), videoInfo.getId(), userId,
								GreatStatusEnum.GREAT, null);
						List<VideoGreat> videoGreatList = videoGreatManager.queryVideoGreatList(videoGreat);
						if (videoGreatList != null && videoGreatList.size() > 0) {
							isGreat = GreatStatusEnum.GREAT.getCode();
							greatDate = videoGreatList.get(0).getGreatTime();
						}
					}

					// 当前用户是否对该播主已关注seller_focus
					int isFocus = 0;
					if (userId != null && userId != 0L) {
						isFocus = sellerFocusManager.querySellerFocusStatus(userId, videoInfo.getSellerId());
					}

					VideoInfoDto videoInfoRes = convertVideoInfoToDto(videoInfo, resUserInfo, greatNum, isGreat,
							greatDate, isFocus);
					videoList.add(videoInfoRes);
				}
			}
		}
		return videoList;
	}

	// videoInfo转DTO
	public static VideoInfoDto convertVideoInfoToDto(VideoInfo video, UserInfo resUserInfo, int greatNum, int isGreat,
			Date greatDate, Integer isFocus) {
		VideoInfoDto videoInfoDto = video.convert();

		if (resUserInfo != null) {
			SellerInfoDto sellerInfoDto = resUserInfo.convertSellerInfoDto();
			sellerInfoDto.setIsFocus(isFocus);
			videoInfoDto.setSellerInfo(sellerInfoDto);
		}
		videoInfoDto.setGreatNum(greatNum);
		videoInfoDto.setIsGreat(isGreat);
		if (greatDate != null) {
			videoInfoDto.setGreatTime(greatDate.getTime());
		}
		return videoInfoDto;
	}

	@Override
	public SellerVideoListDto querySellerVideoByStatus(CommonQueryDto commonQueryDto) throws ParamInvalidException {
		if (null == commonQueryDto || null == commonQueryDto.getUserId()) {
			throw new ParamInvalidException("sellerId不能为空");
		}

		long sellerId = commonQueryDto.getUserId();

		UserInfo userInfo = userInfoManager.queryUserInfoById(sellerId);

		if (userInfo == null) {
			logger.warn("querySellerVideoByStatus error, sellerInfo is null, sellerId=" + sellerId);
			return new SellerVideoListDto(new PageInfo());
		}

		// 查询该主播下所有视频列表
		VideoInfo videoInfo = new VideoInfo(userInfo.getId(),
				null == commonQueryDto.getStatus() ? null
						: VideoStatusEnum.getVideoStatusEnumByCode(commonQueryDto.getStatus()),
				commonQueryDto.getVideoTitle());
		List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByPage(videoInfo,
				commonQueryDto.getPageInfo());
		int total = videoInfoManager.countVideoInfoByPage(videoInfo, commonQueryDto.getPageInfo());
		commonQueryDto.getPageInfo().setTotal(total);

		// 循环查询视频对应的播主信息和该视频的点赞数，并封装数据
		List<VideoInfoDto> respVideoList = this.queryVideoItemList(videoInfoList);

		return new SellerVideoListDto(commonQueryDto.getPageInfo(), respVideoList);
	}

	// 循环查询视频对应的播主信息和该视频的商品信息，并封装数据
	public List<VideoInfoDto> queryVideoItemList(List<VideoInfo> videoInfoList) {
		// 返回视频集合
		List<VideoInfoDto> videoList = new ArrayList<VideoInfoDto>();
		if (videoInfoList != null && videoInfoList.size() > 0) {
			for (VideoInfo video : videoInfoList) {
				if (video != null) {
					VideoItemRelation videoItemRelation = new VideoItemRelation(video.getId(), video.getSellerId(),
							null);
					List<VideoItemRelation> relationList = videoItemRelationManager
							.queryVideoItemRelationList(videoItemRelation);

					// 统计该视频的评论数
					long commentNum = videoCommentManager.countVideoComment(
							new VideoComment(video.getId(), video.getSellerId(), 0L, VideoCommentStatusEnum.NORMAL));
					// 统计该视频的点赞数
					int greatNum = videoGreatManager.countVideoGreatNum(
							new VideoGreat(GreatStatusEnum.GREAT, video.getSellerId(), video.getId()));

					List<ItemInfoDto> itemList = new ArrayList<ItemInfoDto>();
					if (relationList != null) {
						for (VideoItemRelation relationItem : relationList) {
							ItemInfo itemInfo = itemInfoManager.queryItemInfoById(relationItem.getItemId());
							if (itemInfo != null) {
								List<ItemDetailInfo> itemDetailInfoList = itemDetailInfoManager
										.queryItemDetailInfoByItemId(itemInfo.getId());
								ItemInfoDto itemResponseParam = itemInfo.convert(itemDetailInfoList);
								itemList.add(itemResponseParam);
							}
						}
					}
					VideoInfoDto videoInfoRes = convertVideoInfoToDto(video, null, greatNum, 0, null, null);
					videoInfoRes.setItemList(itemList);
					videoInfoRes.setCommentNum(commentNum);
					videoList.add(videoInfoRes);
				}
			}
		}
		return videoList;
	}

	@Override
	public VideoInfoDto queryVideoInfoDetail(VideoInfoDto videoInfoDto) throws MainException {
		if (null == videoInfoDto || null == videoInfoDto.getVideoId()) {
			throw new ParamInvalidException("videoId不能为空");
		}
		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoInfoDto.getVideoId());
		if (videoInfo == null) {
			return new VideoInfoDto();
		}

		// 查询视频详情中除视频信息外的其它信息
		VideoInfoDto videoInfosDto = queryVideoDetailOthers(videoInfo);

		return videoInfosDto;
	}

	private VideoInfoDto queryVideoDetailOthers(VideoInfo videoInfo) {
		VideoInfoDto videoInfoDtos = null;

		if (videoInfo != null) {
			videoInfoDtos = new VideoInfoDto();
			VideoGreat videoGreat = new VideoGreat(GreatStatusEnum.GREAT, videoInfo.getSellerId(), videoInfo.getId());
			int greatNum = videoGreatManager.countVideoGreatNum(videoGreat);
			UserInfo userInfo = userInfoManager.queryUserInfoById(videoInfo.getSellerId());
			// 统计该视频的评论数
			VideoComment videoComment = new VideoComment(videoInfo.getId(), videoInfo.getSellerId(), 0L,
					VideoCommentStatusEnum.NORMAL);
			long commentNum = videoCommentManager.countVideoComment(videoComment);
			// 视频信息赋值dto
			videoInfoDtos = videoInfo.convert();
			videoInfoDtos.setCommentNum(commentNum);
			videoInfoDtos.setGreatNum(greatNum);
			// 该视频下的商品列表
			VideoItemRelation videoItemRelation = new VideoItemRelation(videoInfo.getId(), videoInfo.getSellerId(),
					null);
			List<VideoItemRelation> relationList = videoItemRelationManager
					.queryVideoItemRelationList(videoItemRelation);

			List<ItemInfoDto> itemList = new ArrayList<ItemInfoDto>();
			List<ItemDetailInfoDto> itemDetailList = new ArrayList<ItemDetailInfoDto>();
			if (relationList != null) {
				for (VideoItemRelation relationItem : relationList) {
					ItemInfo itemInfo = itemInfoManager.queryItemInfoById(relationItem.getItemId());
					if (itemInfo != null) {
						ItemInfoDto itemResponseParam = itemInfo.convert();

						List<ItemDetailInfo> itemDetailInfoList = itemDetailInfoManager
								.queryItemDetailInfoByItemId(itemInfo.getId());
						if (itemDetailInfoList != null) {
							for (ItemDetailInfo itemDetailInfo : itemDetailInfoList) {
								itemDetailList.add(itemDetailInfo.convert());
							}
						}
						itemResponseParam.setItemDetailList(itemDetailList);
						itemList.add(itemResponseParam);
					}
				}
			}
			videoInfoDtos.setItemList(itemList);
			videoInfoDtos.setSellerInfo(null == userInfo ? null : userInfo.convertSellerInfoDto());
		}
		return videoInfoDtos;
	}
}
