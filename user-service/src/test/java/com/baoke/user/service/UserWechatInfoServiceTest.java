package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.user.domain.UserWechatInfo;

/**
 * 用户微信信息
 *
 * @author lcl
 * @date 2018年7月19日 下午4:58:29
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserWechatInfoServiceTest {

	@Resource
	private UserWechatInfoService userWechatInfoService;

	/**
	 * 保存微信用户信息
	 *
	 * @author lcl
	 * @date 2018年7月20日 上午10:39:28
	 *
	 */
	@Test
	public void testSaveUserWechatInfo() {
		try {
			UserWechatInfo userWechatInfo = new UserWechatInfo();
			userWechatInfo.setOpenId("12345689");
			userWechatInfo.setSourceType(30);
			/** app 10; 微信公众号 20; 小程序 30 */
			userWechatInfo.setUnionId("123456789");
			userWechatInfo.setStatus(1);

			boolean b = userWechatInfoService.saveUserWechatInfo(userWechatInfo);
			System.out.println(b);
			assertNotNull("断言为true", b);

		} catch (Exception e) {
			fail("error");
		}
	}

}
