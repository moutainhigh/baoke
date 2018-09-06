package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.SmsDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.api.json.JsonApiUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserLoginSmsServiceTest {

	@Resource
	private UserLoginSmsService UserLoginService;

	/**
	 * 手机登录并获取用户信息
	 *
	 * @author zdy
	 * @date: 2018年6月30日 下午3:52:16
	 */
	@Test
	public void testLoginSms() {
		SmsDto smsDto = new SmsDto();
		smsDto.setPhone("13296651593");
		smsDto.setCode("089385");
		smsDto.setUserPhoneCodeTypeEnum(UserPhoneCodeTypeEnum.APP_LOGIN);

		try {
			LoginResultDto loginResultDto = UserLoginService.loginSms(smsDto);

			String userInfoStr = JsonApiUtil.convertToJson(loginResultDto);
			System.out.println(userInfoStr);

			assertNotNull("未查询到数据", loginResultDto);
		} catch (MainException e) {
			fail("error");
		}
	}
}
