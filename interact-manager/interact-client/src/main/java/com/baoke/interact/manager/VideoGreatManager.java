package com.baoke.interact.manager;

import java.util.List;

import com.baoke.interact.constant.GreatStatusEnum;
import com.baoke.interact.domain.VideoGreat;

/**
 * 视频点赞(喜欢)Manager
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:13:09
 */
public interface VideoGreatManager {
	/**
	 * 查询视频点赞列表
	 * 
	 * @param videoGreat
	 * @return
	 */
	List<VideoGreat> queryVideoGreatList(VideoGreat videoGreat);

	/**
	 * 昨日增加的喜欢数
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午4:18:39
	 * @param sellerId
	 * @param greatStatus
	 * @param startTime
	 * @param endTime
	 * @return
	 */

	int queryLastdayAddLikeNum(long sellerId, GreatStatusEnum greatStatusEnum);

	/**
	 * 查询用户喜欢视频数
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:13:31
	 * @param videoGreat
	 * @return
	 */
	int countVideoGreatNum(VideoGreat videoGreat);

	/**
	 * 保存点赞（喜欢）
	 * 
	 * @param videoGreat
	 * @return
	 */
	long addVideoGreat(VideoGreat videoGreat);

	/**
	 * 修改视频点赞信息
	 * 
	 * @param videoGreat
	 * @return
	 */
	int modifyVideoGreat(VideoGreat videoGreat);

}
