package com.baoke.interact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.VideoComplaint;

/**
 * 视频举报
 * 
 * @author zdy
 * @date 2018年6月15日 下午1:53:56
 */
public interface VideoComplaintDao {

	VideoComplaint queryVideoComplaintById(long id);

	List<VideoComplaint> queryVideoComplaintList(VideoComplaint videoComplaint);

	List<VideoComplaint> queryVideoComplaintListByPage(@Param("videoIds") List<Long> videoIds,
			@Param("userIds") List<Long> userIds, @Param("content") String content,
			@Param("pageInfo") PageInfo pageInfo);

	int queryAuditingComplaintNum(Integer status);

	int countVideoComplaintList(@Param("videoIds") List<Long> videoIds, @Param("userIds") List<Long> userIds,
			@Param("content") String content);

	int addVideoComplaint(VideoComplaint videoComplaint);

	int modifyVideoComplaintStatus(@Param("id") long id, @Param("status") int status);

}