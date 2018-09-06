package com.baoke.act.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.act.constant.CodeEnum;
import com.baoke.act.domain.result.ResponseResult;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.item.service.VideoInfoService;

@RestController
public class VideoInfoController {

	private static final Logger logger = Logger.getLogger(VideoInfoController.class);

	@Resource
	private VideoInfoService videoInfoService;

	/**
	 * 视频详情
	 * 
	 * @author ljj
	 * @date: 2018年7月20日 上午9:56:33
	 * @param videoId
	 * @return
	 */
	@RequestMapping(value = "/video/queryVideoInfoByVideoId", method = RequestMethod.POST)
	public ResponseResult queryVideoInfoByVideoId(Long videoId) {

		try {
			VideoInfoDto videoInfo = videoInfoService.queryVideoInfoDetail(new VideoInfoDto(videoId));
			if (logger.isDebugEnabled()) {
				logger.debug("query video info by videoId success, videoId=" + videoId);
			}
			return new ResponseResult(CodeEnum.SUCCESS, "", videoInfo);

		} catch (Exception e) {
			logger.error("query video info by videoId error, videoId=" + videoId, e);
			return new ResponseResult(CodeEnum.ERROR, CodeEnum.ERROR.getName());
		}
	}

}
