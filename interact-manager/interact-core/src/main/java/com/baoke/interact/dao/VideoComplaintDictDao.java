package com.baoke.interact.dao;

import java.util.List;

import com.baoke.interact.domain.VideoComplaintDict;

/**
 * 视频举报字典
 * 
 * @author zdy
 * @date 2018年6月15日 下午1:50:22
 */
public interface VideoComplaintDictDao {

	List<VideoComplaintDict> queryVideoComplaintDictList(VideoComplaintDict complaintVideoDict);

}