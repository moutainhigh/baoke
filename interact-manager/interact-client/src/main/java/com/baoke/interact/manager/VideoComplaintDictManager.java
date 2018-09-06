package com.baoke.interact.manager;

import java.util.List;

import com.baoke.interact.domain.VideoComplaintDict;

/**
 * 举报字典Manager
 * 
 * @author zjm
 * @date: 2018年6月13日 下午4:10:59
 */
public interface VideoComplaintDictManager {

	/**
	 * 获取字典列表
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午4:11:44
	 * @return
	 */
	List<VideoComplaintDict> queryVideoComplaintDictList(VideoComplaintDict videoComplaintDict);

}
