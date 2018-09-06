package com.baoke.user.manager;

import java.util.Date;
import java.util.List;

import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.domain.UserInfo;

/**
 * 用户manager
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午5:01:51
 */
public interface UserInfoManager {

	/**
	 * 根据用户id查询
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:27:30
	 * @param id
	 * @return
	 */
	UserInfo queryUserInfoById(long id);

	/**
	 * 根据用户ID查询
	 * 
	 * @author zjj
	 * @date 2018年7月7日 下午9:31:02
	 * @param baseDto
	 *            userId可为空
	 * @return 用户为空时抛异常
	 * @throws ParamInvalidException
	 *             为空时抛异常
	 */
	UserInfo queryUserInfoById(BaseDto baseDto) throws ParamInvalidException;

	/**
	 * 查询用户基础信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:27:21
	 * @param userInfo
	 * @return
	 */

	UserInfo queryUserInfo(UserInfo userInfo);

	/**
	 * 根据手机号查询信息
	 * 
	 * @author zdy
	 * @date: 2018年7月16日 下午5:03:10
	 * @param phone
	 * @param userStatusEnum
	 * @return
	 */
	List<UserInfo> queryUserInfoByPhone(String phone);

	/**
	 * 根据UnionId查询用户信息
	 * 
	 * @author zdy
	 * @date: 2018年7月16日 下午5:03:28
	 * @param unionId
	 * @return
	 */
	List<UserInfo> queryUserInfoByUnionId(String unionId);

	/**
	 * 分页查询用户列表
	 * 
	 * @author zjm
	 * @date: 2018年7月4日 上午11:28:40
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> queryUserInfoListByPage(UserInfo userInfo, PageInfo pageInfo);

	/**
	 * 查询用户总数
	 * 
	 * @author zjm
	 * @date: 2018年7月16日 上午11:49:41
	 * @param userInfo
	 * @return
	 */
	int countUserInfo(UserInfo userInfo);

	/**
	 * 根据用户昵称查询用户数量
	 * 
	 * @author zjm
	 * @date: 2018年7月24日 下午4:35:09
	 * @param nickName
	 * @param userStatusEnum
	 * @return
	 */
	int countUserInfoByNickName(String nickName, UserStatusEnum userStatusEnum);

	/**
	 * 用户模糊查询
	 * 
	 * @author ljj
	 * @date: 2018年6月28日 下午2:27:44
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> queryUserInfoByNickName(String nickName);

	/**
	 * 通过userIds查询user信息
	 * 
	 * @author zjj
	 * @date 2018年7月13日 下午12:16:15
	 * @param userIdList
	 * @param pageInfo
	 * @return
	 */
	List<UserInfo> queryUserInfoByIds(List<Long> userIdList, UserStatusEnum statusEnum);

	/**
	 * 解除超过禁言时间的用户禁言状态
	 * 
	 * @author zjm
	 * @date: 2018年7月25日 下午3:54:33
	 * @return
	 */
	int modifyUserBeBannedStatusByJob();

	/**
	 * 修改用戶信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:27:37
	 * @param userInfo
	 * @return
	 */
	int modifyUserInfoById(UserInfo userInfo);

	/**
	 * 修改用戶头像
	 * 
	 * @author zdy
	 * @date: 2018年7月11日 下午5:19:57
	 * @param userId
	 * @param headImgUrl
	 * @return
	 */
	int modifyUserHeadImgById(long userId, String headImgUrl);

	/**
	 * 修改昵称
	 * 
	 * @author zjj
	 * @date 2018年7月23日 下午1:54:30
	 * @param id
	 * @param nickName
	 * @return
	 */
	int modifyUserNickNameById(Long id, String nickName);

	/**
	 * 根据id列表修改用戶禁言信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:27:37
	 * @return
	 */
	int modifyUserInfoByIds(List<Long> userIds, Date bannedEndTime, Integer bannedStatus, String bannedReason);

	/**
	 * 新增用户
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:27:44
	 * @param userInfo
	 * @return
	 */
	long addUserInfo(UserInfo userInfo);

}
