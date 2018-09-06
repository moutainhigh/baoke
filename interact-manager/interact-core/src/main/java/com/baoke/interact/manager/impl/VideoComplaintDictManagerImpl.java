package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.interact.dao.VideoComplaintDictDao;
import com.baoke.interact.domain.VideoComplaintDict;
import com.baoke.interact.manager.VideoComplaintDictManager;

/**
 * 举报字典实现类
 * 
 * @author zjm
 * @date: 2018年6月13日 下午4:12:05
 */
@Component
@DataSource("miscDataSource")
public class VideoComplaintDictManagerImpl implements VideoComplaintDictManager {

	@Resource
	private VideoComplaintDictDao videoComplaintDictDao;

	@Override
	public List<VideoComplaintDict> queryVideoComplaintDictList(VideoComplaintDict videoComplaintDict) {

		return videoComplaintDictDao.queryVideoComplaintDictList(videoComplaintDict);
	}

}
