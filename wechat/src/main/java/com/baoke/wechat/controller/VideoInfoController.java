package com.baoke.wechat.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.item.service.VideoInfoService;
import com.baoke.wechat.constant.CodeEnum;
import com.baoke.wechat.result.ResponseResult;

/**
 * 视频详情Controller
 * 
 * @author: wyj
 * @date: 2018年7月5日 上午10:31:39
 */
@RestController
public class VideoInfoController {

	private static final Logger logger = Logger.getLogger(VideoInfoController.class);

	@Resource
	private VideoInfoService videoInfoService;

	/**
	 * 视频详情
	 * 
	 * @author: wyj
	 * @date: 2018年7月5日 上午10:34:53
	 */
	@RequestMapping(value = "/video/queryVideoInfo", method = RequestMethod.POST)
	public ResponseResult createSellerAuthPhoneCode(Long videoId) {
		VideoInfoDto videoInfoDto = new VideoInfoDto(videoId);
		try {
			VideoInfoDto videoInfo = videoInfoService.queryVideoInfoDetail(videoInfoDto);
			return new ResponseResult(CodeEnum.SUCCESS, "", videoInfo);
		} catch (ParamInvalidException e) {
			logger.error("createSellerAuthPhoneCode, videoId=" + videoId, e);
			return new ResponseResult(CodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("createSellerAuthPhoneCode, videoId=" + videoId, e);
			return new ResponseResult(CodeEnum.ERROR, CodeEnum.ERROR.getName());
		}
	}

}
