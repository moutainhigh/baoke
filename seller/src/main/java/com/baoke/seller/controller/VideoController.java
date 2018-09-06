package com.baoke.seller.controller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.constant.UploadFileEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.UploadFileUtil;
import com.baoke.item.service.VideoInfoManagerService;
import com.baoke.item.service.VideoInfoService;
import com.baoke.seller.util.PageUtil;

/**
 * 视频相关控制器
 * 
 * @author zjm
 * @date: 2018年6月22日 上午11:22:34
 */
@RestController
@RequestMapping("/video")
public class VideoController {

	@Resource
	private VideoInfoService videoInfoService;

	@Resource
	private VideoInfoManagerService videoInfoManagerService;

	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

	/**
	 * 视频查询
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryVideoList", method = RequestMethod.POST)
	public ResponsePageResult queryVideoList(CommonQueryDto commonQueryDto, HttpServletRequest request) {
		try {
			commonQueryDto.setPageInfo(PageUtil.getPageInfo(request));
			commonQueryDto.setUserId((Long) request.getSession().getAttribute("userId"));
			SellerVideoListDto sellerInfoPageDto = videoInfoService.querySellerVideoByStatus(commonQueryDto);
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
	 * 视频上架/下架
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/saveUpperLowerVideoInfo", method = RequestMethod.POST)
	public ResponseResult saveUpperLowerVideoInfo(CommonQueryDto commonQueryDto, HttpServletRequest request) {
		ResponseResult result = new ResponseResult();

		try {
			Long userId = (Long) request.getSession().getAttribute("userId");
			commonQueryDto.setUserId(userId);
			result.setData(videoInfoManagerService.saveVideoStatus(commonQueryDto));
			result.setCode(ResponseResultCodeEnum.SUCCESS);
		} catch (ParamInvalidException e) {
			logger.error("save upper lower video info error" + commonQueryDto, e);
			result.setMessage(e.getResponseMsg());
		} catch (Exception e) {
			logger.error("save upper lower video info error" + commonQueryDto, e);
			result.setMessage(ResponseResultCodeEnum.ERROR.getName());
		}

		return result;

	}

	/**
	 * 发布视频
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/saveVideoInfo", method = RequestMethod.POST)
	public ResponseResult addVideoInfo(@RequestBody VideoInfoDto videoInfoDto, HttpServletRequest request) {
		try {
			videoInfoDto.setUserId((Long) request.getSession().getAttribute("userId"));
			videoInfoManagerService.saveVideoInfo(videoInfoDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("add video info  error." + videoInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("add video info  error." + videoInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 查询单条视频
	 *
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryVideoInfo", method = RequestMethod.POST)
	public ResponseResult queryVideoInfo(VideoInfoDto videoInfoDto, HttpSession session) {
		try {
			VideoInfoDto videoDto = videoInfoService.queryVideoInfoDetail(videoInfoDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", videoDto);
		} catch (ParamInvalidException e) {
			logger.error("query VideoInfo error." + videoInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("query VideoInfo error." + videoInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 上传视频
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
	public ResponseResult uploadVideo(@RequestBody MultipartFile video, HttpSession session) {

		if (video == null) {
			return new ResponseResult(ResponseResultCodeEnum.ERROR, "文件不能为空");
		}

		try {
			String[] urls = UploadFileUtil.uploadTencentCloud(video, (Long) session.getAttribute("userId"),
					UploadFileEnum.VIDEO);
			Map<String, String> videoUrl = new HashMap<>();
			videoUrl.put("imgUrl", urls[0]);
			videoUrl.put("videoUrl", urls[1]);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", videoUrl);
		} catch (ParamInvalidException e) {
			logger.error("upload video error:", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("upload video error:", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}

	}

	/**
	 * 上传封面
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/uploadVideoImg", method = RequestMethod.POST)
	public ResponseResult uploadVideoImg(@RequestBody MultipartFile img, HttpSession session) {
		if (img == null) {
			return new ResponseResult(ResponseResultCodeEnum.ERROR, "文件不能为空");
		}
		try {
			String url = UploadFileUtil.uploadTencentCloud(img, (Long) session.getAttribute("userId"),
					UploadFileEnum.VIDEO_COVER)[0];
			Map<String, String> imgUrl = new HashMap<>();
			imgUrl.put("url", url);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", imgUrl);
		} catch (ParamInvalidException e) {
			logger.error("upload video image cover error:", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("upload video image cover error:", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

}
