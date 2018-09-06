package com.baoke.interact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.interact.domain.VideoGreat;

/**
 * 视频点赞(喜欢)dao
 * 
 * @author: zdy
 * @date: 2018年6月12日
 */
public interface VideoGreatDao {

	List<VideoGreat> queryVideoGreatList(VideoGreat videoGreat);

	int queryLastdayAddLikeNum(@Param("sellerId") long sellerId, @Param("greatStatus") Integer greatStatus);

	int countVideoGreat(VideoGreat videoGreat);

	int addVideoGreat(VideoGreat videoGreat);

	int modifyVideoGreat(VideoGreat videoGreat);

}