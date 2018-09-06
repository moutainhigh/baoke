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
import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.item.service.VideoInfoManagerService;
import com.baoke.item.service.VideoInfoService;

/**
 * 视频管理
 * 
 * @author zjm
 * @date: 2018年7月4日 上午10:41:06
 */
@RestController
@RequestMapping("/video")
public class VideoController {

	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

	@Resource
	private VideoInfoService videoInfoService;

	@Resource
	private VideoInfoManagerService videoInfoManagerService;

	/**
	 * 视频查询
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryVideoInfoList", method = RequestMethod.POST)
	public ResponseResult queryVideoList(CommonQueryDto commonQueryDto, HttpServletRequest request) {
		try {
			commonQueryDto.setPageInfo(PageUtil.getPageInfo(request));
			SellerVideoListDto sellerInfoPageDto = videoInfoManagerService.querySellerVideoList(commonQueryDto);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", sellerInfoPageDto.getVideoList(),
					sellerInfoPageDto.getPageInfo());
		} catch (ParamInvalidException e) {
			logger.error("query video list error." + commonQueryDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("query video list error." + commonQueryDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 视频上线/封禁
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/saveUpperLowerVideoInfo", method = RequestMethod.POST)
	public ResponseResult saveUpperLowerVideoInfo(CommonQueryDto commonQueryDto) {
		try {
			videoInfoManagerService.saveVideoStatus(commonQueryDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("save upper lower video info error." + commonQueryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("save upper lower video info error." + commonQueryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 视频审核
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/auditVideoInfo", method = RequestMethod.POST)
	public ResponseResult auditVideoInfo(CommonQueryDto commonQueryDto) {
		try {

			videoInfoManagerService.auditVideoInfo(commonQueryDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("audit video info error." + commonQueryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("audit video info error:" + commonQueryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}

	}

}
