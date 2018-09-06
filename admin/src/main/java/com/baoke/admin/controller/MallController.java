package com.baoke.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baoke.admin.util.PageUtil;
import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.constant.UploadFileEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.MallItemHotInfoDto;
import com.baoke.common.dto.info.MallRecommendInfoDto;
import com.baoke.common.dto.seller.MallItemHotListDto;
import com.baoke.common.dto.seller.MallRecommendListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.UploadFileUtil;
import com.baoke.item.service.MallManagerService;
import com.baoke.item.service.MallService;

/**
 * 商城管理
 * 
 * @author: wyj
 * @date: 2018年7月10日 上午11:23:13
 */
@RestController
@RequestMapping("mall")
public class MallController {

	private Logger logger = Logger.getLogger(MallController.class);

	@Resource
	private MallService mallService;
	@Resource
	private MallManagerService mallManagerService;

	/**
	 * 查询明星推荐列表
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午3:09:12
	 */
	@RequestMapping(value = "queryMallRecommendList", method = RequestMethod.POST)
	public ResponsePageResult queryMallRecommendList(HttpServletRequest request) {
		ResponsePageResult responsePageResult = new ResponsePageResult();
		PageInfo pageInfo = PageUtil.getPageInfo(request);
		MallRecommendListDto mallRecommendListDto = mallService.queryItemRecommendListByPage(pageInfo);
		responsePageResult.setPagination(mallRecommendListDto.getPageInfo());
		mallRecommendListDto.setPageInfo(null);
		responsePageResult.setData(mallRecommendListDto);
		responsePageResult.setCode(ResponseResultCodeEnum.SUCCESS);
		return responsePageResult;
	}

	/**
	 * 上传明星推荐图片
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午3:09:12
	 */
	@RequestMapping(value = "uploadMallRecommendImg", method = RequestMethod.POST)
	public ResponseResult uploadMallRecommendImg(MultipartFile img, HttpSession session) {
		try {
			String url = UploadFileUtil.uploadTencentCloud(img, (Long) session.getAttribute("USER_ID"),
					UploadFileEnum.BANNER)[0];

			Map<String, String> imgResult = new HashMap<>();
			imgResult.put("imgUrl", url);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", imgResult);
		} catch (ParamInvalidException e) {
			logger.error("upload mall recommend image error.", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("upload mall recommend image error.", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 保存明星推荐
	 * 
	 * @author ljj
	 * @date: 2018年7月20日 下午4:42:26
	 * @param mallRecommendInfoDto
	 * @return
	 */
	@RequestMapping("saveMallRecommendInfo")
	public ResponseResult saveMallRecommendInfo(MallRecommendInfoDto mallRecommendInfoDto) {
		try {
			mallManagerService.saveMallRecommendInfo(mallRecommendInfoDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("saveMallRecommendInfo error." + mallRecommendInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("saveMallRecommendInfo error." + mallRecommendInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 查询明星推荐列表
	 * 
	 * @author ljj
	 * @date: 2018年7月20日 下午4:43:19
	 * @param pageInfo
	 * @return
	 */
	@RequestMapping("queryMallHotItemList")
	public ResponsePageResult queryMallHotItemList(PageInfo pageInfo) {
		try {
			MallItemHotListDto mallItemHotListDto = mallService.queryItemHotListByPage(pageInfo);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", mallItemHotListDto, mallItemHotListDto.getPageInfo());
		} catch (ParamInvalidException e) {
			logger.error("query mall hot item list error", e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("query mall hot item list error", e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 保存人气商品接口
	 * 
	 * @author ljj
	 * @date: 2018年7月20日 下午4:43:34
	 * @param mallItemHotInfoDto
	 * @return
	 */
	@RequestMapping("saveHotRecommendItemInfo")
	public ResponseResult saveHotRecommendItemInfo(MallItemHotInfoDto mallItemHotInfoDto) {
		try {
			mallService.saveHotRecommendItemInfo(mallItemHotInfoDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (Exception e) {
			logger.error("saveHotRecommendItemInfo error,", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

}
