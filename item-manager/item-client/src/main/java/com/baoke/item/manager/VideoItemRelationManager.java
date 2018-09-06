package com.baoke.item.manager;

import java.util.List;

import com.baoke.item.domain.VideoItemRelation;

/**
 * 视频商品关联Manager
 * 
 * @author zdy
 * @date: 2018年6月13日 下午1:49:32
 */
public interface VideoItemRelationManager {

	/**
	 * 通用查询
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param videoItemRelation
	 * @return
	 */
	List<VideoItemRelation> queryVideoItemRelationList(VideoItemRelation videoItemRelation);

	/**
	 * 根据视频ID查询最近一条数据
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param id
	 * @return
	 */
	VideoItemRelation queryVideoItemRelationByVideoId(long videoId);

	/**
	 * 查询播主视频数
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param sellerId
	 * @return
	 */
	int countVideoItemRelationBySellerId(long sellerId);

	/**
	 * 根据商品id查询关联的视频数
	 * 
	 * @author zjm
	 * @date: 2018年7月23日 上午11:23:54
	 * @param itemId
	 * @return
	 */
	int countVideoItemRelationByItemId(long itemId);

	/**
	 * 根据视频ID删除关联
	 * 
	 * @author zjm
	 * @date: 2018年7月13日 下午6:57:35
	 * @param videoId
	 * @return
	 */
	int deleteVideoItemRelationByVideoId(long videoId);

	/**
	 * 新增视频商品关联
	 * 
	 * @author: wyj
	 * @date: 2018年7月2日 下午3:59:52
	 */
	long addVideoItemRelation(VideoItemRelation videoItemRelation);
}
