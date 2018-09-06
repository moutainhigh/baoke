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
import com.baoke.common.dto.UserDto;
import com.baoke.common.dto.UserListDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.user.service.UserInfoManagerService;
import com.baoke.user.service.UserInfoService;

/**
 * 用户管理
 * 
 * @author zjm
 * @date: 2018年7月4日 上午10:39:04
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserInfoManagerService userInfoManagerService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 查询用户列表
	 * 
	 * @author zjm
	 * @date: 2018年7月6日 上午9:58:12
	 * @param userInfoDto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
	public ResponsePageResult queryUserList(UserInfoDto userInfoDto, HttpServletRequest request) {
		try {
			UserListDto userInfoListDto = userInfoService
					.queryUserInfoListByPage(new UserDto(userInfoDto, PageUtil.getPageInfo(request)));
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", userInfoListDto.getUserList(),
					userInfoListDto.getPageInfo());
		} catch (MainException e) {
			logger.error("query user list error," + userInfoDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query user list error, " + userInfoDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 禁言
	 * 
	 * @author zjm
	 * @date: 2018年7月6日 上午10:00:22
	 * @param userInfoDto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/banned", method = RequestMethod.POST)
	public ResponseResult banned(CommonQueryDto commonQueryDto, HttpServletRequest request) {

		try {
			userInfoManagerService.bannedUserByIds(commonQueryDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("banned error, " + commonQueryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("banned error," + commonQueryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());

		}
	}

}
