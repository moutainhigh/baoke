package com.baoke.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.admin.util.PageUtil;
import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoComplaintInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.dto.seller.VideoComplaintListDto;
import com.baoke.interact.constant.VideoComplaintStatusEnum;
import com.baoke.interact.service.VideoComplaintService;

/**
 * 视频举报
 * 
 * @author: wyj
 * @date: 2018年7月9日 下午4:05:53
 */
@RestController
@RequestMapping("/interact")
public class VideoComplaintController {

	private static final Logger logger = Logger.getLogger(VideoComplaintController.class);

	@Resource
	private VideoComplaintService videoComplaintService;

	/**
	 * 查询举报列表
	 * 
	 * @author: wyj
	 * @date: 2018年7月7日 上午11:19:40
	 */
	@RequestMapping(value = "/queryVideoComplaintList", method = RequestMethod.POST)
	public ResponsePageResult queryVideoComplaintList(CommonQueryDto commonQueryDto, HttpServletRequest request) {

		commonQueryDto.setPageInfo(PageUtil.getPageInfo(request));
		try {
			VideoComplaintListDto videoComplaintListDto = videoComplaintService
					.queryVideoComplaintListByPage(commonQueryDto);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", videoComplaintListDto,
					videoComplaintListDto.getPagination());
		} catch (Exception e) {
			logger.error("query video complaint list error, " + commonQueryDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 保存举报记录处理结果接口
	 * 
	 * @author: wyj
	 * @date: 2018年7月7日 上午11:19:40
	 */
	@RequestMapping(value = "/saveVideoComplaintResult", method = RequestMethod.POST)
	public ResponseResult saveVideoComplaintResult(VideoComplaintInfoDto videoComplaintInfoDto) {

		if (videoComplaintInfoDto.getComplaintId() == null || videoComplaintInfoDto.getStatus() == null) {
			return new ResponseResult(ResponseResultCodeEnum.ERROR, "缺少必要参数");
		}

		VideoComplaintStatusEnum videoComplaintResultEnum = VideoComplaintStatusEnum
				.getVideoComplaintResultEnumByCode(videoComplaintInfoDto.getStatus());
		if (videoComplaintResultEnum == null) {
			logger.error("save video complaint error, status is empty, " + videoComplaintInfoDto);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, "数据有误，修改失败！");
		}

		BaseResultDto baseResultDto = videoComplaintService
				.modifyVideoComplaintStatus(videoComplaintInfoDto.getComplaintId(), videoComplaintResultEnum);
		if (!baseResultDto.isSuccess()) {
			logger.error("save video complaint error, " + baseResultDto);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, baseResultDto.getMessage());
		}
		return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
	}
}
