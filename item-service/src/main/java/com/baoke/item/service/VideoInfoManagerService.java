package com.baoke.item.service;

import java.text.ParseException;

import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 视频seller增删改service
 * 
 * @author zjm
 * @date: 2018年6月27日 上午10:33:48
 */
public interface VideoInfoManagerService {

	/**
	 * 根据条件查询播主视频
	 * 
	 * @author zjm
	 * @date: 2018年7月6日 上午11:10:22
	 * @param videoDto
	 * @return
	 * @throws ParamInvalidException
	 */
	public SellerVideoListDto querySellerVideoList(CommonQueryDto commonQueryDto) throws ParamInvalidException;

	/**
	 * 保存视频状态
	 * 
	 * @author zjm
	 * @date: 2018年6月27日 上午10:36:24
	 * @param videoInfoDto
	 * @return
	 * @throws ParamInvalidException
	 */
	BaseResultDto saveVideoStatus(CommonQueryDto commonQueryDto) throws ParamInvalidException;

	/**
	 * 视频审核
	 * 
	 * @author zjm
	 * @date: 2018年7月6日 下午3:08:28
	 * @param CommonQueryDto
	 * @return
	 * @throws ParamInvalidException
	 */
	BaseDto auditVideoInfo(CommonQueryDto commonQueryDto) throws ParamInvalidException;

	/**
	 * 发布视频
	 *
	 * @author zjm
	 * @date: 2018年6月27日 下午1:48:58
	 * @param releaseVideoDto
	 * @return
	 * @throws ParamInvalidException
	 * @throws ParseException
	 */
	void saveVideoInfo(VideoInfoDto videoInfoDto) throws ParamInvalidException, ParseException;

}
