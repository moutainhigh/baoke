package com.baoke.admin.controller;

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

import com.baoke.act.service.BannerConfigService;
import com.baoke.admin.util.PageUtil;
import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.constant.UploadFileEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.BannerDto;
import com.baoke.common.dto.BannerListDto;
import com.baoke.common.dto.info.BannerConfigDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.UploadFileUtil;

@RestController
@RequestMapping("/banner")
public class BannerController {

	private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

	@Resource
	private BannerConfigService bannerConfigService;

	/**
	 * banner列表
	 *
	 * @author ljj
	 * @date: 2018年7月9日 下午4:02:14
	 * @param bannerConfigDto
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryBannerList")
	public ResponsePageResult queryBannerList(BannerConfigDto bannerConfigDto, HttpServletRequest request) {

		try {
			BannerListDto bannerListDto = bannerConfigService
					.queryBannerConfigByStatusOrScenType(new BannerDto(bannerConfigDto, PageUtil.getPageInfo(request)));
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", bannerListDto.getBannerConfigList(),
					bannerListDto.getPageInfo());
		} catch (MainException e) {
			logger.error("query banner error." + bannerConfigDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query banner error." + bannerConfigDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 *
	 * banner录入或修改
	 *
	 * @author ljj
	 * @date: 2018年7月9日 下午8:18:54
	 * @param bannerConfigDto
	 * @return
	 */
	@RequestMapping("/saveBannerInfo")
	public ResponseResult saveBannerInfo(BannerConfigDto bannerConfigDto, HttpSession session) {
		bannerConfigDto.setUserId((Long) session.getAttribute("USER_ID"));
		try {
			bannerConfigService.saveBannerConfig(bannerConfigDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("save banner error." + bannerConfigDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("save banner error." + bannerConfigDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 *
	 * 上传banner图片
	 *
	 * @author ljj
	 * @date: 2018年7月10日 下午4:18:54
	 * @param MultipartFile
	 * @return
	 */
	@RequestMapping(value = "/uploadBannerImg", method = RequestMethod.POST)
	public ResponseResult uploadBannerImg(@RequestBody MultipartFile img, HttpSession session) {

		try {
			String url = UploadFileUtil.uploadTencentCloud(img, (Long) session.getAttribute("USER_ID"),
					UploadFileEnum.BANNER)[0];

			Map<String, String> vedioUrl = new HashMap<>();
			vedioUrl.put("imgUrl", url);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", vedioUrl);
		} catch (Exception e) {
			logger.error("upload banner image error.", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		}

	}

}
