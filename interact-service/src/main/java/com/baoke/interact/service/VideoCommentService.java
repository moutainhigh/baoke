package com.baoke.interact.service;

import com.baoke.common.dto.api.VideoCommentListDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.VideoCommentAdminDto;
import com.baoke.common.dto.seller.VideoCommentAdminListDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 视频评论
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:44:26
 */
public interface VideoCommentService {
	/**
	 * 查询视频评论列表
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:47:22
	 * @param videoInfoDto
	 * @return
	 * @throws ParamInvalidException
	 */
	VideoCommentListDto queryVideoCommentListByVideoId(VideoInfoDto videoInfoDto) throws ParamInvalidException;

	/**
	 * *查询回复评论列表
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:47:42
	 * @param videoCommentDto
	 * @return
	 * @throws ParamInvalidException
	 */
	VideoCommentListDto queryReplyVideoCommentListById(VideoCommentInfoDto videoCommentDto)
			throws ParamInvalidException;

	/**
	 * 后台视频评论列表
	 * 
	 * @author: ljj
	 * @date: 2018年6月27日 上午10:52:28
	 * @param videoCommentDto
	 * @throws ParamInvalidException
	 */
	VideoCommentAdminListDto queryCommentListByCondition(VideoCommentAdminDto videoCommentAdminDto);

}
