package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.dto.LoginResultDto;
import com.baoke.common.dto.WechatDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserLoginWechatServiceTest {

	@Resource
	private UserLoginWechatService userLoginWechatService;

	/**
	 * 微信登录获取用户信息
	 *
	 * @author zdy
	 * @date: 2018年6月30日 下午4:16:06
	 */
	@Test
	public void testLoginWechat() {
		try {
			WechatDto wechatDto = new WechatDto();
			wechatDto.setDeviceId(25l);
			wechatDto.setCode("0610Glfm0iL2Fk1y7Fdm0P7Ffm00GlfR");
			wechatDto.setWechatSourceTypeEnum(WechatUserSourceTypeEnum.MOBILE_APP);
			LoginResultDto loginResultDto = userLoginWechatService.loginApp(wechatDto);

			System.out.println(loginResultDto);
			assertNotNull("未查询到数据", loginResultDto);
		} catch (Exception e) {
			fail("error");
		}
	}
}
