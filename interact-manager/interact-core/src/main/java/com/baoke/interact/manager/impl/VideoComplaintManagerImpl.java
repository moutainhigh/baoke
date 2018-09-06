package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.constant.VideoComplaintStatusEnum;
import com.baoke.interact.dao.VideoComplaintDao;
import com.baoke.interact.domain.VideoComplaint;
import com.baoke.interact.manager.VideoComplaintManager;

/**
 * 举报Manager实现类
 * 
 * @author zjm
 * @date: 2018年6月13日 下午4:06:53
 */
@Component
@DataSource("interactDataSource")
public class VideoComplaintManagerImpl implements VideoComplaintManager {

	@Resource
	private VideoComplaintDao videoComplaintDao;

	@Override
	public VideoComplaint queryVideoComplaintById(long id) {
		return videoComplaintDao.queryVideoComplaintById(id);
	}

	@Override
	public List<VideoComplaint> queryVideoComplaintList(VideoComplaint videoComplaint) {
		return videoComplaintDao.queryVideoComplaintList(videoComplaint);
	}

	@Override
	public List<VideoComplaint> queryVideoComplaintListByPage(List<Long> videoIds, List<Long> userIds, String content,
			PageInfo pageInfo) {
		return videoComplaintDao.queryVideoComplaintListByPage(videoIds, userIds, content, pageInfo);
	}

	@Override
	public int queryAuditingComplaintNum(VideoComplaintStatusEnum videoComplaintResultEnum) {
		return videoComplaintDao.queryAuditingComplaintNum(videoComplaintResultEnum.getCode());
	}

	@Override
	public int countVideoComplaintList(List<Long> videoIds, List<Long> userIds, String content) {
		return videoComplaintDao.countVideoComplaintList(videoIds, userIds, content);
	}

	@Override
	public long addVideoComplaint(VideoComplaint videoComplaint) {
		videoComplaintDao.addVideoComplaint(videoComplaint);
		return videoComplaint.getId();
	}

	@Override
	public int modifyVideoComplaintStatus(long id, VideoComplaintStatusEnum videoComplaintResultEnum) {
		return videoComplaintDao.modifyVideoComplaintStatus(id, videoComplaintResultEnum.getCode());
	}

}
