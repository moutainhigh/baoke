package com.baoke.item.dao;

import java.util.List;

import com.baoke.item.domain.VideoItemRelation;

public interface VideoItemRelationDao {

	List<VideoItemRelation> queryVideoItemRelationList(VideoItemRelation videoItemRelation);

	VideoItemRelation queryVideoItemRelationByVideoId(long videoId);

	int countVideoItemRelationBySellerId(Long sellerId);

	int countVideoItemRelationByItemId(long itemId);

	int deleteVideoItemRelationByVideoId(long videoId);

	int addVideoItemRelation(VideoItemRelation videoItemRelation);
}
