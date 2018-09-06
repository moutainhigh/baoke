package com.baoke.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.admin.sys.enums.UserConfig;
import com.baoke.admin.util.PageUtil;
import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.info.BlackKeyWordDto;
import com.baoke.common.dto.seller.BlackKeyWordListDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.interact.service.BlackKeyWordService;

/**
 * 敏感词管理
 *
 * @author lcl
 * @date: 2018年7月25日 下午2:50:36
 */
@RestController
@RequestMapping("/interact")
public class BlackKeyWordController {

	private static final Logger logger = LoggerFactory.getLogger(BlackKeyWordController.class);

	@Resource
	private BlackKeyWordService blackKeyWordService;

	/**
	 * 敏感词查询
	 * 
	 * @author lcl
	 * @date: 2018年7月25日 下午2:54:57
	 * @param commonQueryDto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryBlackKeyWordListByPage", method = RequestMethod.POST)
	public ResponseResult queryBlackKeyWordInfoList(BlackKeyWordDto blackKeyWordDto, HttpServletRequest request) {
		try {
			blackKeyWordDto.setPageInfo(PageUtil.getPageInfo(request));
			blackKeyWordDto.setBkUserName((String) request.getSession().getAttribute(UserConfig.USER_NAME.getValue()));
			BlackKeyWordListDto blackKeyWordListDto = blackKeyWordService.queryBlackKeyWordListByPage(blackKeyWordDto);
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "",
					blackKeyWordListDto.getBlackKeyWordDtoList(), blackKeyWordListDto.getPageInfo());
		} catch (ParamInvalidException e) {
			logger.error("query blackKeyWord list error." + blackKeyWordDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("query blackKeyWord list error." + blackKeyWordDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 保存敏感词
	 * 
	 * @author lcl
	 * @date: 2018年7月25日 下午7:16:13
	 * @param blackKeyWordDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveBlackKeyWord", method = RequestMethod.POST)
	public ResponseResult saveBlackKeyWord(BlackKeyWordDto blackKeyWordDto, HttpSession session) {
		blackKeyWordDto.setBkUserId((Long) session.getAttribute("USER_ID"));
		try {
			blackKeyWordService.addBlackKeyWord(blackKeyWordDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("save blackKeyWord error." + blackKeyWordDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("save blackKeyWord error." + blackKeyWordDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 根据Id批量刪除敏感词 *
	 * 
	 * @author lcl
	 * @date: 2018年7月26日 下午4:31:04
	 * @param commonQueryDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteBlackKeyWordByIds", method = RequestMethod.POST)
	public ResponseResult deleteBlackKeyWordByIds(CommonQueryDto commonQueryDto, HttpSession session) {
		try {
			blackKeyWordService.deleteBlackKeyWordByIds(commonQueryDto.getIds(),
					(Long) session.getAttribute("USER_ID"));
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("delete blackKeyWord by ids error, ids=" + commonQueryDto.getIds(), e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("delete blackKeyWord by ids error, ids=" + commonQueryDto.getIds(), e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}

	}

}
