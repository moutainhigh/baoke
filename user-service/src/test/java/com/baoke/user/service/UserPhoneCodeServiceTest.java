package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.base.MainException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserPhoneCodeServiceTest {

	@Resource
	private UserPhoneCodeService userPhoneCodeService;

	/**
	 * 获取手机登录验证码
	 *
	 * @author zdy
	 * @date: 2018年6月30日 下午3:46:55
	 */
	@Test
	public void testCreateLoginPhoneCode() {
		SmsDto smsDto = new SmsDto();
		smsDto.setPhone("18758870781");
		try {
			BaseResultDto baseResultDto = userPhoneCodeService.createLoginPhoneCode(smsDto);

			System.out.println(baseResultDto);

			assertNotNull("查询数据不为空", baseResultDto);
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 微信绑定手机号—获取短信验证码
	 *
	 * @author zjm
	 * @date: 2018年7月4日 下午5:32:18
	 * @param smsDto
	 * @return
	 * @throws MainException
	 */
	public void testCreateBindPhoneCode() {
		try {
			SmsDto smsDto = new SmsDto();
			smsDto.setPhone("18758870781");
			BaseResultDto baseResultDto = userPhoneCodeService.createBindPhoneCode(smsDto);

			System.out.println(baseResultDto);

			assertNotNull("查询数据不为空", baseResultDto);
		} catch (Exception e) {
			fail("error");
		}
	}

}
