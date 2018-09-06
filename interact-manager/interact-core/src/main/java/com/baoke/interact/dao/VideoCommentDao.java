package com.baoke.interact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.VideoComment;

/**
 * 视频评论
 * 
 * @author zdy
 * @date 2018年6月15日 下午1:52:51
 */
public interface VideoCommentDao {

	VideoComment queryVideoCommentById(@Param("id") long id, @Param("status") Integer status);

	List<VideoComment> queryVideoCommentList(VideoComment videoComment);

	List<VideoComment> queryVideoCommentListByPage(@Param("videoComment") VideoComment videoComment,
			@Param("pageInfo") PageInfo pageInfo);

	List<VideoComment> queryReplyVideoCommentListByPage(@Param("videoComment") VideoComment videoComment,
			@Param("pageInfo") PageInfo pageInfo);

	List<VideoComment> queryVideoCommentByVideoIdsAndUserIdsAndContent(@Param("videoIds") List<Long> videoIds,
			@Param("userIds") List<Long> userIds, @Param("content") String content, @Param("status") Integer status,
			@Param("pageInfo") PageInfo pageInfo);

	int countVideoCommentByVideoIdsAndUserIdsAndContent(@Param("content") String content,
			@Param("videoIds") List<Long> videoIds, @Param("userIds") List<Long> userIds,
			@Param("status") Integer status);

	int countVideoComment(VideoComment videoComment);

	int addVideoComment(VideoComment videoComment);

	int modifyVideoCommentDeleteStatusByids(@Param("ids") List<Long> Ids, @Param("status") Integer status);
}
