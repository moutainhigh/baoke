package com.baoke.user.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.baoke.user.domain.UserPhoneCode;

public interface UserPhoneCodeDao {

	UserPhoneCode queryUserPhoneCode(UserPhoneCode userPhoneCode);

	int countUserPhoneCodeByPhoneAndDate(@Param("phone") String phone, @Param("date") Date date,
			@Param("type") Integer type);

	int addUserPhoneCode(UserPhoneCode userPhoneCode);

}