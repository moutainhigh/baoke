package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.interact.dao.VideoCommentDao;
import com.baoke.interact.domain.VideoComment;
import com.baoke.interact.manager.VideoCommentManager;

/**
 * 视频评论managerImpl
 * 
 * @author: zdy
 * @date: 2018年6月9日 下午4:12:05
 */
@Component
@DataSource("interactDataSource")
public class VideoCommentManagerImpl implements VideoCommentManager {

	@Resource
	private VideoCommentDao videoCommentDao;

	@Override
	public VideoComment queryVideoCommentById(long id, VideoCommentStatusEnum videoCommentStatusEnum) {
		return videoCommentDao.queryVideoCommentById(id, videoCommentStatusEnum.getCode());
	}

	@Override
	public List<VideoComment> queryVideoCommentList(VideoComment videoComment) {
		return videoCommentDao.queryVideoCommentList(videoComment);
	}

	@Override
	public List<VideoComment> queryVideoCommentListByPage(VideoComment videoComment, PageInfo pageInfo) {
		return videoCommentDao.queryVideoCommentListByPage(videoComment, pageInfo);
	}

	@Override
	public List<VideoComment> queryReplyVideoCommentListByPage(VideoComment videoComment, PageInfo pageInfo) {
		return videoCommentDao.queryReplyVideoCommentListByPage(videoComment, pageInfo);
	}

	@Override
	public List<VideoComment> queryVideoCommentByVideoIdsAndUserIdsAndContent(List<Long> videoIds, List<Long> userIds,
			String content, VideoCommentStatusEnum videoCommentStatusEnum, PageInfo pageInfo) {
		return videoCommentDao.queryVideoCommentByVideoIdsAndUserIdsAndContent(videoIds, userIds, content,
				videoCommentStatusEnum.getCode(), pageInfo);
	}

	@Override
	public int countVideoCommentByVideoIdsAndUserIdsAndContent(String content, List<Long> videoIds, List<Long> userIds,
			VideoCommentStatusEnum videoCommentStatusEnum) {
		return videoCommentDao.countVideoCommentByVideoIdsAndUserIdsAndContent(content, videoIds, userIds,
				videoCommentStatusEnum.getCode());
	}

	@Override
	public int countVideoComment(VideoComment videoComment) {
		return videoCommentDao.countVideoComment(videoComment);
	}

	@Override
	public long addVideoComment(VideoComment videoComment) {
		videoCommentDao.addVideoComment(videoComment);
		return videoComment.getId();
	}

	@Override
	public int modifyVideoCommentDeleteStatusByids(List<Long> Ids, VideoCommentStatusEnum status) {
		return videoCommentDao.modifyVideoCommentDeleteStatusByids(Ids, status.getCode());
	}

}
