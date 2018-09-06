package com.baoke.interact.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.constant.VideoCommentStatusEnum;
import com.baoke.interact.domain.VideoComment;

/**
 * 视频评论Manager
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:12:03
 */
public interface VideoCommentManager {

	/**
	 * 根据ID查询对象
	 * 
	 * @author zdy
	 * @date: 2018年6月21日 下午2:21:50
	 * @param id
	 * @return
	 */
	VideoComment queryVideoCommentById(long id, VideoCommentStatusEnum videoCommentStatusEnum);

	/**
	 * 列表查询集合（视频评论按创建时间倒叙）
	 * 
	 * @author zdy
	 * @date: 2018年6月21日 下午2:21:50
	 * @param videoComment
	 * @return
	 */
	List<VideoComment> queryVideoCommentList(VideoComment videoComment);

	/**
	 * 列表查询集合分页（视频评论按创建时间倒叙）
	 * 
	 * @author zdy
	 * @date: 2018年6月21日 下午2:21:50
	 * @param videoComment
	 * @return
	 */
	List<VideoComment> queryVideoCommentListByPage(VideoComment videoComment, PageInfo pageInfo);

	/**
	 * 查询评论回复分页（按创建时间升序）
	 * 
	 * @author zdy
	 * @date: 2018年6月21日 下午2:21:50
	 * @param videoComment
	 * @return
	 */
	List<VideoComment> queryReplyVideoCommentListByPage(VideoComment videoComment, PageInfo pageInfo);

	/**
	 * 根据videoids, userIds分页查询评论列表
	 * 
	 * @author ljj
	 * @date: 2018年6月28日 下午3:21:50
	 */

	List<VideoComment> queryVideoCommentByVideoIdsAndUserIdsAndContent(List<Long> videoIds, List<Long> userIds,
			String content, VideoCommentStatusEnum videoCommentStatusEnum, PageInfo pageInfo);

	/**
	 * 根据videoids, userIds查询评论总条数
	 * 
	 * @author zjm
	 * @date: 2018年7月9日 下午3:40:30
	 * @param videoIds
	 * @param userIds
	 * @param pageInfo
	 * @return
	 */
	int countVideoCommentByVideoIdsAndUserIdsAndContent(String content, List<Long> videoIds, List<Long> userIds,
			VideoCommentStatusEnum videoCommentStatusEnum);

	/**
	 * 统计视频回复数
	 * 
	 * @author zdy
	 * @date: 2018年6月21日 下午2:21:50
	 * @param id
	 * @return
	 */
	int countVideoComment(VideoComment videoComment);

	/**
	 * 发布、回复评论
	 * 
	 * @author zdy
	 * @date: 2018年6月21日 下午2:21:50
	 * @author: wyj
	 * @date: 2018年6月14日 上午10:49:00
	 */
	long addVideoComment(VideoComment videoComment);

	/**
	 * 根据ids删除多条评论 修改对应记录为删除状态
	 * 
	 * @author ljj
	 * @date: 2018年6月28日 下午2:21:50
	 */

	int modifyVideoCommentDeleteStatusByids(List<Long> Ids, VideoCommentStatusEnum status);
}
