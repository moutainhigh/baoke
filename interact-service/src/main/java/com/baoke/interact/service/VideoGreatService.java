package com.baoke.interact.service;

import com.baoke.common.dto.api.VideoGreatDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 视频点赞(喜欢)
 * 
 * @author: zdy
 * @date: 2018年6月12日
 */
public interface VideoGreatService {
	/**
	 * 保存点赞情况（喜欢）
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:46:58
	 * @param videoGreatDto
	 * @throws ParamInvalidException
	 */
	BaseResultDto saveVideoGreat(VideoGreatDto videoGreatDto) throws ParamInvalidException;

	/**
	 * 保存感兴趣情况（用户对视频）
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:47:06
	 * @param videoGreatDto
	 * @throws ParamInvalidException
	 */
	BaseResultDto saveVideoDelike(VideoGreatDto videoGreatDto) throws ParamInvalidException;

}
