package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.interact.dao.VideoCommentGreatDao;
import com.baoke.interact.domain.VideoCommentGreat;
import com.baoke.interact.manager.VideoCommentGreatManager;

/**
 * 评论点赞 managerImpl
 * 
 * @author: zdy
 * @date: 2018年6月12日 下午4:12:05
 */
@Component
@DataSource("interactDataSource")
public class VideoCommentGreatManagerImpl implements VideoCommentGreatManager {

	@Resource
	private VideoCommentGreatDao videoCommentGreatDao;

	@Override
	public List<VideoCommentGreat> queryVideoCommentGreatList(VideoCommentGreat videoCommentGreat) {
		return videoCommentGreatDao.queryVideoCommentGreatList(videoCommentGreat);
	}

	@Override
	public VideoCommentGreat queryVideoCommentGreatById(long id) {
		return videoCommentGreatDao.queryVideoCommentGreatById(id);
	}

	@Override
	public int countVideoCommentGreat(VideoCommentGreat videoCommentGreat) {
		return videoCommentGreatDao.countVideoCommentGreat(videoCommentGreat);
	}

	@Override
	public long addVideoCommentGreat(VideoCommentGreat videoCommentGreat) {
		videoCommentGreatDao.addVideoCommentGreat(videoCommentGreat);
		return videoCommentGreat.getId();
	}

	@Override
	public int modifyVideoCommentGreat(VideoCommentGreat videoCommentGreat) {
		return videoCommentGreatDao.modifyVideoCommentGreat(videoCommentGreat);
	}

}
