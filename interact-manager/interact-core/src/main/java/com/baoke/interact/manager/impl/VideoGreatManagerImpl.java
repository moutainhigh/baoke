package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.dao.VideoGreatDao;
import com.baoke.interact.domain.VideoGreat;
import com.baoke.interact.manager.VideoGreatManager;

/**
 * 视频点赞(喜欢) ManagerImpl
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:28:26
 */
@Component
@DataSource("interactDataSource")
public class VideoGreatManagerImpl implements VideoGreatManager {

	@Resource
	private VideoGreatDao videoGreatDao;

	@Override
	public List<VideoGreat> queryVideoGreatList(VideoGreat videoGreat) {
		return videoGreatDao.queryVideoGreatList(videoGreat);
	}

	@Override
	public int countVideoGreatNum(VideoGreat videoGreat) {
		return videoGreatDao.countVideoGreat(videoGreat);
	}

	@Override
	public int queryLastdayAddLikeNum(long sellerId, GreatStatusEnum greatStatusEnum) {
		return videoGreatDao.queryLastdayAddLikeNum(sellerId, greatStatusEnum.getCode());
	}

	@Override
	public long addVideoGreat(VideoGreat videoGreat) {
		videoGreatDao.addVideoGreat(videoGreat);
		return videoGreat.getId();
	}

	@Override
	public int modifyVideoGreat(VideoGreat videoGreat) {
		return videoGreatDao.modifyVideoGreat(videoGreat);
	}

}
