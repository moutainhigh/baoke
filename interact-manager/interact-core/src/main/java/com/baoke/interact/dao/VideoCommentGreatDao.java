package com.baoke.interact.dao;

import java.util.List;

import com.baoke.interact.domain.VideoCommentGreat;

/**
 * 评论点赞
 * 
 * @author: zdy
 * @date: 2018年6月12日 下午1:52:51
 */
public interface VideoCommentGreatDao {
	List<VideoCommentGreat> queryVideoCommentGreatList(VideoCommentGreat videoCommentGreat);

	VideoCommentGreat queryVideoCommentGreatById(long id);

	int countVideoCommentGreat(VideoCommentGreat videoCommentGreat);

	int addVideoCommentGreat(VideoCommentGreat videoCommentGreat);

	int modifyVideoCommentGreat(VideoCommentGreat videoCommentGreat);

}
