package com.baoke.item.service.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.DateUtil;
import com.baoke.common.util.MoneyUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.interact.domain.VideoComment;
import com.baoke.interact.domain.VideoGreat;
import com.baoke.interact.manager.SellerFocusManager;
import com.baoke.interact.manager.VideoCommentManager;
import com.baoke.interact.manager.VideoGreatManager;
import com.baoke.item.constant.ItemDetailStatusEnum;
import com.baoke.item.constant.ItemStatusEnum;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.domain.VideoItemRelation;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.item.manager.ItemInfoManager;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.item.manager.VideoItemRelationManager;
import com.baoke.item.service.VideoInfoManagerService;
import com.baoke.item.service.VideoInfoService;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SellerInfoManager;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserInfoManager;

@Service("VideoInfoManagerService")
public class VideoInfoManagerServiceImpl implements VideoInfoManagerService {

	private static final Logger logger = Logger.getLogger(VideoInfoManagerServiceImpl.class);

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private ItemInfoManager itemInfoManager;

	@Resource
	private ItemDetailInfoManager itemDetailInfoManager;

	@Resource
	private VideoItemRelationManager videoItemRelationManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private VideoInfoService videoInfoService;

	@Resource
	private SendSiteManager sendSiteManager;

	@Resource
	SellerFocusManager sellerFocusManager;

	@Resource
	private VideoCommentManager videoCommentManager;
	@Resource
	private VideoGreatManager videoGreatManager;

	@Resource
	private SellerInfoManager sellerInfoManager;

	@Override
	public BaseResultDto saveVideoStatus(CommonQueryDto commonQueryDto) throws ParamInvalidException {

		if (null == commonQueryDto) {
			throw new ParamInvalidException("参数不能为空");
		}

		if (null == commonQueryDto.getIds() || commonQueryDto.getIds().size() <= 0) {
			throw new ParamInvalidException("视频id列表不能为空");
		}

		if (null == commonQueryDto.getStatus()) {
			throw new ParamInvalidException("视频状态不能为空");
		}

		List<VideoInfo> videoInfolist = new ArrayList<>();
		if ((commonQueryDto.getStatus() == VideoStatusEnum.DOWNLINE.getCode())
				|| commonQueryDto.getStatus() == VideoStatusEnum.ONLINE.getCode()
				|| commonQueryDto.getStatus() == VideoStatusEnum.FORCE_DOWNLINE.getCode()) {
			for (Long videoId : commonQueryDto.getIds()) {

				VideoItemRelation videoItemRelation = new VideoItemRelation(videoId, null, null);
				List<VideoItemRelation> relationList = videoItemRelationManager
						.queryVideoItemRelationList(videoItemRelation);
				if (relationList == null) {
					continue;
				}

				for (VideoItemRelation relationItem : relationList) {
					ItemInfo itemInfo = itemInfoManager.queryItemInfoById(relationItem.getItemId());

					if (itemInfo == null) {
						continue;
					}

					if (commonQueryDto.getStatus() == VideoStatusEnum.DOWNLINE.getCode()
							|| commonQueryDto.getStatus() == VideoStatusEnum.FORCE_DOWNLINE.getCode()) {
						// 查询该视频是否是该商品最后的一个关联
						videoItemRelation = new VideoItemRelation(null, null, itemInfo.getId());
						List<VideoItemRelation> ItemRelationList = videoItemRelationManager
								.queryVideoItemRelationList(videoItemRelation);

						List<Long> videoIds = new ArrayList<>();

						for (VideoItemRelation relation : ItemRelationList) {
							videoIds.add(relation.getVideoId());
						}
						videoInfolist = videoInfoManager.queryVideoInfoListByVideoIds(videoIds, VideoStatusEnum.ONLINE);
						// 下架商品对应的最后一个视频时，商品对应下架
						if (videoInfolist.size() >= 2) {
							continue;
						}

						if (!commonQueryDto.isDownLineVideoFlag()
								&& commonQueryDto.getStatus() == VideoStatusEnum.DOWNLINE.getCode()) {
							return new BaseResultDto(false, "该视频为该商品最后一个视频");
						}
						itemInfoManager.modifyItemStatusById(itemInfo.getId(),
								ItemStatusEnum.getByCode(commonQueryDto.getStatus()));

					} else if (commonQueryDto.getStatus() == VideoStatusEnum.ONLINE.getCode()) {
						// 查询商品关联上线视频数量
						List<VideoItemRelation> videoItemRelationList = videoItemRelationManager
								.queryVideoItemRelationList(new VideoItemRelation(null, null, itemInfo.getId()));
						List<Long> videoIds = new ArrayList<>();
						for (VideoItemRelation relation : videoItemRelationList) {
							videoIds.add(relation.getVideoId());
						}
						int videoCount = videoInfoManager.countVideoInfoListByVideoIds(videoIds,
								VideoStatusEnum.ONLINE);
						if (videoCount >= CommonConfig.ITEM_VIDEO_RELATION_COUNT) {
							return new BaseResultDto(false,
									"每个商品最多关联" + CommonConfig.ITEM_VIDEO_RELATION_COUNT + "个视频");
						} else {
							itemInfoManager.modifyItemStatusById(itemInfo.getId(), ItemStatusEnum.ONLINE);
						}
					}
				}

			}
		}
		videoInfoManager.modifyVideoInfoStatusByIds(commonQueryDto.getIds(), commonQueryDto.getStatus(),
				commonQueryDto.getAuditResult());

		// 站内信
		for (VideoInfo videoInfoTemp : videoInfolist) {
			SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID,
					videoInfoTemp.getSellerId(), videoInfoTemp.getId(), "视频上线/封禁",
					videoInfoTemp.getTitle() + commonQueryDto.getAuditResult()); // 给主播
			sendSiteManager.sendSite(siteMessage);
		}

		return new BaseResultDto(true, "");
	}

	@Override
	public void saveVideoInfo(VideoInfoDto videoInfoDto) throws ParamInvalidException, ParseException {

		if (null == videoInfoDto) {
			throw new ParamInvalidException("参数不能为空");
		}
		if (StringUtil.isBlank(videoInfoDto.getTitle())) {
			throw new ParamInvalidException("视频标题不能为空");
		}
		if (StringUtil.isBlank(videoInfoDto.getUrl())) {
			throw new ParamInvalidException("视频地址不能为空");
		}
		if (StringUtil.isBlank(videoInfoDto.getIconImgUrl())) {
			throw new ParamInvalidException("视频封面不能为空");
		}

		// 每个商品只能关联五个视频
		if (null == videoInfoDto.getVideoId() && CollectionUtil.isNotEmpty(videoInfoDto.getItemList())
				&& null != videoInfoDto.getItemList().get(0).getItemId()) {
			Long itemId = videoInfoDto.getItemList().get(0).getItemId();
			// 查询商品关联上线视频数量
			List<VideoItemRelation> videoItemRelationList = videoItemRelationManager
					.queryVideoItemRelationList(new VideoItemRelation(null, null, itemId));
			List<Long> videoIds = new ArrayList<>();
			for (VideoItemRelation videoItemRelation : videoItemRelationList) {
				videoIds.add(videoItemRelation.getVideoId());
			}
			int videoCount = videoInfoManager.countVideoInfoListByVideoIds(videoIds, VideoStatusEnum.ONLINE);
			if (videoCount >= CommonConfig.ITEM_VIDEO_RELATION_COUNT) {
				String errorMessage = String.format("每个商品最多关联" + CommonConfig.ITEM_VIDEO_RELATION_COUNT + "个视频,商品id=%s",
						new Object[] { itemId });
				logger.error("保存视频异常： " + errorMessage);
				throw new ParamInvalidException(errorMessage);
			}
		}

		if (null == videoInfoDto.getVideoId()) {
			videoInfoDto.setVideoId(addVideoInfoForVideoId(videoInfoDto));
		} else {
			modifyVideoInfoByVideoId(videoInfoDto);
			videoItemRelationManager.deleteVideoItemRelationByVideoId(videoInfoDto.getVideoId());
		}

		if (videoInfoDto.getItemList() == null) {
			// 类似宣传视频,仅添加视频,无关联商品
			return;
		}

		for (ItemInfoDto itemInfoDto : videoInfoDto.getItemList()) {
			itemInfoDto.setUserId(videoInfoDto.getUserId());
			Long itemId = itemInfoDto.getItemId();
			if (itemId == null) {
				itemId = addItemInfoForItemId(itemInfoDto);
				if (itemInfoDto.getItemDetailList() == null) {
					throw new ParamInvalidException("缺少商品价格或库存信息!");
				}
				for (ItemDetailInfoDto itemDetailInfo : itemInfoDto.getItemDetailList()) {
					itemDetailInfo.setItemId(itemId);
					itemDetailInfo.setUserId(videoInfoDto.getUserId());
					addItemDetailInfoForItemDetailInfoId(itemDetailInfo);
				}
			}

			addVideoItemRelationForId(itemId, videoInfoDto.getVideoId(), videoInfoDto.getUserId());
		}

	}

	// 新增视频商品关联
	private Long addVideoItemRelationForId(Long itemId, Long videoId, Long userId) {
		return videoItemRelationManager.addVideoItemRelation(new VideoItemRelation(videoId, userId, itemId));
	}

	// 新增商品详情
	private Long addItemDetailInfoForItemDetailInfoId(ItemDetailInfoDto itemDetailInfoDto) {
		ItemDetailInfo itemDetailInfo = new ItemDetailInfo();
		itemDetailInfo.setPrice(MoneyUtil.changeY2F(itemDetailInfoDto.getPrice()));
		itemDetailInfo.setMarketPrice(MoneyUtil.changeY2F(itemDetailInfoDto.getMarketPrice()));
		itemDetailInfo.setSaleNum(0);
		itemDetailInfo.setStartTime(new Date());
		try {
			itemDetailInfo.setEndTime(DateUtil.parse(CommonConfig.DEFAULT_END_DATE, DateUtil.DATE_TIME_FORMAT_STR));
		} catch (ParseException e) {
		}
		itemDetailInfo.setTotalNum(itemDetailInfoDto.getTotalNum());
		itemDetailInfo.setItemId(itemDetailInfoDto.getItemId());
		itemDetailInfo.setStatus(ItemDetailStatusEnum.ENABLE.getCode());
		itemDetailInfo.setSellerId(itemDetailInfoDto.getUserId());
		return itemDetailInfoManager.addItemDetailInfo(itemDetailInfo);
	}

	// 新增商品
	private Long addItemInfoForItemId(ItemInfoDto itemInfoDto) {
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setPrice(MoneyUtil.changeY2F(itemInfoDto.getItemDetailList().get(0).getPrice()));
		itemInfo.setTitle(itemInfoDto.getTitle());
		itemInfo.setSellerId(itemInfoDto.getUserId());
		itemInfo.setMainImgUrl(itemInfoDto.getMainImgUrl());
		itemInfo.setStatus(ItemStatusEnum.WAIT_AUDIT.getCode());
		itemInfo.setStartTime(new Date());
		try {
			itemInfo.setEndTime(DateUtil.parse(CommonConfig.DEFAULT_END_DATE, DateUtil.DATE_TIME_FORMAT_STR));
		} catch (ParseException e) {
		}
		itemInfo.setPostage(0);
		itemInfo.setPostage(0);
		return itemInfoManager.addItemInfo(itemInfo);
	}

	// 新增视频,获取主键id
	private Long addVideoInfoForVideoId(VideoInfoDto videoInfoDto) throws ParseException {
		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setSellerId(videoInfoDto.getUserId());
		videoInfo.setTitle(videoInfoDto.getTitle());
		videoInfo.setUrl(videoInfoDto.getUrl());
		videoInfo.setIconImgUrl(videoInfoDto.getIconImgUrl());
		videoInfo.setStatus(VideoStatusEnum.WAIT_AUDIT.getCode());
		videoInfo.setTags(videoInfoDto.getTags());
		videoInfo.setEndTime(DateUtil.parse(CommonConfig.DEFAULT_END_DATE, DateUtil.DATE_TIME_FORMAT_STR));
		return videoInfoManager.addVideoInfo(videoInfo);
	}

	private Integer modifyVideoInfoByVideoId(VideoInfoDto videoInfoDto) throws ParseException {
		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setId(videoInfoDto.getVideoId());
		videoInfo.setTitle(videoInfoDto.getTitle());
		videoInfo.setUrl(videoInfoDto.getUrl());
		videoInfo.setIconImgUrl(videoInfoDto.getIconImgUrl());
		videoInfo.setStatus(VideoStatusEnum.WAIT_AUDIT.getCode());
		videoInfo.setTags(videoInfoDto.getTags());
		videoInfo
				.setStartTime(null == videoInfoDto.getStartTime() ? new Date() : new Date(videoInfoDto.getStartTime()));
		videoInfo.setEndTime(null == videoInfoDto.getEndTime()
				? DateUtil.parse(CommonConfig.DEFAULT_END_DATE, DateUtil.DATE_TIME_FORMAT_STR)
				: new Date(videoInfoDto.getEndTime()));
		return videoInfoManager.modifyVideoInfoByVideoId(videoInfo);
	}

	@Override
	public SellerVideoListDto querySellerVideoList(CommonQueryDto commonQueryDto) throws ParamInvalidException {

		if (null == commonQueryDto) {
			logger.error("videoInfoMngService.querySellerVideoList error , CommonQueryDto ParamInvalidException");
			throw new ParamInvalidException("参数不能为空");
		}

		List<Long> userIds = new ArrayList<>();

		if (StringUtil.hasLength(commonQueryDto.getNickName())) {
			List<UserInfo> userInfoList = userInfoManager.queryUserInfoByNickName(commonQueryDto.getNickName());
			if (CollectionUtil.isEmpty(userInfoList)) {
				return new SellerVideoListDto(new PageInfo());
			}
			for (UserInfo userInfo : userInfoList) {
				userIds.add(userInfo.getId());
			}
		}

		// 查询该主播下所有视频列表

		VideoInfo videoInfo = new VideoInfo(null,
				null == commonQueryDto.getStatus() ? null
						: VideoStatusEnum.getVideoStatusEnumByCode(commonQueryDto.getStatus()),
				commonQueryDto.getVideoTitle());
		List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByPageAndSellerIds(userIds, videoInfo,
				commonQueryDto.getPageInfo());
		int total = videoInfoManager.countVideoInfoByPageAndSellerIds(userIds, videoInfo, commonQueryDto.getPageInfo());
		commonQueryDto.getPageInfo().setTotal(total);

		// 循环查询视频对应的播主信息和该视频的点赞数，并封装数据
		List<VideoInfoDto> respVideoList = this.queryVideoItemList(videoInfoList);

		return new SellerVideoListDto(commonQueryDto.getPageInfo(), respVideoList);

	}

	// 循环查询视频对应的播主信息和该视频的商品信息，并封装数据
	public List<VideoInfoDto> queryVideoItemList(List<VideoInfo> videoInfoList) {
		// 返回视频集合
		List<VideoInfoDto> videoList = new ArrayList<VideoInfoDto>();
		if (CollectionUtil.isNotEmpty(videoInfoList)) {
			for (VideoInfo video : videoInfoList) {
				if (video == null) {
					continue;
				}
				VideoItemRelation videoItemRelation = new VideoItemRelation(video.getId(), video.getSellerId(), null);
				List<VideoItemRelation> relationList = videoItemRelationManager
						.queryVideoItemRelationList(videoItemRelation);

				UserInfo userInfo = userInfoManager.queryUserInfoById(video.getSellerId());
				// 统计该视频的评论数
				long commentNum = videoCommentManager.countVideoComment(
						new VideoComment(video.getId(), video.getSellerId(), null, VideoCommentStatusEnum.NORMAL));
				// 统计该视频的点赞数
				int greatNum = videoGreatManager
						.countVideoGreatNum(new VideoGreat(GreatStatusEnum.GREAT, video.getSellerId(), video.getId()));

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
				VideoInfoDto videoInfoDto = video.convert();
				SellerInfoDto sellerInfoDto = userInfo.convertSellerInfoDto();
				videoInfoDto.setSellerInfo(sellerInfoDto);
				videoInfoDto.setGreatNum(greatNum);
				videoInfoDto.setItemList(itemList);
				videoInfoDto.setCommentNum(commentNum);
				videoList.add(videoInfoDto);

			}
		}
		return videoList;
	}

	@Override
	public BaseDto auditVideoInfo(CommonQueryDto commonQueryDto) throws ParamInvalidException {

		if (null == commonQueryDto || null == commonQueryDto.getVideoId()) {
			logger.error("videoInfoMngService.auditVideoInfo error , CommonQueryDto ParamInvalidException");
			throw new ParamInvalidException("视频id不能为空");
		}

		if (null == commonQueryDto.getStatus() || !(commonQueryDto.getStatus() == VideoStatusEnum.AUDIT_FAIL.getCode()
				|| commonQueryDto.getStatus() == VideoStatusEnum.ONLINE.getCode())) {
			logger.error(MessageFormat.format("videoInfoMngService.auditVideoInfo error , status invalid , status={0}",
					commonQueryDto.getStatus()));
			throw new ParamInvalidException("视频审核状态值非法");
		}

		// 查询视频对应的商品
		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(commonQueryDto.getVideoId());

		if (null == videoInfo) {
			logger.error(
					MessageFormat.format("videoInfoMngService.auditVideoInfo error , video is not exist  , VideoId={0}",
							commonQueryDto.getVideoId()));
			throw new ParamInvalidException("视频不存在");
		}

		List<VideoItemRelation> videoItemRelationList = videoItemRelationManager
				.queryVideoItemRelationList(new VideoItemRelation(commonQueryDto.getVideoId(), null, null));

		ItemStatusEnum itemStatusEnum = ItemStatusEnum.AUDIT_FAIL;

		if (commonQueryDto.getStatus() == VideoStatusEnum.ONLINE.getCode()) {
			itemStatusEnum = ItemStatusEnum.ONLINE;
		}

		List<Long> items = new ArrayList<>();
		for (VideoItemRelation videoItemRelation : videoItemRelationList) {
			items.add(videoItemRelation.getItemId());
		}

		if (items.size() > 0) {
			for (Long itemId : items) {
				int count = 0;
				List<VideoItemRelation> relationList = videoItemRelationManager
						.queryVideoItemRelationList(new VideoItemRelation(null, null, itemId));
				List<Long> videoIds = new ArrayList<>();
				for (VideoItemRelation relation : relationList) {
					videoIds.add(relation.getVideoId());
				}
				if (commonQueryDto.getStatus() == VideoStatusEnum.AUDIT_FAIL.getCode()) {
					// 如果商品存在已上线以及已下线的视频则为上线商品
					List<Integer> statuses = new ArrayList<>();
					statuses.add(VideoStatusEnum.ONLINE.getCode());
					statuses.add(VideoStatusEnum.DOWNLINE.getCode());
					count = videoInfoManager.countVideoInfoByStatusesAndIds(videoIds, statuses);

				} else if (commonQueryDto.getStatus() == VideoStatusEnum.ONLINE.getCode()) {
					// 查询商品关联上线视频数量
					int videoCount = videoInfoManager.countVideoInfoListByVideoIds(videoIds, VideoStatusEnum.ONLINE);
					if (videoCount >= CommonConfig.ITEM_VIDEO_RELATION_COUNT) {
						String errorMessage = String.format(
								"每个商品最多关联" + CommonConfig.ITEM_VIDEO_RELATION_COUNT + "个视频,商品id=%s",
								new Object[] { itemId });
						logger.error("审核视频异常： " + errorMessage);
						throw new ParamInvalidException(errorMessage);
					}
				}
				itemInfoManager.modifyItemByitemId(itemId, count > 0 ? ItemStatusEnum.ONLINE : itemStatusEnum,
						commonQueryDto.getAuditResult());
			}
		}
		// 视频审核
		videoInfoManager.modifyVideoInfoStatusById(commonQueryDto.getVideoId(), commonQueryDto.getAuditResult(),
				commonQueryDto.getStatus());

		/** 站内信 */
		SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID,
				videoInfo.getSellerId(), videoInfo.getId(), "视频审核",
				videoInfo.getTitle() + commonQueryDto.getAuditResult()); // 给主播
		sendSiteManager.sendSite(siteMessage);
		if (commonQueryDto.getStatus() == VideoStatusEnum.ONLINE.getCode()) { // 审核成功，给所有关注了该主播的用户
			List<Long> userIdList = sellerFocusManager.queryAllFocusID(videoInfo.getSellerId(),
					SellerFocusStatusEnum.FOCUS);
			for (Long userId : userIdList) {
				SiteMessage message = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID, userId,
						videoInfo.getId(), "视频上线", "您关注的主播有新视频上线了： " + videoInfo.getTitle());
				sendSiteManager.sendSite(message);
			}
		}

		return new BaseDto();
	}

}
