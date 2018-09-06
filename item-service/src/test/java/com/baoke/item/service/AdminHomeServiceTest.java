package com.baoke.item.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.seller.AdminHomeDto;

/**
 * admin首页统计
 *
 * @author lcl
 * @date 2018年7月23日 下午1:30:07
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class AdminHomeServiceTest {

	@Resource
	private AdminHomeService adminHomeService;

	@Test
	public void testQueryAdminHome() {
		try {
			AdminHomeDto adminHomeDto = adminHomeService.queryAdminHome();

			System.out.println(adminHomeDto);
			assertNotNull("断言不为空", adminHomeDto);
		} catch (Exception e) {
			fail("error");
		}

	}

}
