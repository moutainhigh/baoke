package com.baoke.item.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.VideoInfo;

/**
 * 视频Manager
 * 
 * @author: zdy
 * @date: 2018年6月13日 下午1:32:32
 */
public interface VideoInfoManager {

	/**
	 * 根据视频ID和状态查询
	 * 
	 * @author zjj
	 * @date 2018年7月25日 上午9:36:21
	 * @param videoId
	 * @param videoStatusEnum
	 * @return
	 */
	VideoInfo queryVideoInfoByIdAndStatus(long videoId, VideoStatusEnum videoStatusEnum);

	/**
	 * 列表查询视频集合
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param videoInfo
	 * @return
	 */
	List<VideoInfo> queryVideoInfoList(VideoInfo videoInfo);

	/**
	 * 查询主播页视频详情-分页
	 * 
	 * @author zdy
	 * @date: 2018年7月18日 下午8:25:20
	 * @param videoInfo
	 * @param pageInfo
	 * @return
	 */
	List<VideoInfo> querySellerVideoDetailListByPage(VideoInfo videoInfo, PageInfo pageInfo);

	/**
	 * 根据ID查询视频对象
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param videoInfo
	 * @return
	 */
	VideoInfo queryVideoInfoById(long id);

	/**
	 * 视频统计
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param videoInfo
	 * @return
	 */
	int countVideoInfo(VideoInfo videoInfo);

	/**
	 * 根据id列表和状态列表查询视频数量
	 * 
	 * @author zjm
	 * @date: 2018年7月23日 下午8:04:45
	 * @param videoIds
	 * @param statuses
	 * @return
	 */
	int countVideoInfoByStatusesAndIds(List<Long> videoIds, List<Integer> statuses);

	/**
	 * 分页查询视频集合数量
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param videoInfo
	 * @return
	 */
	int countVideoInfoByPage(VideoInfo videoInfo, PageInfo pageInfo);

	/**
	 * 根据播主id列表分页查询视频集合数量
	 * 
	 * @author zjm
	 * @date: 2018年7月9日 下午7:38:50
	 * @param sellerIds
	 * @param videoInfo
	 * @param pageInfo
	 * @return
	 */
	int countVideoInfoByPageAndSellerIds(List<Long> sellerIds, VideoInfo videoInfo, PageInfo pageInfo);

	/**
	 * 根据视频id列表和状态查询视频数量
	 * 
	 * @author zjm
	 * @date: 2018年7月23日 下午3:16:27
	 * @param videoIds
	 * @param videoStatusEnum
	 * @return
	 */
	int countVideoInfoListByVideoIds(List<Long> videoIds, VideoStatusEnum videoStatusEnum);

	/**
	 * 查询视频详情集合
	 * 
	 * @author zdy
	 * @date: 2018年6月27日 下午4:26:15
	 * @param id
	 * @param pageSize
	 * @return
	 */
	List<VideoInfo> queryVideoDetailList(VideoInfo videoInfo, PageInfo pageInfo);

	/**
	 * 分页查询视频集合
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param videoInfo
	 * @return
	 */
	List<VideoInfo> queryVideoInfoListByPage(VideoInfo videoInfo, PageInfo pageInfo);

	/**
	 * 根据播主id类别分页查询视频集合
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param videoInfo
	 * @return
	 */
	List<VideoInfo> queryVideoInfoListByPageAndSellerIds(List<Long> sellerIds, VideoInfo videoInfo, PageInfo pageInfo);

	/**
	 * 根据主播Id查询视频
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:49:32
	 * @param map
	 * @return
	 */
	List<VideoInfo> queryVideoInfoListBySellerIdsAndVideoIds(List<Long> sellerIdlist, List<Long> videoIdlist,
			Long videoId, List<Integer> videoStatusList, PageInfo pageInfo);

	/**
	 * 根据视频id列表查询视频列表
	 * 
	 * @author zjm
	 * @date: 2018年7月12日 下午4:32:54
	 * @param videoIds
	 * @param videoStatusEnum
	 * @return
	 */
	List<VideoInfo> queryVideoInfoListByVideoIds(List<Long> videoIds, VideoStatusEnum videoStatusEnum);

	/**
	 * 根据视频标题和卖家查询
	 * 
	 * @author ljj
	 * @date: 2018年7月5日 下午6:30:03
	 * @param title
	 * @param sellerId
	 * @return
	 */
	List<VideoInfo> queryVideoInfoListByTitleAndSellerId(String title, Long sellerId, VideoStatusEnum videoStatusEnum);

	/**
	 * 根据视频标题查询
	 * 
	 * @author ljj
	 * @date: 2018年7月25日 下午2:30:03
	 * @param title
	 * @param title
	 * @return
	 */
	List<VideoInfo> queryVideoInfoListByTitle(String title);

	/**
	 * 根据id修改视频状态
	 * 
	 * @author zjm
	 * @date: 2018年6月27日 上午10:39:20
	 * @param videoInfo
	 * @return
	 */
	int modifyVideoInfoStatusById(Long id, String auditResult, Integer status);

	/**
	 * 根据id列表修改视频状态
	 * 
	 * @author zjm
	 * @date: 2018年6月27日 上午10:39:20
	 * @param videoInfo
	 * @return
	 */
	int modifyVideoInfoStatusByIds(List<Long> videoIds, Integer status, String auditResult);

	/**
	 * 根据视频id更新视频信息
	 * 
	 * @author zjm
	 * @date: 2018年7月13日 下午6:35:10
	 * @param videoInfo
	 * @return
	 */
	int modifyVideoInfoByVideoId(VideoInfo videoInfo);

	/**
	 * 新增视频
	 * 
	 * @author: wyj
	 * @date: 2018年7月2日 下午3:58:56
	 */
	long addVideoInfo(VideoInfo videoInfo);

}
