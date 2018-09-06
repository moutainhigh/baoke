package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.UserInfoDto;

/**
 * 用户信息
 *
 * @author zdy
 * @date: 2018年7月3日 上午9:38:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserInfoServiceTest {

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 获取用户信息
	 *
	 * @author zdy
	 * @date: 2018年7月3日 上午9:39:00
	 */
	@Test
	public void testQueryUserInfo() {
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setUserCode("QUFBQUFBQUFBQUk9");// 2L
		userInfoDto.setUserId(9L);
		try {
			UserInfoDto userInfo = userInfoService.queryUserInfo(userInfoDto);

			System.out.println(userInfo);

			assertNotNull("未查询到数据", userInfo);
		} catch (Exception e) {
			fail("error");
		}

	}

	@Test
	public void testQuerySellerInfoById() {
		try {
			SellerInfoDto sellerInfoDto = new SellerInfoDto();
			sellerInfoDto.setSellerId(9L);
			sellerInfoDto = userInfoService.querySellerInfoById(sellerInfoDto);

			System.out.println(sellerInfoDto);

			assertNotNull("未查询到数据", sellerInfoDto);
		} catch (Exception e) {
			fail("error");
		}
	}

}
