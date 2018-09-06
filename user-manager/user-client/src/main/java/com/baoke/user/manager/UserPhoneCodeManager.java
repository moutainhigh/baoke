package com.baoke.user.manager;

import java.util.Date;

import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.user.domain.UserPhoneCode;

/**
 * 用户登录验证码manager
 * 
 * @author zjm
 * @Date: 2018年6月6日 下午1:51:52
 */
public interface UserPhoneCodeManager {

	/**
	 * 校验验证码是否正确
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:29:35
	 * @param userPhoneCode
	 * @return
	 */
	UserPhoneCode queryUserPhoneCode(UserPhoneCode userPhoneCode);

	/**
	 * 根据手机号和日期查询发送次数
	 * 
	 * @author zjm
	 * @date: 2018年6月19日 下午7:44:10
	 * @param phone
	 * @param date
	 * @return
	 */
	int countUserPhoneCodeByPhoneAndDate(String phone, Date date, UserPhoneCodeTypeEnum type);

	/**
	 * 新增验证码
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:29:11
	 * @param userPhoneCode
	 * @return
	 */
	long addUserPhoneCode(UserPhoneCode userPhoneCode);
}
