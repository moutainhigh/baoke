package com.baoke.item.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.dao.VideoInfoDao;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.VideoInfoManager;

/**
 * 视频ManagerImpl
 * 
 * @author zdy
 * @date: 2018年6月13日 下午1:57:12
 */
@Component
@DataSource("coreDataSource")
public class VideoInfoManagerImpl implements VideoInfoManager {
	@Resource
	private VideoInfoDao videoInfoDao;

	@Override
	public VideoInfo queryVideoInfoByIdAndStatus(long videoId, VideoStatusEnum videoStatusEnum) {
		return videoInfoDao.queryVideoInfoByIdAndStatus(videoId, videoStatusEnum.getCode());
	}

	@Override
	public List<VideoInfo> queryVideoInfoList(VideoInfo videoInfo) {
		return videoInfoDao.queryVideoInfoList(videoInfo);
	}

	@Override
	public List<VideoInfo> querySellerVideoDetailListByPage(VideoInfo videoInfo, PageInfo pageInfo) {
		return videoInfoDao.querySellerVideoDetailListByPage(videoInfo, pageInfo);
	}

	@Override
	public VideoInfo queryVideoInfoById(long id) {
		return videoInfoDao.queryVideoInfoById(id);
	}

	@Override
	public int countVideoInfo(VideoInfo videoInfo) {
		return videoInfoDao.countVideoInfo(videoInfo);
	}

	@Override
	public int countVideoInfoByPage(VideoInfo videoInfo, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return countVideoInfoByPageAndSellerIds(null, videoInfo, pageInfo);
	}

	@Override
	public int countVideoInfoByPageAndSellerIds(List<Long> sellerIds, VideoInfo videoInfo, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return videoInfoDao.countVideoInfoByPage(sellerIds, videoInfo, pageInfo);
	}

	@Override
	public int countVideoInfoListByVideoIds(List<Long> videoIds, VideoStatusEnum videoStatusEnum) {
		return videoInfoDao.countVideoInfoListByVideoIds(videoIds,
				null == videoStatusEnum ? null : videoStatusEnum.getCode());
	}

	@Override
	public int countVideoInfoByStatusesAndIds(List<Long> videoIds, List<Integer> statuses) {
		return videoInfoDao.countVideoInfoByStatusesAndIds(videoIds, statuses);
	}

	@Override
	public List<VideoInfo> queryVideoDetailList(VideoInfo videoInfo, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return videoInfoDao.queryVideoDetailList(videoInfo, pageInfo);
	}

	@Override
	public List<VideoInfo> queryVideoInfoListByPage(VideoInfo videoInfo, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return queryVideoInfoListByPageAndSellerIds(null, videoInfo, pageInfo);
	}

	@Override
	public List<VideoInfo> queryVideoInfoListByPageAndSellerIds(List<Long> sellerIds, VideoInfo videoInfo,
			PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return videoInfoDao.queryVideoInfoListByPage(sellerIds, videoInfo, pageInfo);
	}

	@Override
	public List<VideoInfo> queryVideoInfoListBySellerIdsAndVideoIds(List<Long> sellerIdlist, List<Long> videoIdlist,
			Long videoId, List<Integer> videoStatusList, PageInfo pageInfo) {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		// 查询关注的视频集合
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerIdList", sellerIdlist);
		map.put("videoIdList", videoIdlist);
		map.put("videoId", videoId);
		map.put("statusList", videoStatusList);
		map.put("pageInfo", pageInfo);
		return videoInfoDao.queryVideoInfoListBySellerIdsAndVideoIds(map);
	}

	@Override
	public List<VideoInfo> queryVideoInfoListByVideoIds(List<Long> videoIds, VideoStatusEnum videoStatusEnum) {

		return videoInfoDao.queryVideoInfoListByVideoIds(videoIds,
				null == videoStatusEnum ? null : videoStatusEnum.getCode());
	}

	@Override
	public List<VideoInfo> queryVideoInfoListByTitleAndSellerId(String title, Long sellerId,
			VideoStatusEnum videoStatusEnum) {
		return videoInfoDao.queryVideoInfoListByTitleAndSellerId(title, sellerId, videoStatusEnum.getCode());

	}

	@Override
	public int modifyVideoInfoStatusById(Long id, String auditResult, Integer status) {
		return videoInfoDao.modifyVideoInfoStatusById(id, auditResult, status);
	}

	@Override
	public int modifyVideoInfoStatusByIds(List<Long> videoIds, Integer status, String auditResult) {

		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setStatus(status);
		videoInfo.setAuditResult(auditResult);

		return videoInfoDao.modifyVideoInfoStatusByIds(videoIds, videoInfo);
	}

	@Override
	public int modifyVideoInfoByVideoId(VideoInfo videoInfo) {
		return videoInfoDao.modifyVideoInfoByVideoId(videoInfo);
	}

	@Override
	public long addVideoInfo(VideoInfo videoInfo) {
		videoInfoDao.addVideoInfo(videoInfo);
		return videoInfo.getId();
	}

	@Override
	public List<VideoInfo> queryVideoInfoListByTitle(String title) {
		return videoInfoDao.queryVideoInfoListByTitle(title);
	}

}
