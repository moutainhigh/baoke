package com.baoke.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.user.domain.UserInfo;

public interface UserInfoDao {

	UserInfo queryUserInfoById(long id);

	List<UserInfo> queryUserInfoListByPage(@Param(value = "userInfo") UserInfo userInfo,
			@Param(value = "pageInfo") PageInfo pageInfo);

	UserInfo queryUserInfo(UserInfo userInfo);

	List<UserInfo> queryUserInfoByPhone(@Param(value = "phone") String phone);

	List<UserInfo> queryUserInfoByUnionId(@Param(value = "unionId") String unionId);

	int countUserInfo(UserInfo userInfo);

	int countUserInfoByNickName(@Param("nickName") String nickName, @Param("status") Integer status);

	// 用户模糊查询
	List<UserInfo> queryUserInfoByNickName(@Param("nickName") String nickName);

	List<UserInfo> queryUserInfoByIds(@Param("userIdList") List<Long> userIdList, @Param("status") Integer status);

	int addUserInfo(UserInfo userInfo);

	int modifyUserBeBannedStatusByJob(@Param("beBannedStatus") Integer beBannedStatus,
			@Param("bannedStatus") Integer bannedStatus);

	int modifyUserInfoById(UserInfo userInfo);

	int modifyUserInfoByIds(@Param("userIds") List<Long> userIds, @Param("userInfo") UserInfo userInfo);

	int modifyUserNickNameById(@Param("id") Long id, @Param("nickName") String nickName);

}