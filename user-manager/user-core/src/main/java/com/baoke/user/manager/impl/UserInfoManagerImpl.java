package com.baoke.user.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.user.constant.UserBannedEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.dao.UserInfoDao;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.UserInfoManager;

@Component
@DataSource("coreDataSource")
public class UserInfoManagerImpl implements UserInfoManager {

	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public UserInfo queryUserInfoById(long id) {
		return userInfoDao.queryUserInfoById(id);
	}

	@Override
	public UserInfo queryUserInfoById(BaseDto baseDto) throws ParamInvalidException {
		if (null == baseDto) {
			throw new ParamInvalidException();
		}
		Long userId = baseDto.getUserId();
		if (null == userId) {
			throw new ParamInvalidException("userId不可为空");
		}
		UserInfo userInfo = this.queryUserInfoById(userId);
		if (null == userInfo) {
			throw new ParamInvalidException("用户信息无法找到，请刷新重试");
		}
		return userInfo;
	}

	@Override
	public UserInfo queryUserInfo(UserInfo userInfo) {
		return userInfoDao.queryUserInfo(userInfo);
	}

	@Override
	public List<UserInfo> queryUserInfoByPhone(String phone) {
		return userInfoDao.queryUserInfoByPhone(phone);
	}

	@Override
	public List<UserInfo> queryUserInfoByUnionId(String unionId) {
		return userInfoDao.queryUserInfoByUnionId(unionId);
	}

	@Override
	public List<UserInfo> queryUserInfoListByPage(UserInfo userInfo, PageInfo pageInfo) {
		return userInfoDao.queryUserInfoListByPage(userInfo, pageInfo);
	}

	@Override
	public int countUserInfo(UserInfo userInfo) {
		return userInfoDao.countUserInfo(userInfo);
	}

	@Override
	public int countUserInfoByNickName(String nickName, UserStatusEnum userStatusEnum) {
		return userInfoDao.countUserInfoByNickName(nickName, userStatusEnum.getCode());
	}

	@Override
	public List<UserInfo> queryUserInfoByNickName(String nickName) {
		return userInfoDao.queryUserInfoByNickName(nickName);
	}

	@Override
	public List<UserInfo> queryUserInfoByIds(List<Long> userIdList, UserStatusEnum userStatusEnum) {
		return userInfoDao.queryUserInfoByIds(userIdList, userStatusEnum.getCode());
	}

	@Override
	public int modifyUserBeBannedStatusByJob() {
		return userInfoDao.modifyUserBeBannedStatusByJob(UserBannedEnum.NO.getCode(), UserBannedEnum.YES.getCode());
	}

	@Override
	public int modifyUserInfoById(UserInfo userInfo) {
		return userInfoDao.modifyUserInfoById(userInfo);
	}

	@Override
	public int modifyUserInfoByIds(List<Long> userIds, Date bannedEndTime, Integer bannedStatus, String bannedReason) {
		UserInfo userInfo = new UserInfo();
		userInfo.setBannedEndTime(bannedEndTime);
		userInfo.setBannedStatus(bannedStatus);
		userInfo.setBannedReason(bannedReason);
		return userInfoDao.modifyUserInfoByIds(userIds, userInfo);
	}

	@Override
	public int modifyUserHeadImgById(long userId, String headImgUrl) {
		UserInfo userInfo = new UserInfo();
		userInfo.setHeadImgUrl(headImgUrl);
		userInfo.setId(userId);
		return userInfoDao.modifyUserInfoById(userInfo);
	}

	@Override
	public int modifyUserNickNameById(Long id, String nickName) {
		return userInfoDao.modifyUserNickNameById(id, nickName);
	}

	@Override
	public long addUserInfo(UserInfo userInfo) {
		userInfoDao.addUserInfo(userInfo);
		return userInfo.getId();
	}

}
