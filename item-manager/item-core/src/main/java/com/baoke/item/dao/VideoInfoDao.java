package com.baoke.item.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.domain.VideoInfo;

public interface VideoInfoDao {

	VideoInfo queryVideoInfoByIdAndStatus(@Param("videoId") long videoId, @Param("status") int status);

	List<VideoInfo> queryVideoInfoListByPage(@Param("sellerIds") List<Long> sellerIds,
			@Param("videoInfo") VideoInfo videoInfo, @Param("pageInfo") PageInfo pageInfo);

	List<VideoInfo> querySellerVideoDetailListByPage(@Param("videoInfo") VideoInfo videoInfo,
			@Param("pageInfo") PageInfo pageInfo);

	int countVideoInfoByPage(@Param("sellerIds") List<Long> sellerIds, @Param("videoInfo") VideoInfo videoInfo,
			@Param("pageInfo") PageInfo pageInfo);

	int countVideoInfoListByVideoIds(@Param("videoIds") List<Long> videoIds, @Param("status") Integer status);

	int countVideoInfoByStatusesAndIds(@Param("videoIds") List<Long> videoIds,
			@Param("statuses") List<Integer> statuses);

	List<VideoInfo> queryVideoInfoList(VideoInfo videoInfo);

	List<VideoInfo> queryVideoDetailList(@Param("videoInfo") VideoInfo videoInfo, @Param("pageInfo") PageInfo pageInfo);

	VideoInfo queryVideoInfoById(long id);

	List<VideoInfo> queryVideoInfoListBySellerIdsAndVideoIds(Map<String, Object> map);

	List<VideoInfo> queryVideoInfoListByVideoIds(@Param("videoIds") List<Long> videoIds,
			@Param("status") Integer status);

	int countVideoInfo(VideoInfo videoInfo);

	List<VideoInfo> queryVideoInfoListByTitleAndSellerId(@Param("title") String title, @Param("sellerId") Long sellerId,
			@Param("status") Integer status);

	List<VideoInfo> queryVideoInfoListByTitle(@Param("title") String title);

	int modifyVideoInfoStatusByIds(@Param("videoIds") List<Long> videoIds, @Param("videoInfo") VideoInfo videoInfo);

	int modifyVideoInfoStatusById(@Param("id") long id, @Param("auditResult") String auditResult,
			@Param("status") long status);

	int modifyVideoInfoByVideoId(VideoInfo videoInfo);

	int addVideoInfo(VideoInfo videoInfo);

}
