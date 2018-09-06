package com.baoke.user.manager.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.db.DataSource;
import com.baoke.user.dao.UserPhoneCodeDao;
import com.baoke.user.domain.UserPhoneCode;
import com.baoke.user.manager.UserPhoneCodeManager;

/**
 * 用户登录验证码manager实现类
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午1:53:10
 */
@Component
@DataSource("miscDataSource")
public class UserPhoneCodeManagerImpl implements UserPhoneCodeManager {

	@Resource
	private UserPhoneCodeDao UserPhoneCodeDao;

	@Override
	public UserPhoneCode queryUserPhoneCode(UserPhoneCode queryUserPhoneCode) {
		return UserPhoneCodeDao.queryUserPhoneCode(queryUserPhoneCode);

	}

	@Override
	public int countUserPhoneCodeByPhoneAndDate(String phone, Date date, UserPhoneCodeTypeEnum type) {
		return UserPhoneCodeDao.countUserPhoneCodeByPhoneAndDate(phone, date, type.getCode());
	}

	@Override
	public long addUserPhoneCode(UserPhoneCode userPhoneCode) {
		UserPhoneCodeDao.addUserPhoneCode(userPhoneCode);
		return userPhoneCode.getId();
	}
}
