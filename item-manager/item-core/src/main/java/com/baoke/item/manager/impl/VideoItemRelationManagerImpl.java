package com.baoke.item.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.item.dao.VideoItemRelationDao;
import com.baoke.item.domain.VideoItemRelation;
import com.baoke.item.manager.VideoItemRelationManager;

/**
 * 视频商品关联ManagerImpl
 *
 * @author zdy
 * @date: 2018年6月13日 下午2:11:42
 */
@Component
@DataSource("coreDataSource")
public class VideoItemRelationManagerImpl implements VideoItemRelationManager {

	@Autowired
	private VideoItemRelationDao videoItemRelationDao;

	@Override
	public List<VideoItemRelation> queryVideoItemRelationList(VideoItemRelation videoItemRelation) {
		return videoItemRelationDao.queryVideoItemRelationList(videoItemRelation);
	}

	@Override
	public VideoItemRelation queryVideoItemRelationByVideoId(long videoId) {
		return videoItemRelationDao.queryVideoItemRelationByVideoId(videoId);
	}

	@Override
	public int countVideoItemRelationBySellerId(long sellerId) {
		return videoItemRelationDao.countVideoItemRelationBySellerId(sellerId);
	}

	@Override
	public int countVideoItemRelationByItemId(long itemId) {
		return videoItemRelationDao.countVideoItemRelationByItemId(itemId);
	}

	@Override
	public int deleteVideoItemRelationByVideoId(long videoId) {
		return videoItemRelationDao.deleteVideoItemRelationByVideoId(videoId);
	}

	@Override
	public long addVideoItemRelation(VideoItemRelation videoItemRelation) {
		videoItemRelationDao.addVideoItemRelation(videoItemRelation);
		return videoItemRelation.getId();
	}

}
