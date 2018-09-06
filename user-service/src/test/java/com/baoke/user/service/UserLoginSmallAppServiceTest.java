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
import com.baoke.user.domain.UserWechatInfo;

/**
 * 用户小程序登陆
 *
 * @author lcl
 * @date 2018年7月19日 下午4:54:10
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserLoginSmallAppServiceTest {

	@Resource
	private UserLoginSmallAppService userLoginSmallAppService;

	/**
	 * 初始化小程序
	 *
	 * @author lcl
	 * @throws Exception
	 * @date 2018年7月20日 下午1:47:37
	 *
	 */
	@Test
	public void testLoginSmallApp() {

		try {
			UserWechatInfo userWechatInfo = new UserWechatInfo();
			userWechatInfo.setOpenId("o49N65XgR658cwA5vt50a6fBxSWY");
			// 非空测试
			// userWechatInfo.setUnionId("oi4AHv548ZZ6T_lhFt1wwbYmyKVM");
			userWechatInfo.setSourceType(WechatUserSourceTypeEnum.SMALL_APP.getCode());

			LoginResultDto loginResultDto = userLoginSmallAppService.loginSmallApp(userWechatInfo);
			assertNotNull("对象不为空", loginResultDto);
		} catch (Exception e) {
			fail("error");

		}

	}

}
