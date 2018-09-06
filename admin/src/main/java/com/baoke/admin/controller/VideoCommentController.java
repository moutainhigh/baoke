package com.baoke.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.admin.util.PageUtil;
import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.seller.VideoCommentAdminDto;
import com.baoke.common.dto.seller.VideoCommentAdminListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.interact.service.VideoCommentManagerService;
import com.baoke.interact.service.VideoCommentService;
import com.baoke.item.service.VideoInfoService;

@RestController
@RequestMapping("/interact")
public class VideoCommentController {

	private static final Logger logger = LoggerFactory.getLogger(VideoCommentController.class);
	@Resource
	private VideoInfoService videoInfoService;
	@Resource
	private VideoCommentService videoCommentService;
	@Resource
	private VideoCommentManagerService videoCommentManagerService;

	/**
	 * 视频的评论列表
	 *
	 * @author ljj
	 * @date: 2018年7月11日 下午3:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryVideoCommentList", method = RequestMethod.POST)
	public ResponsePageResult queryVideoComment(VideoCommentAdminDto videoCommentAdminDto, HttpServletRequest request) {

		videoCommentAdminDto.setPageInfo(PageUtil.getPageInfo(request));
		try {
			VideoCommentAdminListDto videoCommentListDto = videoCommentService
					.queryCommentListByCondition(videoCommentAdminDto);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "",
					videoCommentListDto.getVideoCommentAdminList(), videoCommentListDto.getPageInfo());
		} catch (Exception e) {
			logger.error("query VideoComment  error." + videoCommentAdminDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		}
	}

	/**
	 * 删除评论
	 *
	 * @author ljj
	 * @date: 2018年6月26日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/deleteVideoCommentByIds", method = RequestMethod.POST)
	public ResponseResult deleteVideoCommentByIds(VideoCommentAdminDto videoCommentAdminDto) {

		try {
			videoCommentManagerService.modifyVideoCommentDeleteStatusByids(videoCommentAdminDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("delete videoComment error." + videoCommentAdminDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("delete videoComment error." + videoCommentAdminDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

}
