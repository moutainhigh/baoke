package com.baoke.user.service;

import java.io.IOException;

import com.baoke.common.dto.FileDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;

/**
 * 用户Managerservice
 * 
 * @author zjm
 * @date: 2018年7月4日 上午11:21:47
 */
public interface UserInfoManagerService {

	/**
	 * 设置－保存用户性别
	 * 
	 * @author zdy
	 * @date: 2018年7月9日 下午8:15:31
	 * @param userInfoDto
	 * @return
	 * @throws MainException
	 */
	BaseResultDto saveUserSex(UserInfoDto userInfoDto) throws MainException;

	/**
	 * 设置－保存用户昵称
	 * 
	 * @author zdy
	 * @date: 2018年7月9日 下午8:15:31
	 * @param userInfoDto
	 * @return
	 * @throws MainException
	 */
	BaseResultDto saveUserNickName(UserInfoDto userInfoDto) throws MainException;

	/**
	 * 设置－上传头像
	 * 
	 * @author zdy
	 * @date: 2018年7月11日 下午4:29:18
	 * @param fileDto
	 * @return
	 * @throws MainException
	 * @throws IOException
	 */
	UserInfoDto uploadUserHeadImg(FileDto fileDto) throws MainException, IOException;

	/**
	 * 禁言
	 * 
	 * @author zjm
	 * @date: 2018年7月6日 上午10:02:24
	 * @param commonQueryDto
	 * @return
	 * @throws ParamInvalidException
	 */
	BaseDto bannedUserByIds(CommonQueryDto commonQueryDto) throws ParamInvalidException;

	/**
	 * 解除超过禁言时间的用户禁言状态
	 * 
	 * @author zjm
	 * @date: 2018年7月25日 下午3:53:03
	 * @return
	 * @throws ParamInvalidException
	 */
	int modifyUserBeBannedStatusByJob();

}
