package com.baoke.seller.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.seller.VideoCommentAdminDto;
import com.baoke.common.dto.seller.VideoCommentAdminListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.interact.service.VideoCommentManagerService;
import com.baoke.interact.service.VideoCommentService;
import com.baoke.item.service.VideoInfoService;
import com.baoke.seller.util.PageUtil;

/**
 * 评论管理相关控制器
 * 
 * @author ljj
 * @date: 2018年6月28日 下午2:18:28
 */
@RestController
@RequestMapping("/comment")
public class VideoCommentController {

	private static final Logger logger = LoggerFactory.getLogger(VideoCommentController.class);
	@Resource
	private VideoInfoService videoInfoService;
	@Resource
	private VideoCommentService videoCommentService;
	@Resource
	private VideoCommentManagerService videoCommentManagerService;

	@RequestMapping(value = "/queryVideoCommentList", method = RequestMethod.POST)
	public ResponsePageResult queryVideoComment(VideoCommentAdminDto videoCommentAdminDto, HttpSession session,
			HttpServletRequest request) {

		Long userId = (Long) session.getAttribute("userId");
		videoCommentAdminDto.setSellerId(userId);
		videoCommentAdminDto.setPageInfo(PageUtil.getPageInfo(request));
		try {
			VideoCommentAdminListDto videoCommentListDto = videoCommentService
					.queryCommentListByCondition(videoCommentAdminDto);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "",
					videoCommentListDto.getVideoCommentAdminList(), videoCommentListDto.getPageInfo());
		} catch (Exception e) {
			logger.error("query VideoComment  error." + videoCommentAdminDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
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
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("delete videoComment error." + videoCommentAdminDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}
}
