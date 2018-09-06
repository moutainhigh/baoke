package com.baoke.interact.service;

import com.baoke.common.dto.api.VideoCommentGreatDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.dto.seller.VideoCommentAdminDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 视频评论管理
 * 
 * @author ljj
 * @date: 2018年7月4日 下午2:00:29
 */
public interface VideoCommentManagerService {

	/**
	 * 保存视频评论点赞
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午8:04:00
	 * @param videoCommentDto
	 * @throws ParamInvalidException
	 */
	BaseResultDto saveVideoCommentGreat(VideoCommentGreatDto videoCommentGreatDto) throws ParamInvalidException;

	/**
	 * 发布、回复评论
	 * 
	 * @author: wyj
	 * @date: 2018年6月14日 上午10:52:28
	 * @param videoCommentDto
	 * @throws ParamInvalidException
	 */
	VideoCommentInfoDto saveVideoComment(VideoCommentInfoDto videoCommentDto) throws ParamInvalidException;

	/**
	 * 根据IDs删除视频评论，修改为删除状态
	 * 
	 * @author: ljj
	 * @date: 2018年6月28日 上午10:52:28
	 * @param videoCommentAdminDto.ids
	 * @throws ParamInvalidException
	 */
	int modifyVideoCommentDeleteStatusByids(VideoCommentAdminDto videoCommentAdminDto) throws ParamInvalidException;

}
