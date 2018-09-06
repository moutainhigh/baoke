package com.baoke.interact.manager;

import java.util.List;

import com.baoke.interact.domain.VideoCommentGreat;

/**
 * 视频评论点赞 manager
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:08:58
 */
public interface VideoCommentGreatManager {
	/**
	 * 列表查询视频评论点赞集合
	 * 
	 * @param videoCommentGreat
	 * @return
	 */
	List<VideoCommentGreat> queryVideoCommentGreatList(VideoCommentGreat videoCommentGreat);

	/**
	 * 根据ID查询视频评论点赞对象
	 * 
	 * @param id
	 * @return
	 */
	VideoCommentGreat queryVideoCommentGreatById(long id);

	/**
	 * 统计视频评论点赞
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:09:47
	 * @param videoCommentGreat
	 * @return
	 */
	int countVideoCommentGreat(VideoCommentGreat videoCommentGreat);

	/**
	 * 新增视频评论点赞
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:10:59
	 * @param videoCommentGreat
	 * @return
	 */
	long addVideoCommentGreat(VideoCommentGreat videoCommentGreat);

	/**
	 * 修改视频评论
	 * 
	 * @author zdy
	 * @date: 2018年7月2日 下午2:39:28
	 * @param videoCommentGreat
	 * @return
	 */
	int modifyVideoCommentGreat(VideoCommentGreat videoCommentGreat);

}
