package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.SmsDto;
import com.baoke.common.dto.WechatDto;
import com.baoke.common.dto.info.UserInfoDto;

/**
 * 用户绑定信息
 *
 * @author zdy
 * @date: 2018年7月3日 上午9:38:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserBindServiceTest {

	@Resource
	private UserBindService userBindService;

	/**
	 * 微信绑定手机号（并重新下发用户信息）
	 *
	 * @author zdy
	 * @date: 2018年7月3日 上午9:39:11
	 */
	@Test
	public void testBindPhone() {
		try {
			SmsDto smsDto = new SmsDto();
			smsDto.setPhone("18758870000");
			smsDto.setUserId(100007L);
			smsDto.setCode("000000");
			UserInfoDto userInfo = userBindService.bindPhone(smsDto);

			System.out.println(userInfo);

			assertNotNull("用户信息为空", userInfo);
			if (!userInfo.isSuccess()) {
				fail("error");
			}
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testBindWechat() {
		try {
			WechatDto wechatDto = new WechatDto();
			wechatDto.setCode("12121212");
			wechatDto.setUserId(10L);
			UserInfoDto userInfoDto = userBindService.bindWechat(wechatDto);

			System.out.println(userInfoDto);

			assertNotNull("用户信息为空", userInfoDto);
			if (!userInfoDto.isSuccess()) {
				fail("微信绑定手机号（并重新下发用户信息）失败");
			}
		} catch (Exception e) {
			fail("error");
		}
	}

}
