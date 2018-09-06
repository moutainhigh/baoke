package com.baoke.item.service;

import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.VideoListDto;
import com.baoke.common.dto.api.RecommendHomeDto;
import com.baoke.common.dto.api.VideoDetailDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.dto.wechat.VideoInfoListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.item.domain.VideoInfo;

/**
 * 视频Service
 * 
 * @author zdy
 * @date: 2018年6月13日 下午2:23:05
 */
public interface VideoInfoService {
	/**
	 * 查询首页推荐列表
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:23:37
	 * @param videoInfoDto
	 * @return
	 */
	public RecommendHomeDto queryRecommendHome(VideoInfoDto videoInfoDto);

	/**
	 * 微信小程序首页
	 * 
	 * @author: wyj
	 * @date: 2018年7月5日 上午11:05:25
	 */
	public VideoInfoListDto queryWechatHome(VideoInfoDto videoInfoDto);

	/**
	 * 查询首页关注列表
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:23:46
	 * @param pageInfoDto
	 * @return
	 * @throws ParamInvalidException
	 */
	public VideoListDto queryFollowVideo(VideoInfoDto videoInfoDto) throws MainException;

	/**
	 * 查询视频详情
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:23:55
	 * @param videoInfoDto
	 * @return
	 */
	public VideoListDto queryVideoDetail(VideoDetailDto videoDetailDto) throws MainException;

	/**
	 * 查询当前视频的评论数，点赞数，及视频对应的商品集合
	 * 
	 * @author zdy
	 * @date: 2018年7月25日 下午2:27:19
	 * @param videoInfo
	 * @param userId
	 *            当前登陆用户
	 * @return
	 */
	public VideoInfoDto queryVideoInfoDto(VideoInfo videoInfo, Long userId);

	/**
	 * 查询播主（卖家）首页（视频页）
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:24:42
	 * @param sellerUserInfoDto
	 * @return
	 */
	public SellerVideoListDto querySellerVideo(SellerInfoDto sellerInfoDto) throws MainException;

	/**
	 * 查询发现列表
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:24:54
	 * @param pageInfoDto
	 * @return
	 */
	public VideoListDto queryFindVideo(VideoInfoDto videoInfoDto);

	/**
	 * 查询我喜欢的视频
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:25:03
	 * @param videoInfoDto
	 * @return
	 * @throws ParamInvalidException
	 */
	public VideoListDto queryMyGreatVideo(VideoInfoDto videoInfoDto) throws MainException;

	/**
	 * 根据状态查询播主视频
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午2:24:42
	 * @param sellerUserInfoDto
	 * @return
	 */
	public SellerVideoListDto querySellerVideoByStatus(CommonQueryDto commonQueryDto) throws MainException;

	/**
	 * 查询视频详情
	 *
	 * @author ljj
	 * @date: 2018年7月2日 下午3:24:42
	 */
	public VideoInfoDto queryVideoInfoDetail(VideoInfoDto VideoInfoDto) throws MainException;

}
